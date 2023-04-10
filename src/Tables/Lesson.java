package Tables;

public class Lesson {

    Integer id;
    String name;
    String updatedTime;
    Homework homework;


    public Lesson(int name, String updatedTime, Homework homework) {
        this.homework = homework;
        this.name = name;
        this.updatedTime = updatedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", homework=" + homework +
                '}';
    }
}
