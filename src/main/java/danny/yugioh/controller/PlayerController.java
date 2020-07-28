package danny.yugioh.controller;

import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeckResponse;
import danny.yugioh.response.PlayerNameResponse;
import danny.yugioh.service.IDeckListService;
import danny.yugioh.service.IGameCardService;
import danny.yugioh.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {
    @Autowired
    IPlayerService playerService;
    @Autowired
    IGameCardService gameCardService;
    @Autowired
    IDeckListService deckListService;

    //一對一
    //新增一個新的決鬥者(問題:可以重複人名新增，不能確保是不是不同人)
    @PostMapping(value = "newplayer")
    public String newDuelist(@RequestBody @Valid NewPlayerRequest input){ return playerService.newDuelist(input); }
    //一對多
    //新增決鬥者的牌組清單(牌組名字不能重複新增，一次加一副牌組名稱)
    @PostMapping(value = "newdueldecks")
    public String newDeck(@RequestBody @Valid AddDuelDeckRequest input) throws Exception { return playerService.newDuelDeck(input);    }
    //多對一
    //新增牌組的持有者?(這種情況很怪，如果玩家在原本的資料庫裡的話，就是建立牌組跟玩家的關聯)
    //首先去新增一副沒有持有者的牌組----->到 GameCardController
    @PostMapping(value = "newDeckOwner")
    public String newDeckOwner(@RequestBody @Valid NewDeckOwnerRequest input) throws Exception{ return playerService.newDeckOwner(input);}

    //多對多
    //新增牌組裡面的卡--->到GameCardController
    //多對多
    //新增一堆卡的牌組名稱(這啥?為孤兒牌組命名?)---> 沒這種情況吧~

    // 一對一
    // 修改決鬥者的資訊
    @PutMapping(value = "changePlayer")
    public String changeArea(@RequestBody ChangePlayerRequest input)throws Exception{ return playerService.changePlayer(input);    }
    // 一對多
    // 修改決鬥者的牌組名稱
    @PutMapping(value = "changeDeckList")
    public String changeDeckName(@RequestBody ChangeDeckNameRequest input) throws Exception{ return deckListService.changeDeckName(input);    }

    //多對多
    //修改牌組的卡--->到 GameCardController
    //多對多
    //修改牌組的名稱...這個跟一對多情況一樣啊

    //一對一
    //刪除玩家
    @DeleteMapping(value = "deleteplayer")
    public String deletePlayer(Integer playId) throws Exception { return playerService.deletePlayer(playId);    }
    //一對多
    //刪除玩家某個牌組
    @DeleteMapping(value = "deleteplayerDeck")
    public String deletePlayerDeck(@RequestBody DeletePlayerDeckRequest input)throws Exception{ return playerService.deletePlayerDeck(input);    }

    //多對多
    //刪除牌組的卡--->到GameCardController
    //多對多
    //刪除牌組?阿這不是跟一對多一樣嗎?

    //一對一
    //查詢某個玩家，或是各種由玩家資料搜尋的類型，此例為性別
    @GetMapping(value = "queryPlayer")
    public PlayerNameResponse queryPlayer(String gender){
        return playerService.queryPlayer(gender);
    }
    //一對多
    //查詢玩家某個牌組
    @GetMapping(value = "queryPlayerDeck")
    public PlayerDeckResponse queryPlayerDeck(@RequestBody QueryPlayerDeckRequest input) throws Exception{
        return playerService.queryPlayerDeck(input);
    }

    //多對多
    //查詢牌組的卡--->到GameCardController
    //多對多
    //查詢某張卡被放到那些牌組裡
    //===============================================7/27==========================================================
    //任務: 寫一個同時修改玩家名字與卡包的方法
    //玩家存玩家，卡包存卡包，因此需要寫一個卡包存卡包的方法給這個方法引用
    @PutMapping(value = "changePlayerAndDeck")
    public int changePlayerAndDeck(@RequestBody PlayerAndDeckRequest input) throws Exception{
        return playerService.changePlayerAndDeck(input);
    }
    //=========用用看 Stream
    @GetMapping(value = "hey")
    public List<String> testStream(@RequestBody List<String> input){
        List<String> filtered = input.stream().filter(string -> !string.isBlank()).collect(Collectors.toList());

        return filtered;
    }

}
