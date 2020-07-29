package danny.yugioh.service.ing;

import danny.yugioh.entity.GameMatch;
import danny.yugioh.entity.Player;
import danny.yugioh.repository.IGameMatchRepository;
import danny.yugioh.repository.IPlayerRepository;
import danny.yugioh.request.GamePlayerRequest;
import danny.yugioh.service.IGameMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameMatchService implements IGameMatchService {
    @Autowired
    IPlayerRepository playerRepository;
    @Autowired
    IGameMatchRepository gameMatchRepository;
    @Override
    public java.lang.String joinGame(GamePlayerRequest input) throws Exception {
        //檢查玩家在不在，
        Player player = cheackPlayer(input.getPlayerId());
        GameMatch gameMatch = cheackGameMatch(input.getGame());
        List<Player> duelists = gameMatch.getDuelists();
        duelists.add(player);
        gameMatch.setDuelists(duelists);
        gameMatchRepository.save(gameMatch);

        return "參加成功";
    }

    @Override
    public String findCompititionPlayer(Integer gameName) throws Exception {
        //先檢查賽事在不在
        GameMatch gameMatch = cheackGameMatch(gameName);
        List<Player> duelists = gameMatch.getDuelists();//獲取參加的玩家
        String tmp="";
        for (Player p:duelists) {
            String name = p.getName();
            tmp+=name+",";
        }

        return tmp;
    }


    //方法1，判斷有無這個玩家
    private Player cheackPlayer(Integer id) throws Exception {
        Optional<Player> player = playerRepository.findById(id);
        if (!player.isPresent()) {
            throw new Exception("沒有這個決鬥者");
        }
        return player.get();
    }
    //方法2，判斷有無這個賽事
    private GameMatch cheackGameMatch(String gameMatchName) throws Exception {
        Optional<GameMatch> gameMatch = gameMatchRepository.findallByname(gameMatchName);
        if (!gameMatch.isPresent()) {
            throw new Exception("沒有這個賽事");
        }
        return gameMatch.get();
    }
    //方法3，判斷有無這個賽事2
    private GameMatch cheackGameMatch(Integer gameId) throws Exception {
        Optional<GameMatch> gameMatch = gameMatchRepository.findById(gameId);
        if (!gameMatch.isPresent()) {
            throw new Exception("沒有這個賽事");
        }
        return gameMatch.get();
    }
}
