import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pupils")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pupil {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "surname", length = 50, nullable = false)
    private String surname;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "fathername", length = 50)
    private String fathername;
    @Column(name = "gender", length = 1, nullable = false)
    private String gender;
    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;
    @Column(name = "height", nullable = false)
    private Integer height;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "phone_number", length = 15, nullable = false)
    private String phone_number;
    @Column(name = "post_code", length = 10, nullable = false)
    private String post_code;
    @Column(name = "country", length = 30, nullable = false)
    private String country;
    @Column(name = "region", length = 30)
    private String region;
    @Column(name = "district", length = 30)
    private String district;
    @Column(name = "city", length = 30, nullable = false)
    private String city;
    @Column(name = "street", length = 30, nullable = false)
    private String street;
    @Column(name = "building", length = 10, nullable = false)
    private String building;
    @Column(name = "flat")
    private Integer flat;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "school_id")
    private Long school_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil that = (Pupil) o;
        return Objects.equals(id, that.id) && Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) && Objects.equals(fathername, that.fathername) &&
                Objects.equals(gender, that.gender) && Objects.equals(nationality, that.nationality) &&
                Objects.equals(birthday, that.birthday) && Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) && Objects.equals(phone_number, that.phone_number) &&
                Objects.equals(post_code, that.post_code) && Objects.equals(country, that.country) &&
                Objects.equals(region, that.region) && Objects.equals(district, that.district) &&
                Objects.equals(city, that.city) && Objects.equals(street, that.street) &&
                Objects.equals(building, that.building) && Objects.equals(flat, that.flat) &&
                Objects.equals(grade, that.grade) && Objects.equals(school_id, that.school_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,surname,name,fathername,gender,nationality,height,
                            weight,birthday,phone_number,post_code, country,region,district,
                            city,street,building,flat,grade,school_id);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "Pupil: " +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", fathername='" + fathername + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", birthday=" + format.format(birthday) +
                ", phone_number='" + phone_number + '\'' +
                ", post_code='" + post_code + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", grade='" + grade + '\'' +
                ", school_id='" + school_id +
                "\n";
    }
}

