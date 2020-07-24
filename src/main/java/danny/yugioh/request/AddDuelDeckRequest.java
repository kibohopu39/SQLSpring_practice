package danny.yugioh.request;

import javax.validation.constraints.NotBlank;

public class AddDuelDeckRequest {
    @NotBlank
    private String deckname;
    private int duelistId;
    //要新增牌組的持有者

    public int getDuelistId() {
        return duelistId;
    }

    public void setDuelistId(int duelistId) {
        this.duelistId = duelistId;
    }

    public String getDeckname() {
        return deckname;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
