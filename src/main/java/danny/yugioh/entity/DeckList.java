package danny.yugioh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "decklist")
public class DeckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "deckname",length = 30)
    private java.lang.String deckname;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "duelist_id")
    private Player duelist;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "useCard_decklist",joinColumns = {@JoinColumn(name = "decklist_id")},inverseJoinColumns = {@JoinColumn(name = "card_id")})
    private List<GameCardUse> gameCardUseList;//本牌組使用了哪些卡片

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getDeckname() {
        return deckname;
    }

    public void setDeckname(java.lang.String deckname) {
        this.deckname = deckname;
    }

    public Player getDuelist() {
        return duelist;
    }

    public void setDuelist(Player duelist) {
        this.duelist = duelist;
    }

    public List<GameCardUse> getGameCardUseList() {
        return gameCardUseList;
    }

    public void setGameCardUseList(List<GameCardUse> gameCardUseList) {
        this.gameCardUseList = gameCardUseList;
    }
}
