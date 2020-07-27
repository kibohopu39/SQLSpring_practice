package danny.yugioh.service;

import danny.yugioh.entity.DeckList;
import danny.yugioh.request.ChangeDeckNameRequest;
import danny.yugioh.request.DeckNamePlayerRequest;
import danny.yugioh.request.PlayerAndDeckRequest;

public interface IDeckListService {
    String changeDeckName(ChangeDeckNameRequest input)throws Exception;
    String changeDeckOwner(DeckNamePlayerRequest input)throws Exception;
    void changeDeckName(PlayerAndDeckRequest input, DeckList deckList);
}
