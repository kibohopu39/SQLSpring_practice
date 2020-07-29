package danny.yugioh.service;

import danny.yugioh.request.GamePlayerRequest;

public interface IGameMatchService {
    String joinGame(GamePlayerRequest input)throws Exception;
    String findCompititionPlayer(Integer gameName) throws Exception;

}
