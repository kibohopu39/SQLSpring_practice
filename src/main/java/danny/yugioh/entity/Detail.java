package danny.yugioh.entity;

import javax.persistence.*;

@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "livingArea",length = 20)
    private String area;
    @Column(name = "gender",length = 10)
    private String gender;

    @OneToOne(mappedBy = "detail")
    private Player duelist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Player getDuelist() {
        return duelist;
    }

    public void setDuelist(Player duelist) {
        this.duelist = duelist;
    }
}
