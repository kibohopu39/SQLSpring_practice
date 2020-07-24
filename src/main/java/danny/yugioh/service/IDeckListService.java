package danny.yugioh.service;

import danny.yugioh.request.ChangeDeckName;
import danny.yugioh.request.ChangeDeckOwner;

public interface IDeckListService {
    String changeDeckName(ChangeDeckName input)throws Exception;
    String changeDeckOwner(ChangeDeckOwner input)throws Exception;
}
