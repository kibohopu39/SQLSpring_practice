package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.Detail;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IDetailRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeckResponse;
import danny.yugioh.response.PlayerNameResponse;
import danny.yugioh.service.IDeckListService;
import danny.yugioh.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    IPlayerRepository playerRepository;
    @Autowired
    IDetailRepository detailRepository;
    @Autowired
    IDeckListRepository deckListRepository;
    @Autowired
    IDeckListService deckListService;

    @Override
    public String newDuelist(NewPlayerRequest input) {
        Player player = new Player();
        player.setAge(input.getAge());
        player.setName(input.getName());
        player.setRank(input.getRank());
        player.setEmail(input.getEmail());

        Detail detail = new Detail();
        detail.setArea(input.getLivingArea());
        detail.setGender(input.getGender());

        player.setDetail(detail);
        playerRepository.save(player);
        return "決鬥者資料加入成功";
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String newDuelDeck(AddDuelDeckRequest input) throws Exception {
        //要先看新增的決鬥者名字有沒有先登入在資料庫裡
        Player tempplayer = cheackPlayer(input.getDuelistId());
        //問新增的牌組名稱是不是重複新增了
        List<DeckList> tempDecklist = tempplayer.getDeckLists();//拿查詢到的決鬥者，獲取其牌組清單以便尋訪得知牌組名稱
        for (DeckList dl : tempDecklist) {//尋訪牌組清單
            if (dl.getDeckname().equals(input.getDeckname())) {//比較是否有跟輸入的牌組名稱一樣
                throw new Exception("該牌組已經存在囉!");
            }
        }
        //新增
        //DeckList 卡包
        //duelist 玩家

        DeckList deckList = new DeckList();
        deckList.setDeckname(input.getDeckname());//去設置本次要新增的牌組內容
        deckList.setDuelist(tempplayer);//跟玩家建立關聯
        deckListRepository.save(deckList);//儲存
        return "新增牌組清單成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeArea(ChangeDuelistAreaRequest input) throws Exception {
        //先看要改的玩家
        Optional<Player> tempduelist = playerRepository.findById(input.getId());
        if (!tempduelist.isPresent()) {
            throw new Exception("您要新增的決鬥者不存在");
        }
        Detail detail = tempduelist.get().getDetail();
        detail.setArea(input.getArea());
        detailRepository.save(detail);
        return "修改居住地區成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String newDeckOwner(NewDeckOwnerRequest input) throws Exception {
        //先看玩家
        Optional<Player> tempplayer = playerRepository.findById(input.getPlayerId());
        if (!tempplayer.isPresent()) {
            throw new Exception("您要認領牌組的決鬥者不存在");
        }
        //看認領牌組在不在，順便看是不是真的是孤兒
        List<DeckList> deckLists = deckListRepository.findallByname(input.getDeckname());
        if (!deckLists.isEmpty()) {
            throw new Exception("輸入的牌組不存在!");
        }
        for (DeckList deck : deckLists) {
            Player player = deck.getDuelist();
            if (!player.getName().isEmpty()) {
                throw new Exception("輸入的牌組有人用囉!");
            }
        }
        //最後檢查要認領的玩家是不是有相同的牌組名稱
        List<DeckList> tempDecklist = tempplayer.get().getDeckLists();//拿查詢到的決鬥者，獲取其牌組清單
        for (DeckList dl : tempDecklist) {//尋訪牌組清單
            if (dl.getDeckname().equals(input.getDeckname())) {//比較是否有跟認領的牌組名稱一樣
                throw new Exception("該認領牌組跟你已有的牌組名稱有重複哦!");
            }
        }

        Player duelist = tempplayer.get();//獲取該符合的決鬥者
        DeckList deckList = new DeckList();
        deckList.setDeckname(input.getDeckname());//去設置本次要新增的牌組內容
        deckList.setDuelist(duelist);//設置玩家
        deckListRepository.save(deckList);//儲存


        return "玩家認領牌組成功";
    }

    @Override
    public String deletePlayer(int playerId) throws Exception {
        //直接刪除該名字的
        Player player = cheackPlayer(playerId);
        playerRepository.delete(player);
        return "刪除玩家成功";
    }

    @Override
    public String deletePlayerDeck(DeckNamePlayerRequest input) throws Exception {
        //玩家在不?
        Player player = cheackPlayer(input.getPlayerId());
        //牌組在不?牌組要先跟玩家斷關係
        List<String> deckNameList = input.getDeckNameList();
        for (String deckname : deckNameList) {
            DeckList deckList = cheackPlayerDeck(deckname, player);//傳出玩家符合要刪除的牌組
            deckList.setDuelist(null);//先跟玩家斷關聯再刪掉
            deckListRepository.delete(deckList);
        }
        return "刪除完成";
    }

    @Override
    public PlayerNameResponse queryPlayer(String gender) {
        List<Player> playersByGender = playerRepository.findPlayersByGender(gender);
        PlayerNameResponse playerName = new PlayerNameResponse();
        List<Player> outputplayers = playerName.getPlayers();
        for (Player p : playersByGender) {
            outputplayers.add(p);
        }
        return playerName;
    }

    @Override
    public PlayerDeckResponse queryPlayerDeck(DeckNamePlayerRequest input) throws Exception {
        //玩家在否?
        Player player = cheackPlayer(input.getPlayerId());
        //牌組在不?
        List<String> deckNameList = input.getDeckNameList();
        //要回傳用的
        PlayerDeckResponse playerDeck = new PlayerDeckResponse();
        List<DeckList> deckLists = playerDeck.getDeckLists();
        for (String deckname : deckNameList) {
            DeckList deckList = cheackPlayerDeck(deckname, player);//傳出玩家符合的牌組
            deckLists.add(deckList);
        }
        return playerDeck;
    }

    @Override
    public int changePlayerAndDeck(PlayerAndDeckRequest input) throws Exception {
        //要判斷人，判斷牌組(即判斷有沒有關聯)
        Player playerTarget = cheackPlayer(input.getPlayerIdtarget());//判斷修改前有沒有這個人，有的話取出
        DeckList deckList = cheackPlayerDeck(input.getOlddeckname(), playerTarget);//修改對象有沒有這個卡包?有的話再改
        //分成兩個部分，一個是對應卡包修改人ID，一個是對應人ID修改卡包名，我後者方法寫在DeckListService裡

        //對想要改的卡包設置更改後的name
        deckListService.changeDeckName(input, deckList);
        //接下來要去修改玩家Id
//        Player player = playerRepository.findById(input.getPlayerIdtarget()).get();
//        playerTarget.setDeckLists(null);
//        player.setId(5);
//        Player player=new Player();
        playerTarget.setName("小叮噹");
//        deckList.setDuelist(playerTarget);
        playerRepository.save(playerTarget);//用操作player資料表的介面方法去儲存
//        playerTarget.setId(input.getPlayerId());
//        playerRepository.save(playerTarget);


        int result = 0;
        return result;
//        return "同時修改成功";
    }


    //方法1，判斷有無這個玩家
    private Player cheackPlayer(Integer id) throws Exception {
        Optional<Player> player = playerRepository.findById(id);
        if (!player.isPresent()) {
            throw new Exception("沒有這個決鬥者");
        }
        return player.get();
    }

    //方法2，判斷玩家牌組清單有無這個牌組，有的話回傳該牌組
    private DeckList cheackPlayerDeck(String deckname, Player player) throws Exception {
        List<DeckList> deckLists = player.getDeckLists();//該玩家的牌組清單列表
        for (DeckList i : deckLists) {//尋訪清單列表
            if (i.getDeckname().equals(deckname)) {//有對應的名字就傳回
                return i;
            }
        }
        throw new Exception("沒有這個牌組!");
    }
}
