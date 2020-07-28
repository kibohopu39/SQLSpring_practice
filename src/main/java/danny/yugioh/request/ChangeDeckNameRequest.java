package danny.yugioh.request;

public class ChangeDeckNameRequest {
    //輸入要修改的牌組名
    //輸入要修改的對象
    //輸入要修改牌組的持有玩家Id
    Integer playId;
    String deckname;
    String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int plaeyId) {
        this.playId = plaeyId;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
