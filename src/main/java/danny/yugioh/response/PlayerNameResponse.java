package danny.yugioh.response;

import danny.yugioh.entity.Player;

import java.util.List;

public class PlayerNameResponse {
    //回傳符合條件的player
    List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
