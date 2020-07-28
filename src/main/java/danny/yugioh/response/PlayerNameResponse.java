package danny.yugioh.response;

import java.util.List;

public class PlayerNameResponse {
    //回傳符合條件的player
    List<String> players;

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
