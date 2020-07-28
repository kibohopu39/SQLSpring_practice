package danny.yugioh.controller;

import danny.yugioh.entity.Player;
import danny.yugioh.request.*;
import danny.yugioh.response.NewDeckCardsResponse;
import danny.yugioh.service.IDeckListService;
import danny.yugioh.service.IGameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameCardController {
    @Autowired
    IGameCardService gameCardService;
    @Autowired
    IDeckListService deckListService;
    //新增可用卡片池
    @PostMapping(value = "newcard")
    public java.lang.String newCard(@RequestBody @Valid NewCardUseRequest input) throws Exception{ return gameCardService.newCard(input);    }
    //新增卡圖
    //把卡圖傳進來，卡圖的格式?
    @PostMapping(value = "newCardpic")
    public java.lang.String newCardpic(Long data) throws Exception{ return gameCardService.newCardpic(data);   }
    //單純新增沒有持有者的牌組(你可以新增一個跟別人重複名稱的牌組)
    @PostMapping(value = "newNoPlayerDeck")
    public java.lang.String newNoPlayerDeck(@RequestBody java.lang.String input){
        return gameCardService.newNoPlayerDeck(input);
    }
    //多對一
    //修改牌組的持有人
    @PutMapping(value = "changeDeckOwner")
    public java.lang.String changeDeckOwner(@RequestBody ChangeDeckPlayerRequest input)throws Exception{ return deckListService.changeDeckOwner(input);    }
    //多對多
    //新增指定牌組內的卡片(就是卡片跟牌組建立關聯)
    @PutMapping(value = "newDeckCards")
    public NewDeckCardsResponse newDeckCards(@RequestBody NewDeckCardsRequest input)throws Exception{return gameCardService.newDeckCards(input);    }





    //多對一
    //刪除牌組的持有人
    @PutMapping(value = "deleteDeckplayer")
    public java.lang.String deleteDeckPlayer(@RequestBody DeletePlayerDeckRequest input)throws Exception{
        return gameCardService.deleteDeckPlayer(input);
    }
    //多對一
    //查詢某個牌組的擁有者
    @GetMapping(value = "queryDeckPlayer")
    public List<Player> queryDeckPlayer(@RequestBody java.lang.String deckname)throws Exception{
        return gameCardService.queryDeckPlayer(deckname);
    }

    //多對多
    //修改指定決鬥者牌組的卡片--->請問你跟上面那位不是一樣嗎?
    @PutMapping(value = "changedeckcard")
    public java.lang.String changeDeckCard(@RequestBody @Valid DeckCardsDeckNamePlayerRequest input) throws Exception{
        return gameCardService.changeDeckListCard(input);
    }

    //多對多
    //刪除牌組的卡
    @PutMapping(value = "deleteDeckCard")
    public java.lang.String deleteDeckCard(@RequestBody @Valid DeckCardsDeckNamePlayerRequest input)throws Exception{
        return gameCardService.deleteDeckCard(input);
    }

    //多對多
    //查詢有放輸入卡片的所有牌組
    @GetMapping(value = "queryDeckCard")
    public void queryDeckCard(@RequestBody List<java.lang.String> Cards) throws Exception{

    }
    //多對多
    //查詢某張卡被放到那些牌組裡

}
