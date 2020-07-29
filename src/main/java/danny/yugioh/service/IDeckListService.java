package danny.yugioh.service;

import danny.yugioh.entity.DeckList;
import danny.yugioh.request.ChangeDeckNameRequest;
import danny.yugioh.request.ChangeDeckPlayerRequest;
import danny.yugioh.request.PlayerAndDeckRequest;

import java.util.HashMap;
import java.util.List;

public interface IDeckListService {
    String changeDeckName(ChangeDeckNameRequest input)throws Exception;
    String changeDeckOwner(ChangeDeckPlayerRequest input)throws Exception;
    void changeDeckName(PlayerAndDeckRequest input, DeckList deckList);
    List<HashMap<String,String>> queryDeckCard(String card) throws Exception;
}
