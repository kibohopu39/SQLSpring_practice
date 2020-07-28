package danny.yugioh.request;

public class ChangePlayerRequest {
    //輸入要更改目標的決鬥者名稱ID
    private Integer id;
    //輸入更改後地區
    private String area;
    //輸入更改後姓名
    private String name;
    //輸入更改後年齡
    private Integer age;
    //輸入更改後積分
    private Integer score;
    //輸入更改後性別
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
