package danny.yugioh.service;

import danny.yugioh.request.*;
import danny.yugioh.response.PlayerDeck;
import danny.yugioh.response.PlayerName;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPlayerService {
    String newDuelist(AddPlayerRequest input);

    String newDuelDeck(AddDuelDeckRequest input) throws Exception;

    String changeArea(ChangeDuelistArea input) throws Exception;

    String newDeckOwner(NewDeckOwner input) throws Exception;

    String deletePlayer(int playerId) throws Exception;

    String deletePlayerDeck(DeckNamePlayerRequest input) throws Exception;

    PlayerName queryPlayer(String gender);

    PlayerDeck queryPlayerDeck(DeckNamePlayerRequest input) throws Exception;
}
