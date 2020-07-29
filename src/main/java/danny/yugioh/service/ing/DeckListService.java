package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.GameCardUse;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IGameCardRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.ChangeDeckNameRequest;
import danny.yugioh.request.ChangeDeckPlayerRequest;
import danny.yugioh.request.PlayerAndDeckRequest;
import danny.yugioh.service.IDeckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DeckListService implements IDeckListService {
    @Autowired
    IDeckListRepository deckListRepository;
    @Autowired
    IPlayerRepository playerRepository;
    @Autowired
    IGameCardRepository gameCardRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String changeDeckName(ChangeDeckNameRequest input) throws Exception {
        //看玩家名在不在
        Player player = cheackPlayer(input.getPlayId());
        List<DeckList> deckLists = player.getDeckLists();
        for (DeckList i : deckLists) {//尋訪玩家的牌組清單
            if (i.getDeckname().equals(input.getTarget())) {//如果是目標牌組
                i.setDeckname(input.getDeckname());//更改名字
                player.setDeckLists(deckLists);  //修改
                playerRepository.save(player);
                return "修改牌組名稱成功";
            }
        }
        return "玩家牌組清單內沒有指定的對象!";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String changeDeckOwner(ChangeDeckPlayerRequest input) throws Exception {
        //修改前後不能一樣
        if (input.getNewPlayerId()==input.getOldPlayerId()){
            throw new Exception("修改後玩家名稱相同，請修改!");
        }
        //先看修改前後玩家有沒有存在
        Player player = cheackPlayer(input.getOldPlayerId());//想取消關聯的玩家
        Player player1 = cheackPlayer(input.getNewPlayerId());//想轉移的對象
        //玩家有這些牌組名稱?
        DeckList olddeckList = cheackPlayerDeck(input.getDeckname(), player);
        if (cheackPlayerDeck2(input.getDeckname(),player1)){
            olddeckList.setDuelist(null);
            olddeckList.setDuelist(player1);
            deckListRepository.save(olddeckList);
        }
        return "修改牌組名稱成功!";
    }

    @Override
    public void changeDeckName(PlayerAndDeckRequest input, DeckList deckList) {
        //直接改指定玩家的卡包名稱，因為前面已經都檢查過了
        deckList.setDeckname(input.getNewdeckname());
//        Player duelist = deckList.getDuelist();
//        playerRepository.save(duelist);
        deckListRepository.save(deckList);
    }

    @Override
    public List<HashMap<String,String>> queryDeckCard(String card) throws Exception {
        //輸入的卡牌是合理的嗎?
        HashMap<String,String> playerandDeck=new HashMap<>();
        List<HashMap<String,String>> response=new ArrayList<>();
        if (cheackCard(card)){
            Optional<GameCardUse> card1 = gameCardRepository.findCard(card);
            List<DeckList> deckListByCardname = deckListRepository.findDeckListByCardname(card1.get());
            for (DeckList d:deckListByCardname
                 ) {
                playerandDeck.put(d.getDuelist().getName(),d.getDeckname());
                response.add(playerandDeck);
            }
        }
        return response;
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
    private DeckList cheackPlayerDeck(java.lang.String deckname, Player player) throws Exception {
        List<DeckList> deckLists = player.getDeckLists();//該玩家的牌組清單列表
        for (DeckList i : deckLists) {//尋訪清單列表
            if (i.getDeckname().equals(deckname)) {//有對應的名字就傳回
                return i;
            }
        }
        throw new Exception("沒有這個牌組!");
    }
    //方法3，判斷玩家牌組有無這個牌組沒有的話回傳true，表示可以新增
    private Boolean cheackPlayerDeck2(java.lang.String deckname, Player player){
        List<DeckList> deckLists = player.getDeckLists();//該玩家的牌組清單列表
        for (DeckList i : deckLists) {//尋訪清單列表
            if (i.getDeckname().equals(deckname)) {//有對應的名字就傳回
                return false;
            }
        }
        return true;
    }
    //方法4，判斷卡牌是否合理
    private Boolean cheackCard(String cardname)throws Exception{
        Optional<GameCardUse> card = gameCardRepository.findCard(cardname);
        if (!card.isPresent()) {
            throw new Exception("沒有這張卡");
        }
        return true;
    }
}
