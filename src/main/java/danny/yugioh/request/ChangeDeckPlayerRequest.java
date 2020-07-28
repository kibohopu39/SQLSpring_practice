package danny.yugioh.request;

public class ChangeDeckPlayerRequest {
    //輸入的牌組名
    private String deckname;
    //修改前的玩家
    private Integer oldPlayerId;
    //修改後的玩家
    private Integer newPlayerId;

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }

    public Integer getOldPlayerId() {
        return oldPlayerId;
    }

    public void setOldPlayerId(Integer oldPlayerId) {
        this.oldPlayerId = oldPlayerId;
    }

    public Integer getNewPlayerId() {
        return newPlayerId;
    }

    public void setNewPlayerId(Integer newPlayerId) {
        this.newPlayerId = newPlayerId;
    }
}
