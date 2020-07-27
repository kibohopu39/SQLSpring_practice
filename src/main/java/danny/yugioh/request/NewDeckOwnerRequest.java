package danny.yugioh.request;

import javax.validation.constraints.NotEmpty;

public class NewDeckOwnerRequest {

    int playerId;
    @NotEmpty
    String deckname;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
