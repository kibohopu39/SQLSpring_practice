package danny.yugioh.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "picname")
    private Player name;
    @Column(name = "pic")
    private byte[] BlobTypedata;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Player getName() {
        return name;
    }

    public void setName(Player name) {
        this.name = name;
    }

    public byte[] getBlobTypedata() {
        return BlobTypedata;
    }

    public void setBlobTypedata(byte[] blobTypedata) {
        BlobTypedata = blobTypedata;
    }
}
