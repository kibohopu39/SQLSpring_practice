package danny.yugioh.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class DeckCardsDeckNamePlayerRequest {
    private List<String> cardsList;
    //想要修改誰的牌組
    private int duelistId;
    //想要修改哪個牌組
    @NotBlank
    private String deckname;


    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }

    public int getDuelistId() {
        return duelistId;
    }

    public void setDuelistId(int duelistId) {
        this.duelistId = duelistId;
    }

    public List<String> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<String> cardsList) {
        this.cardsList = cardsList;
    }
}
