import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ORMQueriesTest {

    private static Flyway flyway;
    private static ORMQueries ormQueries;

    @BeforeAll
    static void initialize() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "password";
        try {

            //доступ к БД
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, login, password);
            flyway = Flyway.configure().dataSource(url, login, password).load();
            flyway.baseline();
            flyway.migrate();

            ormQueries = new ORMQueries();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllPupilsInfo() {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {
            expected.add(new Pupil(1l,"Горбач", "Дмитрий", "Николаевич", "M",
                    "Belarusian", 182, 70, format.parse("13.09.2001"), "375291976500",
                    "220137", "Belarus", "Minsk", "Minsk", "Minsk", "ул. Ангарская",
                    "21", 1, 7, 80l));
            expected.add(new Pupil(2l,"Врублевская", "Екатерина", "Александровна", "F", "Belarusian", 173, 50,
                    format.parse("29.07.2002"), "375291972233", "220102", "Belarus", "Minsk", "Minsk",
                    "Dziarzhynsk", "пер. Весенний", "23", null, 5, 1l));

            expected.add(new Pupil(3l,"Доскоч", "Роман", "Дмитриевич", "M", "Belarusian", 130, 68,
                    format.parse("15.05.2002"), "375295553535", "220047", "Belarus", "Mahilyow", "Horki",
                    "Horki", "ул. Сельскохозяйственная", "3", 1, 5, 312l));

            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));

            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));

            expected.add(new Pupil(6l,"Котько", "Пётр", "Васильевич", "M", "Ukrainian", 175, 80,
                    format.parse("10.12.1999"), "480291976500", "220137", "Ukraine", "Kyiv", "Kyiv",
                    "Kyiv", "вул. Аеродромна", "14/2", 1, 5, 3l));
            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                            format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetAllPupilsInfo();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsSortedByIdDESC() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {
            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));
            expected.add(new Pupil(6l,"Котько", "Пётр", "Васильевич", "M", "Ukrainian", 175, 80,
                    format.parse("10.12.1999"), "480291976500", "220137", "Ukraine", "Kyiv", "Kyiv",
                    "Kyiv", "вул. Аеродромна", "14/2", 1, 5, 3l));
            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));
            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));
            expected.add(new Pupil(3l,"Доскоч", "Роман", "Дмитриевич", "M", "Belarusian", 130, 68,
                    format.parse("15.05.2002"), "375295553535", "220047", "Belarus", "Mahilyow", "Horki",
                    "Horki", "ул. Сельскохозяйственная", "3", 1, 5, 312l));
            expected.add(new Pupil(2l,"Врублевская", "Екатерина", "Александровна", "F", "Belarusian", 173, 50,
                    format.parse("29.07.2002"), "375291972233", "220102", "Belarus", "Minsk", "Minsk",
                    "Dziarzhynsk", "пер. Весенний", "23", null, 5, 1l));
            expected.add(new Pupil(1l,"Горбач", "Дмитрий", "Николаевич", "M",
                    "Belarusian", 182, 70, format.parse("13.09.2001"), "375291976500",
                    "220137", "Belarus", "Minsk", "Minsk", "Minsk", "ул. Ангарская",
                    "21", 1, 7, 80l));
        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsSortedByIdDESC();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsSortedByNameASC() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {
            expected.add(new Pupil(1l,"Горбач", "Дмитрий", "Николаевич", "M",
                    "Belarusian", 182, 70, format.parse("13.09.2001"), "375291976500",
                    "220137", "Belarus", "Minsk", "Minsk", "Minsk", "ул. Ангарская",
                    "21", 1, 7, 80l));
            expected.add(new Pupil(2l,"Врублевская", "Екатерина", "Александровна", "F", "Belarusian", 173, 50,
                    format.parse("29.07.2002"), "375291972233", "220102", "Belarus", "Minsk", "Minsk",
                    "Dziarzhynsk", "пер. Весенний", "23", null, 5, 1l));
            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));
            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));
            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));
            expected.add(new Pupil(6l,"Котько", "Пётр", "Васильевич", "M", "Ukrainian", 175, 80,
                    format.parse("10.12.1999"), "480291976500", "220137", "Ukraine", "Kyiv", "Kyiv",
                    "Kyiv", "вул. Аеродромна", "14/2", 1, 5, 3l));
            expected.add(new Pupil(3l,"Доскоч", "Роман", "Дмитриевич", "M", "Belarusian", 130, 68,
                    format.parse("15.05.2002"), "375295553535", "220047", "Belarus", "Mahilyow", "Horki",
                    "Horki", "ул. Сельскохозяйственная", "3", 1, 5, 312l));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsSortedByNameASC();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsId5() {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {
            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));
        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsId5();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsNameLikeM() {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {

            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));

            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));

            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsNameLikeM();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsWhereGrade5() {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {

            expected.add(new Pupil(2l,"Врублевская", "Екатерина", "Александровна", "F", "Belarusian", 173, 50,
                    format.parse("29.07.2002"), "375291972233", "220102", "Belarus", "Minsk", "Minsk",
                    "Dziarzhynsk", "пер. Весенний", "23", null, 5, 1l));

            expected.add(new Pupil(3l,"Доскоч", "Роман", "Дмитриевич", "M", "Belarusian", 130, 68,
                    format.parse("15.05.2002"), "375295553535", "220047", "Belarus", "Mahilyow", "Horki",
                    "Horki", "ул. Сельскохозяйственная", "3", 1, 5, 312l));

            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));

            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));

            expected.add(new Pupil(6l,"Котько", "Пётр", "Васильевич", "M", "Ukrainian", 175, 80,
                    format.parse("10.12.1999"), "480291976500", "220137", "Ukraine", "Kyiv", "Kyiv",
                    "Kyiv", "вул. Аеродромна", "14/2", 1, 5, 3l));
            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsWhereGrade5();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsLast5() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<Pupil> expected = new ArrayList<>();

        try {

            expected.add(new Pupil(3l,"Доскоч", "Роман", "Дмитриевич", "M", "Belarusian", 130, 68,
                    format.parse("15.05.2002"), "375295553535", "220047", "Belarus", "Mahilyow", "Horki",
                    "Horki", "ул. Сельскохозяйственная", "3", 1, 5, 312l));

            expected.add(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l));

            expected.add(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l));

            expected.add(new Pupil(6l,"Котько", "Пётр", "Васильевич", "M", "Ukrainian", 175, 80,
                    format.parse("10.12.1999"), "480291976500", "220137", "Ukraine", "Kyiv", "Kyiv",
                    "Kyiv", "вул. Аеродромна", "14/2", 1, 5, 3l));
            expected.add(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }
        var result = ormQueries.GetPupilsLast5();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsMinMaxWeight() {

        var result = ormQueries.GetPupilsMinMaxWeight();
        assertEquals(34, result[0]);
        assertEquals(80, result[1]);
    }

    @Test
    void getCountPupilsWithHeightNoLess128() {
        var result = ormQueries.GetCountPupilsWithHeightNoLess128();
        assertEquals(6, result);
    }

    @Test
    void getWeightPupilsWithHeight120() {
        var result = ormQueries.GetWeightPupilsWithHeight120();
        assertEquals(34, result);
    }

    @Test
    void getPupilsAndSchool() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<PupilAndSchool> expected = new ArrayList<>();
        try {
            expected.add(new PupilAndSchool(1l,"Горбач", "Дмитрий", "Николаевич",
                    format.parse("13.09.2001"), 7, null));
            expected.add(new PupilAndSchool(2l,"Врублевская", "Екатерина", "Александровна",
                    format.parse("29.07.2002"),  5, "№ 1"));

            expected.add(new PupilAndSchool(3l,"Доскоч", "Роман", "Дмитриевич",
                    format.parse("15.05.2002"), 5, "High Klementon"));

            expected.add(new PupilAndSchool(4l,"Буль", "Мартин", "Леонидович",
                    format.parse("15.04.2003"), 5, "№ 1"));

            expected.add(new PupilAndSchool(5l,"Коновалова", "Мария", "Валерьевна",
                    format.parse("27.09.2002"), 5, "№ 5"));

            expected.add(new PupilAndSchool(6l,"Котько", "Пётр", "Васильевич",
                    format.parse("10.12.1999"), 5, null));

            expected.add(new PupilAndSchool(7l,"Гобинко", "Марвин", "Николаевич",
                    format.parse("23.04.2004"), 5, "№ 5"));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }

        var result = ormQueries.GetPupilsAndSchool();
        assertEquals(expected, result);
    }

    @Test
    void getPupilsAndSchoolAllId1and5() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        List<Pair<Pupil,School>> expected = new ArrayList<>();

        School schools[] = {new School(312l,"High Klementon","High Klementon for High grates!"),
                        new School(1290l,"HS Cherryton", "For cool kids"),
                        new School(1l, "№ 1" ,"Everage high school"),
                        new School(5l, "№ 5" ,"Everage middle school")};
        try {

            expected.add(Pair.with(new Pupil(2l,"Врублевская", "Екатерина", "Александровна", "F", "Belarusian", 173, 50,
                    format.parse("29.07.2002"), "375291972233", "220102", "Belarus", "Minsk", "Minsk",
                    "Dziarzhynsk", "пер. Весенний", "23", null, 5, 1l),schools[2]));

            expected.add(Pair.with(new Pupil(4l,"Буль", "Мартин", "Леонидович", "M", "Belarusian", 165, 70,
                    format.parse("15.04.2003"), "375297258444", "220047", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Нестерова", "54/2", 47, 5, 1l),schools[2]));

            expected.add(Pair.with(new Pupil(5l,"Коновалова", "Мария", "Валерьевна", "F", "Belarusian", 172, 51,
                    format.parse("27.09.2002"), "375292003040", "220137", "Belarus", "Brest", "Brest",
                    "Brest", "ул. Польская", "35", 121, 5, 5l),schools[3]));

            expected.add(Pair.with(new Pupil(7l,"Гобинко", "Марвин", "Николаевич", "M", "Belarusian", 120, 34,
                    format.parse("23.04.2004"), "375291979000", "220137", "Belarus", "Minsk", "Minsk",
                    "Minsk", "ул. Ангарская", "89", 89, 5, 5l),schools[3]));

        }
        catch(ParseException e){
            System.err.println("Incorrect data input! "+e.getMessage());
        }


        var result = ormQueries.GetPupilsAndSchoolAllId1and5();
        assertEquals(expected, result);
    }

    @AfterAll
    static void clean() {
        flyway.clean();
    }
}