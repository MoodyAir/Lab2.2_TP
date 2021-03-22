import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
@Table(name = "school")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "sch_name", length = 30, nullable = false)
    private String sch_name;
    @Column(name = "sch_description")
    private String sch_description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School that = (School) o;
        return Objects.equals(id, that.id) && Objects.equals(sch_name, that.sch_name) &&
                Objects.equals(sch_description, that.sch_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sch_name, sch_description);
    }

    @Override
    public String toString() {

        return "School: " +
                "id=" + id +
                ", name=" + sch_name +
                ", description=" + sch_description +
                "\n";
    }
}

