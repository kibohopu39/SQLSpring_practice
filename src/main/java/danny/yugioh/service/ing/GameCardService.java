package danny.yugioh.service.ing;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.GameCardUse;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IDeckListRepository;
import danny.yugioh.repository.IGameCardRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.*;
import danny.yugioh.response.NewDeckCardsResponse;
import danny.yugioh.service.IGameCardService;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String newCard(NewCardUseRequest input) throws Exception {
        //卡片名稱沒有重複
        //所以先看這張牌有沒有已經在牌庫池裡了
        Optional<GameCardUse> tmpCard = gameCardRepository.findCard(input.getCardname());
        if (tmpCard.isPresent()) {
            throw new Exception("新增的卡已經在卡池中了");
        }
        GameCardUse gameCardUse = new GameCardUse();
        gameCardUse.setCardname(input.getCardname());
        gameCardUse.setmATK(input.getAtk());
        gameCardUse.setmDEF(input.getDef());
        gameCardUse.setmLevel(input.getLevel());
        gameCardUse.setmAttribute(input.getAttribute());
        gameCardRepository.save(gameCardUse);
        return "新增卡片成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String newNoPlayerDeck(java.lang.String input) {
        //單純新增一個孤兒牌組
        List<DeckList> all = deckListRepository.findAll();
        DeckList newdecklist = new DeckList();
        newdecklist.setDeckname(input);
        deckListRepository.save(newdecklist);
        return "新增孤兒牌組成功!請幫它找個主人~";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewDeckCardsResponse newDeckCards(NewDeckCardsRequest input) throws Exception {
        //確認要新增的對象在不在，三連
        Player player = cheackPlayer(input.getPlayerId());//確認玩家
        DeckList deckList = cheackPlayerDeck(input.getDeckname(), player);//確認牌組名稱
        List<java.lang.String> cardsname = input.getCardsname();
        List<GameCardUse> cardsUseList = correctCardListFilter(cardsname);//篩選出有效的加入清單，並轉成卡片物件
        //依照遊戲王規則，牌組相同卡片不得放超過三張，所以要看看牌組裡的卡
        List<GameCardUse> gameCardUseList = deckList.getGameCardUseList();//該牌組目前的卡
        NewDeckCardsResponse newDeckCardsResponse=new NewDeckCardsResponse();//回傳的東西
        List<java.lang.String> unmatchCards=new ArrayList<>();

        for (GameCardUse i : cardsUseList) {//對那些要加入的卡尋訪
            int cnum=Collections.frequency(gameCardUseList, i);
            if (cnum >= 3) {
                unmatchCards.add(i.getCardname());
            }else if(cnum<3){
            gameCardUseList.add(i);}
        }
        deckList.setGameCardUseList(gameCardUseList);
        deckListRepository.save(deckList);
        if (unmatchCards.size()!=0){
            newDeckCardsResponse.setCardsname(unmatchCards);
            newDeckCardsResponse.setMessge("有卡片超過可用張數!");
            return newDeckCardsResponse;
        }else{
            newDeckCardsResponse.setMessge("新增卡片到牌組成功!");
            return newDeckCardsResponse;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String newCardpic(Long data) throws Exception {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String changeDeckListCard(DeckCardsDeckNamePlayerRequest input) throws Exception {
        //一樣先確認人，再確認牌組名，最後確認卡
        Player player = cheackPlayer(input.getDuelistId());
        DeckList targetdeckList = cheackPlayerDeck(input.getDeckname(), player);//查該牌組名，因為我需要從獲取符合的該列，由關聯去獲得該牌組的內容
        List<GameCardUse> gameCardUseList = targetdeckList.getGameCardUseList();//獲得目前該牌組內容
        //檢查想要加的卡是不是資料庫裡有的卡
        List<GameCardUse> addGameCard = correctCardListFilter(input.getCardsList());//得到初步篩選後能夠加的卡

        //依照遊戲王規則，牌組相同卡片不得放超過三張，所以能加的卡在加入牌組後，如果超過三張就不能再加
        for (GameCardUse card : addGameCard) {
            if (Collections.frequency(gameCardUseList, card) < 3) {
                gameCardUseList.add(card);
            }
        }

        return "卡片修改成功";//效果是加進來，其實這也算是修改，因為我們不會去指定把哪一張換成另一張，所以可以考慮把上面的新增跟這裡的修改合併
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String deleteDeckPlayer(DeletePlayerDeckRequest input) throws Exception {
        //這個部分要注意，只是要刪除牌組跟玩家的關聯，不能刪除掉該玩家的資料
        //玩家在不?
        Player player = cheackPlayer(input.getPlayerId());
        //牌組在不?牌組要先跟玩家斷關係
        List<java.lang.String> deckNameList = input.getDeckNameList();
        for (java.lang.String deckname : deckNameList) {
            DeckList deckList = cheackPlayerDeck(deckname, player);//傳出玩家符合要刪除的牌組
            deckList.setDuelist(null);//跟玩家斷關聯....
            deckListRepository.save(deckList);//儲存
        }
        return "刪除完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public java.lang.String deleteDeckCard(DeckCardsDeckNamePlayerRequest input) throws Exception {
        //一樣先確認人，再確認牌組名，最後確認卡
        Player player = cheackPlayer(input.getDuelistId());
        DeckList targetdeckList = cheackPlayerDeck(input.getDeckname(), player);//查該牌組名，因為我需要從獲取符合的該列，由關聯去獲得該牌組的內容
        List<GameCardUse> gameCardUseList = targetdeckList.getGameCardUseList();//獲得目前該牌組內容
        //檢查想要刪的卡是不是資料庫裡有的卡
        List<GameCardUse> addGameCard = correctCardListFilter(input.getCardsList());//得到初步篩選後能夠刪的卡

        //刪除跟牌組的關聯
        for (GameCardUse card : addGameCard) {
            if (gameCardUseList.contains(card)) {//假設牌組有那張卡
                //把那張卡刪掉就好了
                gameCardUseList.remove(card);
            }
        }
        targetdeckList.setGameCardUseList(gameCardUseList);
        deckListRepository.save(targetdeckList);

        return "刪除牌組卡片成功";
    }

    @Override
    public List<String> queryDeckPlayer(String deckname) throws Exception {
        //輸入的牌組名稱是否存在
        List<DeckList> deckLists = checkDeckName(deckname);
        List<String> names=new ArrayList<>();
        for (DeckList d : deckLists) {
           names.add(d.getDuelist().getName());
        }
        return names;
    }

    @Override
    public void queryDeckCard(List<java.lang.String> Cards) throws Exception {
        //先看看這些卡是否合法
        List<GameCardUse> cardUseList = correctCardListFilter(Cards);
        List<DeckList> deckLists=new ArrayList<>();
        for (GameCardUse card:cardUseList) {
            List<DeckList> deckList = card.getDeckLists();

        }

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

    //方法3，篩選出有效的加入卡
    private List<GameCardUse> correctCardListFilter(List<java.lang.String> inputCardname) {
        List<GameCardUse> addGameCard = new ArrayList<>();//符合可以加的卡片
        for (java.lang.String cardname : inputCardname) {
            Optional<GameCardUse> card = gameCardRepository.findCard(cardname);
            if (card.isPresent()) {
                addGameCard.add(card.get());
            }
        }
        return addGameCard;
    }
    //方法4，判斷有無這副牌組
    private List<DeckList> checkDeckName(String inputDeckname) throws Exception{
        List<DeckList> deckLists = deckListRepository.findallByname(inputDeckname);//可能會獲得不同人的相同牌組名稱
        if (deckLists.isEmpty()){
            throw new Exception("查無此牌組");
        }
        return deckLists;
    }
}
