package danny.yugioh.service;

import danny.yugioh.request.ChangeDeckName;
import danny.yugioh.request.DeckNamePlayerRequest;

public interface IDeckListService {
    String changeDeckName(ChangeDeckName input)throws Exception;
    String changeDeckOwner(DeckNamePlayerRequest input)throws Exception;
}
