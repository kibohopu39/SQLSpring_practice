package danny.yugioh.service;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.Player;
import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCardsRequest;
import danny.yugioh.request.DeckCardsDeckNamePlayerRequest;
import danny.yugioh.request.DeckNamePlayerRequest;

import java.util.List;

public interface IGameCardService {
    String newCard(AddCardRequest input) throws Exception;
    String changeDeckListCard(DeckCardsDeckNamePlayerRequest input) throws Exception;
    String newNoPlayerDeck(String input);
    String AddDeckCards(AddDeckCardsRequest input)throws Exception;
    String deleteDeckPlayer(DeckNamePlayerRequest input)throws Exception;
    String deleteDeckCard( DeckCardsDeckNamePlayerRequest input) throws Exception;
    List<Player> queryDeckPlayer(String deckname)throws Exception;
    void queryDeckCard(List<String> Cards) throws Exception;
}
