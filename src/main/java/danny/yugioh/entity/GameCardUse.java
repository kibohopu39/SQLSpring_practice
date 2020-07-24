package danny.yugioh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cardlist")
public class GameCardUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //所有現在可以用的卡片，每張牌每副牌組都可以用
    @Column(name = "cardname",length = 30)
    private String cardname;
    @Column(name = "mAtk",length = 10)
    private int mATK;
    @Column(name = "mDEF",length = 10)
    private int mDEF;
    @Column(name = "mLevel",length = 10)
    private int mLevel;
    @Column(name = "mAttrib",length = 10)
    private String mAttribute;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "useCard_decklist",joinColumns = {@JoinColumn(name = "card_id")},inverseJoinColumns = {@JoinColumn(name = "decklist_id")})
    //產出一個中間表叫做useCard_decklist，他的某一欄位跟本表的主鍵對應，另一個欄位與跟有使用JoinTable名字是useCard_decklist的表的主鍵對應
    private List<DeckList> deckLists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public int getmATK() {
        return mATK;
    }

    public void setmATK(int mATK) {
        this.mATK = mATK;
    }

    public int getmDEF() {
        return mDEF;
    }

    public void setmDEF(int mDEF) {
        this.mDEF = mDEF;
    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public String getmAttribute() {
        return mAttribute;
    }

    public void setmAttribute(String mAttribute) {
        this.mAttribute = mAttribute;
    }

    public List<DeckList> getDeckLists() {
        return deckLists;
    }

    public void setDeckLists(List<DeckList> deckLists) {
        this.deckLists = deckLists;
    }
}
