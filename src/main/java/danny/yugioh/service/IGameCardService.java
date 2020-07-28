package danny.yugioh.service;

import danny.yugioh.entity.Player;
import danny.yugioh.request.*;
import danny.yugioh.response.NewDeckCardsResponse;

import java.util.List;

public interface IGameCardService {
    java.lang.String newCard(NewCardUseRequest input) throws Exception;
    java.lang.String newCardpic(Long data)throws Exception;
    NewDeckCardsResponse newDeckCards(NewDeckCardsRequest input)throws Exception;
    java.lang.String changeDeckListCard(DeckCardsDeckNamePlayerRequest input) throws Exception;
    java.lang.String newNoPlayerDeck(java.lang.String input);
    java.lang.String deleteDeckPlayer(DeletePlayerDeckRequest input)throws Exception;
    java.lang.String deleteDeckCard(DeckCardsDeckNamePlayerRequest input) throws Exception;
    List<Player> queryDeckPlayer(java.lang.String deckname)throws Exception;
    void queryDeckCard(List<java.lang.String> Cards) throws Exception;
}
