package danny.yugioh.response;

import danny.yugioh.entity.DeckList;

import java.util.List;

public class PlayerDeck {
    //回傳出玩家的牌組
    List<DeckList> deckLists;

    public List<DeckList> getDeckLists() {
        return deckLists;
    }

    public void setDeckLists(List<DeckList> deckLists) {
        this.deckLists = deckLists;
    }
}
