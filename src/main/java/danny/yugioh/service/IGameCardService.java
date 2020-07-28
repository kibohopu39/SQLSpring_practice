package danny.yugioh.service;

import danny.yugioh.entity.Player;
import danny.yugioh.request.*;
import danny.yugioh.response.NewDeckCardsResponse;

import java.util.List;

public interface IGameCardService {
    String newCard(NewCardUseRequest input) throws Exception;
    NewDeckCardsResponse newDeckCards(NewDeckCardsRequest input)throws Exception;
    String changeDeckListCard(DeckCardsDeckNamePlayerRequest input) throws Exception;
    String newNoPlayerDeck(String input);
    String deleteDeckPlayer(DeckNamePlayerRequest input)throws Exception;
    String deleteDeckCard( DeckCardsDeckNamePlayerRequest input) throws Exception;
    List<Player> queryDeckPlayer(String deckname)throws Exception;
    void queryDeckCard(List<String> Cards) throws Exception;
}
