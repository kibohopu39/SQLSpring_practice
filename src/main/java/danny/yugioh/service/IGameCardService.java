package danny.yugioh.service;

import danny.yugioh.request.AddCardRequest;
import danny.yugioh.request.AddDeckCards;
import danny.yugioh.request.ChangeDeckListCard;

public interface IGameCardService {
    String newCard(AddCardRequest input) throws Exception;
    String changeDeckListCard(ChangeDeckListCard input) throws Exception;
    String newNoPlayerDeck(String input);
    String AddDeckCards(AddDeckCards input)throws Exception;
}
