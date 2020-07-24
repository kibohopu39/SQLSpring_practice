package danny.yugioh.controller;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.GameCardUse;
import danny.yugioh.entity.Player;
import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCards;
import danny.yugioh.request.DeckCardsDeckNamePlayerRequest;
import danny.yugioh.request.DeckNamePlayerRequest;
import danny.yugioh.service.IGameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameCardController {
    @Autowired
    IGameCardService gameCardService;

    //一對一
    //新增可用卡片池
    @PostMapping(value = "newcard")
    public String newCard(@RequestBody @Valid AddCardRequest input) throws Exception{
        return gameCardService.newCard(input);
    }
    //不屬於任何分類
    //單純新增沒有持有者的牌組(你可以新增一個跟別人重複名稱的牌組)
    @PostMapping(value = "newNoPlayerDeck")
    public String newNoPlayerDeck(@RequestBody String input){
        return gameCardService.newNoPlayerDeck(input);
    }

    //多對一
    //刪除牌組的持有人
    @PutMapping(value = "deleteDeckplayer")
    public String deleteDeckPlayer(@RequestBody DeckNamePlayerRequest input)throws Exception{
        return gameCardService.deleteDeckPlayer(input);
    }
    //多對一
    //查詢某個牌組的擁有者
    @GetMapping(value = "queryDeckPlayer")
    public List<Player> queryDeckPlayer(@RequestBody String deckname)throws Exception{
        return gameCardService.queryDeckPlayer(deckname);
    }


    //多對多
    //新增牌組裡面的卡
    @PostMapping(value = "newDeckCard")
    public String newDeckCards(@RequestBody @Valid AddDeckCards input)throws Exception{
        return gameCardService.AddDeckCards(input);
    }
    //多對多
    //修改指定決鬥者牌組的卡片--->請問你跟上面那位不是一樣嗎?
    @PutMapping(value = "changedeckcard")
    public String changeDeckCard(@RequestBody @Valid DeckCardsDeckNamePlayerRequest input) throws Exception{
        return gameCardService.changeDeckListCard(input);
    }

    //多對多
    //刪除牌組的卡
    @PutMapping(value = "deleteDeckCard")
    public String deleteDeckCard(@RequestBody @Valid DeckCardsDeckNamePlayerRequest input)throws Exception{
        return gameCardService.deleteDeckCard(input);
    }

    //多對多
    //查詢有放輸入卡片的所有牌組
    @GetMapping(value = "queryDeckCard")
    public List<DeckList> queryDeckCard(@RequestBody List<String> Cards) throws Exception{
        return gameCardService.queryDeckCard(Cards);

    }
    //多對多
    //查詢某張卡被放到那些牌組裡

}
