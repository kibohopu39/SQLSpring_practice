package danny.yugioh.request;

import java.util.List;

public class DeletePlayerDeckRequest {
    List<String> DeckNameList;
    Integer PlayerId;

    public List<String> getDeckNameList() {
        return DeckNameList;
    }

    public void setDeckNameList(List<String> deckNameList) {
        DeckNameList = deckNameList;
    }

    public Integer getPlayerId() {
        return PlayerId;
    }

    public void setPlayerId(Integer playerId) {
        PlayerId = playerId;
    }
}
