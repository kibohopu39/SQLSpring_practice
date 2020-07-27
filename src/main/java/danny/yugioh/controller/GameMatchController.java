package danny.yugioh.controller;

import danny.yugioh.request.GamePlayerRequest;
import danny.yugioh.service.IGameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameMatchController {
    @Autowired
    IGameMatchService gameMatchService;
    //賽事與玩家建立關聯
    @PutMapping(value = "joinGame")
    public String joinGame(@RequestBody GamePlayerRequest input)throws Exception{
        return gameMatchService.joinGame(input);
    }
}
