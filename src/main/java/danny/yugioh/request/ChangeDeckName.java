package danny.yugioh.request;

public class ChangeDeckName {
    //輸入要修改的牌組名
    //輸入要修改的對象
    //輸入要修改牌組的持有玩家Id
    int plaeyId;
    String deckname;
    String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPlaeyId() {
        return plaeyId;
    }

    public void setPlaeyId(int plaeyId) {
        this.plaeyId = plaeyId;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
