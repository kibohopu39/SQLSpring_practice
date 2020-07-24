package danny.yugioh.request;

import danny.yugioh.entity.DeckList;

import java.util.List;

public class ChangeDeckOwner {
    //輸入的牌組名增
    //想要修改成哪位玩家持有
    List<String> deckNameList;
    int playerId;

    public List<String> getDeckNameList() {
        return deckNameList;
    }

    public void setDeckNameList(List<String> deckNameList) {
        this.deckNameList = deckNameList;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
