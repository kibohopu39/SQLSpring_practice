package danny.yugioh.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gamematch")
public class GameMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "matchname",length = 20)//比賽名稱，一個人可以參加多場比賽，比賽也可以有多人參加
    private java.lang.String matchname;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "duelist_gamematch",joinColumns = {@JoinColumn(name = "gamematch_id")},inverseJoinColumns = {@JoinColumn(name = "duelist_id")})
    private List<Player> duelists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getMatchname() {
        return matchname;
    }

    public void setMatchname(java.lang.String matchname) {
        this.matchname = matchname;
    }

    public List<Player> getDuelists() {
        return duelists;
    }

    public void setDuelists(List<Player> duelists) {
        this.duelists = duelists;
    }
}
