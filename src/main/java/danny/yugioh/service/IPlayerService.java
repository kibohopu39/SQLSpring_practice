package danny.yugioh.service;

import danny.yugioh.entity.Player;
import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeckResponse;
import danny.yugioh.response.PlayerNameResponse;

public interface IPlayerService {
    String newDuelist(AddPlayerRequest input);

    String newDuelDeck(AddDuelDeckRequest input) throws Exception;

    String changeArea(ChangeDuelistAreaRequest input) throws Exception;

    String newDeckOwner(NewDeckOwnerRequest input) throws Exception;

    String deletePlayer(int playerId) throws Exception;

    String deletePlayerDeck(DeckNamePlayerRequest input) throws Exception;

    PlayerNameResponse queryPlayer(String gender);

    PlayerDeckResponse queryPlayerDeck(DeckNamePlayerRequest input) throws Exception;

    int changePlayerAndDeck(PlayerAndDeckRequest input) throws Exception;
}
