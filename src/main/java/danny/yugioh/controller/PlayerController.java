package danny.yugioh.controller;

import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeck;
import danny.yugioh.response.PlayerName;
import danny.yugioh.service.IDeckListService;
import danny.yugioh.service.IPlayerService;
import danny.yugioh.service.IGameCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PlayerController {
    //前端進來什麼要求，如想要知道某玩家使用了什麼牌
    @Autowired
    IPlayerService playerService;
    @Autowired
    IGameCardService gameCardService;
    @Autowired
    IDeckListService deckListService;

    //一對一
    //新增一個新的決鬥者(問題:可以重複人名新增)
    @PostMapping(value = "newduelist")
    public String newDuelist(@RequestBody @Valid AddPlayerRequest input){
        return playerService.newDuelist(input);
    }
    //一對多
    //新增決鬥者的牌組清單(牌組名字不能重複新增，一次加一副牌組名稱)
    @PostMapping(value = "newdueldeck")
    public String newDeck(@RequestBody @Valid AddDuelDeckRequest input) throws Exception {
        return playerService.newDuelDeck(input);
    }
    //多對一
    //新增牌組的持有者?(這種情況很怪，如果玩家在原本的資料庫裡的話，就是建立關聯)
    //首先去新增一副沒有持有者的牌組----->到GameCardController
    @PostMapping(value = "newDeckOwner")
    public String newDeckOwner(@RequestBody @Valid NewDeckOwner input) throws Exception{
        return playerService.newDeckOwner(input);
}
    //多對多
    //新增牌組裡面的卡--->到GameCardController
    //多對多
    //新增一堆卡的牌組名稱(這啥?)--->沒這種情況吧~


    // 一對一
    // 修改決鬥者的資訊(目前只有放Area)
    @PutMapping(value = "changeArea")
    public String changeArea(@RequestBody ChangeDuelistArea input)throws Exception{
        return playerService.changeArea(input);
    }

    // 一對多
    // 修改決鬥者的牌組名稱
    @PutMapping(value = "newDeckList")
    public String newDeckName(@RequestBody ChangeDeckName input) throws Exception{
        return deckListService.changeDeckName(input);
    }

    //多對一
    //修改牌組的持有人
    @PutMapping()
    public String changeDeckOwner(DeckNamePlayerRequest input)throws Exception{
        return deckListService.changeDeckOwner(input);
    }


    //多對多
    //修改牌組的卡--->到GameCardController
    //多對多
    //修改牌組的名稱...這個跟一對多情況一樣啊


    //一對一
    //刪除玩家
    @DeleteMapping(value = "deleteplayer")
    public String deletePlayer(int playId) throws Exception {
        return playerService.deletePlayer(playId);
    }

    //一對多
    //刪除某個牌組
    @DeleteMapping(value = "deleteplayerDeck")
    public String deletePlayerDeck(@RequestBody DeckNamePlayerRequest input)throws Exception{
        return playerService.deletePlayerDeck(input);
    }


    //多對多
    //刪除牌組的卡--->到GameCardController
    //多對多
    //刪除牌組?阿這不是跟一對多一樣嗎?

    //一對一
    //查詢某個玩家，或是各種由玩家資料搜尋的類型，此例為性別
    @GetMapping(value = "queryPlayer")
    public PlayerName queryPlayer(String gender){
        return playerService.queryPlayer(gender);
    }
    //一對多
    //查詢玩家某個牌組
    @GetMapping(value = "queryPlayerDeck")
    public PlayerDeck queryPlayerDeck(@RequestBody DeckNamePlayerRequest input) throws Exception{
        return playerService.queryPlayerDeck(input);
    }


    //多對多
    //查詢牌組的卡--->到GameCardController
    //多對多
    //查詢某張卡被放到那些牌組裡

}
