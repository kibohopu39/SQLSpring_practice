package danny.yugioh.controller;

import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCards;
import danny.yugioh.request.ChangeDeckListCard;
import danny.yugioh.service.IGameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    //多對多
    //新增牌組裡面的卡
    @PostMapping(value = "newDeckCard")
    public String newDeckCards(@RequestBody @Valid AddDeckCards input)throws Exception{
        return gameCardService.AddDeckCards(input);
    }
    //多對多
    //修改指定決鬥者牌組的卡片
    @PutMapping(value = "changedeckcard")
    public String changeDeckCard(@RequestBody @Valid ChangeDeckListCard input) throws Exception{
        return gameCardService.changeDeckListCard(input);
    }
}
