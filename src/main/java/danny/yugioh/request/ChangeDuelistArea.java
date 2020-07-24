package danny.yugioh.request;

public class ChangeDuelistArea {
    //輸入要更改的決鬥者名稱ID
    //輸入要更改的地區
    private int id;
    private String area;

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
}
