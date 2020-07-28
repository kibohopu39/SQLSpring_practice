package danny.yugioh.request;

import java.util.List;

public class NewDeckCardsRequest {
    //新增對象玩家
    private Integer playerId;
    //新增對象卡片
    private List<String> cardsname;
    //新增對象牌組
    private String deckname;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public List<String> getCardsname() {
        return cardsname;
    }

    public void setCardsname(List<String> cardname) {
        this.cardsname = cardname;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
