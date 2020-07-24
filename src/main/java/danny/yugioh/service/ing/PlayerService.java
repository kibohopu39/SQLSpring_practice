package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.Detail;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IDetailRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.AddDuelDeckRequest;
import danny.yugioh.request.AddPlayerRequest;
import danny.yugioh.request.ChangeDuelistArea;
import danny.yugioh.request.NewDeckOwner;
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
    @Override
    public String newDuelist(AddPlayerRequest input) {
        Player player=new Player();
        player.setAge(input.getAge());
        player.setName(input.getName());
        player.setRank(input.getRank());
        player.setEmail(input.getEmail());

        Detail detail=new Detail();
        detail.setArea(input.getLivingArea());
        detail.setGender(input.getGender());

        player.setDetail(detail);
        playerRepository.save(player);
        return "決鬥者資料加入成功";
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String newDuelDeck(AddDuelDeckRequest input) throws Exception{
        //要先看新增的決鬥者名字有沒有先登入在資料庫裡
        Optional<Player> tempplayer = playerRepository.findById(input.getDuelistId());
        if (!tempplayer.isPresent()){
            throw new Exception("您要新增的決鬥者不存在!");
        }
        //問新增的牌組名稱是不是重複新增了
        List<DeckList> tempDecklist=tempplayer.get().getDeckLists();//拿查詢到的決鬥者，獲取其牌組清單
        for (DeckList dl:tempDecklist) {//尋訪牌組清單
            if(dl.getDeckname().equals(input.getDeckname())){//比較是否有跟輸入的牌組名稱一樣
                throw new Exception("該牌組已經存在囉!");
            }
        }
        //可以新增了


        Player duelist = tempplayer.get();//獲取該符合的決鬥者
        //DeckList 卡包
        //duelist 玩家

        DeckList deckList = new DeckList();
        deckList.setDeckname(input.getDeckname());//去設置本次要新增的牌組內容
//        deckListRepository.save(deckList);
//        duelist.getDeckLists().add(deckList);
//        tempDecklist.add(deckList);
//        duelist.setDeckLists(tempDecklist);
//
//        duelistRepository.save(duelist);
        deckList.setDuelist(duelist);//設置玩家
        deckListRepository.save(deckList);//儲存

        return "新增牌組清單成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeArea(ChangeDuelistArea input) throws Exception {
        //先看要改的玩家
        Optional<Player> tempduelist = playerRepository.findById(input.getId());
        if (!tempduelist.isPresent()){
            throw new Exception("您要新增的決鬥者不存在");
        }
        Detail detail=tempduelist.get().getDetail();
        detail.setArea(input.getArea());
        detailRepository.save(detail);
        return "修改居住地區成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String newDeckOwner(NewDeckOwner input) throws Exception {
        //先看玩家
        Optional<Player> tempplayer = playerRepository.findById(input.getPlayerId());
        if (!tempplayer.isPresent()){
            throw new Exception("您要認領牌組的決鬥者不存在");
        }
        //看認領牌組在不在，順便看是不是真的是孤兒
        List<DeckList> deckLists = deckListRepository.findallByname(input.getDeckname());
        if (!deckLists.isEmpty()){
            throw new Exception("輸入的牌組不存在!");
        }
        for (DeckList deck:deckLists) {
            Player player = deck.getDuelist();
            if (!player.getName().isEmpty()){
                throw new Exception("輸入的牌組有人用囉!");
            }
        }
        //最後檢查要認領的玩家是不是有相同的牌組名稱
        List<DeckList> tempDecklist=tempplayer.get().getDeckLists();//拿查詢到的決鬥者，獲取其牌組清單
        for (DeckList dl:tempDecklist) {//尋訪牌組清單
            if(dl.getDeckname().equals(input.getDeckname())){//比較是否有跟認領的牌組名稱一樣
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


}
