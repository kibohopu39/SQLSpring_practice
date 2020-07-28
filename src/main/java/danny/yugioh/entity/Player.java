package danny.yugioh.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "duelist")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",length = 11)
    private java.lang.String name;
    @Column(name = "age",length = 10)
    private int age;
    @Column(name = "eamil",length = 40)
    private java.lang.String email;
    @Column(name = "rankpoint",length = 255)
    private int rank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id",referencedColumnName = "id")
    private Detail detail;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "duelist")
    private List<DeckList> deckLists;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "duelist_gamematch",joinColumns = {@JoinColumn(name = "duelist_id")},inverseJoinColumns = {@JoinColumn(name = "gamematch_id")})
    private List<GameMatch> gameMatches;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<DeckList> getDeckLists() {
        return deckLists;
    }

    public void setDeckLists(List<DeckList> deckLists) {
        this.deckLists = deckLists;
    }

    public List<GameMatch> getGameMatches() {
        return gameMatches;
    }

    public void setGameMatches(List<GameMatch> gameMatches) {
        this.gameMatches = gameMatches;
    }
}
