package danny.yugioh.service;

import danny.yugioh.request.AddDuelDeckRequest;
import danny.yugioh.request.AddPlayerRequest;
import danny.yugioh.request.ChangeDuelistArea;
import danny.yugioh.request.NewDeckOwner;

public interface IPlayerService {
    String newDuelist(AddPlayerRequest input);
    String newDuelDeck(AddDuelDeckRequest input) throws Exception;
    String changeArea(ChangeDuelistArea input) throws Exception;
    String newDeckOwner(NewDeckOwner input) throws Exception;
}
