package danny.yugioh.controller;

import danny.yugioh.entity.Player;
import danny.yugioh.repository.IGameMatchRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.GamePlayerRequest;
import danny.yugioh.service.IGameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GameMatchController {
    @Autowired
    IGameMatchService gameMatchService;
    @Autowired
    IGameMatchRepository gameMatchRepository;
    @Autowired
    IPlayerRepository playerRepository;
    //賽事與玩家建立關聯
    @PutMapping(value = "joinGame")
    public java.lang.String joinGame(@RequestBody GamePlayerRequest input)throws Exception{
        return gameMatchService.joinGame(input);
    }

    //========用用看 Stream 猜選並搜尋符合條件的結果
    @GetMapping(value = "findMatchGamePlayer")
    public java.lang.String findMatchGamePlayer(Integer input)throws Exception{
        //我要找台南2001店家賽的參賽選手
        Optional<Player> byId = playerRepository.findById(input);
        Player player = byId.get();
        List<java.lang.String> gameMatches = gameMatchRepository.gofingGameMatches(player);
//        List<GameMatch> collect = gameMatches.stream().filter(name -> !"台南2001店家賽".equals(name)).collect(Collectors.toList());
        java.lang.String s = gameMatches.get(0);
        return s;
    }


    //找參加指定賽事的所有玩家
    @GetMapping(value = "compititionPlayer")
    public String findCompititionPlayer(Integer gameName) throws Exception{
        return gameMatchService.findCompititionPlayer(gameName);
    }
}
