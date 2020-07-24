package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.GameCardUse;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IGameCardRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCards;
import danny.yugioh.request.ChangeDeckListCard;
import danny.yugioh.service.IGameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCardService implements IGameCardService {
    @Autowired
    IGameCardRepository gameCardRepository;
    @Autowired
    IDeckListRepository deckListRepository;
    @Autowired
    IPlayerRepository playerRepository;
    @Override
    public String newCard(AddCardRequest input) throws Exception{
        //先看這張牌有沒有已經在牌庫池裡了
        Optional<GameCardUse> tmpCard = gameCardRepository.findCard(input.getCardname());
        if (tmpCard.isPresent()){
            throw new Exception("新增的卡已經在卡池中了");
        }
        GameCardUse gameCardUse=new GameCardUse();
        gameCardUse.setCardname(input.getCardname());
        gameCardUse.setmATK(input.getAtk());
        gameCardUse.setmDEF(input.getDef());
        gameCardUse.setmLevel(input.getLevel());
        gameCardUse.setmAttribute(input.getAttribute());
        gameCardRepository.save(gameCardUse);
        return "新增卡片成功";
    }

    @Override
    public String newNoPlayerDeck(String input) {
        //單純新增一個孤兒牌組
        List<DeckList> all = deckListRepository.findAll();
        DeckList newdecklist=new DeckList();
        newdecklist.setDeckname(input);
        deckListRepository.save(newdecklist);
        return "新增孤兒牌組成功!請幫它找個主人~";
    }

    @Override
    public String AddDeckCards(AddDeckCards input) throws Exception {
        //先確認人，再確認牌組名，最後確認卡
        Player tempplayer=cheackPlayer(input.getPlayerId());
        List<DeckList> finddeckLists = tempplayer.getDeckLists();//該玩家的牌組清單
        DeckList targetdeckList=new DeckList();
        for (DeckList i:finddeckLists) {
            if (i.getDeckname().equals(input.getDeckname())){
                targetdeckList=i;
                break;
            }
            throw new Exception("這個玩家沒這個牌組啦");
        }
        //檢查一下想要加的卡是不是資料庫裡有的卡
        List<String> addcarnameList=input.getCards();//想加的卡片
        List<GameCardUse> addGameCard =new ArrayList<>();//符合可以加的卡片
        for (String cardname:addcarnameList) {
            Optional<GameCardUse> card = gameCardRepository.findCard(cardname);
            if (card.isPresent()){
                addGameCard.add(card.get());
            }
        }
        //依照遊戲王規則，牌組相同卡片不得放超過三張，所以要看看牌組裡的卡
        List<GameCardUse> cardUseList=targetdeckList.getGameCardUseList();//該牌組目前的卡
        for (GameCardUse i:addGameCard) {
            if(Collections.frequency(cardUseList,i)<3) {
                cardUseList.add(i);
            }
        }

        return "新增卡片到牌組成功";
    }



    @Override
    public String changeDeckListCard(ChangeDeckListCard input) throws Exception {
        //一樣先確認人，再確認牌組名，最後確認卡
        Player player=cheackPlayer(input.getDuelistId());
        deckListRepository.findDeckListByDeckname();//查該牌組名是什麼Id，因為我需要從id獲取那一列，進而獲得該牌組的內容
        input.getDeckname();

        //檢查一下想要加的卡是不是資料庫裡有的卡
        List<String> addcarnameList=input.getCardsList();//想加的卡片
        List<GameCardUse> addGameCard =new ArrayList<>();//符合可以加的卡片
        for (String cardname:addcarnameList) {
            Optional<GameCardUse> card = gameCardRepository.findCard(cardname);
            if (card.isPresent()){
                addGameCard.add(card.get());
            }
        }
        //依照遊戲王規則，牌組相同卡片不得放超過三張，所以要看看牌組裡的卡
        List<DeckList> cardDeckList=player.getDeckLists();//該玩家目前的牌組清單



        for (GameCardUse i:addGameCard) {
            if(Collections.frequency(cardUseList,i)<3) {
                cardUseList.add(i);
            }
        }


        return null;
    }
    //方法1，判斷有無這個玩家
    private Player cheackPlayer(Integer id) throws Exception {
        Optional<Player> player = playerRepository.findById(id);
        if (!player.isPresent()){
            throw new Exception("沒有這個決鬥者");
        }
        return player.get();
    }


}
