package danny.yugioh.service;

import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeckResponse;
import danny.yugioh.response.PlayerNameResponse;

public interface IPlayerService {
    String newDuelist(NewPlayerRequest input);

    String newDuelDeck(AddDuelDeckRequest input) throws Exception;

    String changePlayer(ChangePlayerRequest input) throws Exception;

    String newDeckOwner(NewDeckOwnerRequest input) throws Exception;

    String deletePlayer(Integer playerId) throws Exception;

    String deletePlayerDeck(DeletePlayerDeckRequest input) throws Exception;

    PlayerNameResponse queryPlayer(String gender);

    PlayerDeckResponse queryPlayerDeck(QueryPlayerDeckRequest input) throws Exception;

    int changePlayerAndDeck(PlayerAndDeckRequest input) throws Exception;
}
