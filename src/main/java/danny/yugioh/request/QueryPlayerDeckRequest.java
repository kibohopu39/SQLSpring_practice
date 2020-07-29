package danny.yugioh.request;

import java.util.List;

public class QueryPlayerDeckRequest {
    List<String> deckNameList;
    Integer playerId;

    public List<String> getDeckNameList() {
        return deckNameList;
    }

    public void setDeckNameList(List<String> deckNameList) {
        this.deckNameList = deckNameList;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
