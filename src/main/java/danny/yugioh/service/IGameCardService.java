package danny.yugioh.service;

import danny.yugioh.entity.DeckList;
import danny.yugioh.entity.Player;
import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCards;
import danny.yugioh.request.DeckCardsDeckNamePlayerRequest;
import danny.yugioh.request.DeckNamePlayerRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IGameCardService {
    String newCard(AddCardRequest input) throws Exception;
    String changeDeckListCard(DeckCardsDeckNamePlayerRequest input) throws Exception;
    String newNoPlayerDeck(String input);
    String AddDeckCards(AddDeckCards input)throws Exception;
    String deleteDeckPlayer(DeckNamePlayerRequest input)throws Exception;
    String deleteDeckCard( DeckCardsDeckNamePlayerRequest input) throws Exception;
    List<Player> queryDeckPlayer(String deckname)throws Exception;
    List<DeckList> queryDeckCard(List<String> Cards) throws Exception;
}
