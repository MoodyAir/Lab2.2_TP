import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PupilAndSchool {
    private Long id;
    private String surname;
    private String name;
    private String fathername;
    private Date birthday;
    private Integer grade;
    private String sch_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilAndSchool that = (PupilAndSchool) o;
        return Objects.equals(id, that.id) && Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) && Objects.equals(fathername, that.fathername) &&
                Objects.equals(birthday, that.birthday) && Objects.equals(grade, that.grade) &&
                Objects.equals(sch_name, that.sch_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,surname,name,fathername,birthday,grade,sch_name);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        return "Pupil: " +
                "id=" + id +
                ", surname=" + surname +
                ", name=" + name +
                ", fathername='" + fathername +
                ", birthday=" + format.format(birthday) +
                ", grade=" + grade +
                ", school=" + sch_name +
                "\n";
    }
}