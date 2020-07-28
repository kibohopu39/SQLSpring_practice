package danny.yugioh.response;

import java.util.List;

public class NewDeckCardsResponse {
    //傳出重複的卡名
    private List<String> cardsname;
    //傳出的Message
    private String messge;
    public List<String> getCardsname() {
        return cardsname;
    }

    public void setCardsname(List<String> cardsname) {
        this.cardsname = cardsname;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }
}
