import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class QueriesTest {

    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String login = "postgres";
    static String password = "password";

    @BeforeAll
    public static void InitBD(){
        try {
            //доступ к БД
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, login, password);

            //flyway для миграции версий БД
            Flyway flyway = Flyway.configure().dataSource(url, login, password).load();
            //flyway.baseline();
            flyway.migrate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllPupilsInfo() {

        String expectedResult = "1|Горбач|Дмитрий|Николаевич|M|Belarusian|182|70|2001-09-13|375291976500|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|21|1|7|80|\n" +
                "2|Врублевская|Екатерина|Александровна|F|Belarusian|173|50|2002-07-29|375291972233|220102|Belarus|Minsk|Minsk|Dziarzhynsk|пер. Весенний|23|null|5|1|\n" +
                "3|Доскоч|Роман|Дмитриевич|M|Belarusian|130|68|2002-05-15|375295553535|220047|Belarus|Mahilyow|Horki|Horki|ул. Сельскохозяйственная|3|1|5|312|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "6|Котько|Пётр|Васильевич|M|Ukrainian|175|80|1999-12-10|480291976500|220137|Ukraine|Kyiv|Kyiv|Kyiv|вул. Аеродромна|14/2|1|5|3|\n" +
                "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetAllPupilsInfo(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsSortedByIdDESC() {

        String expectedResult = "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n" +
                "6|Котько|Пётр|Васильевич|M|Ukrainian|175|80|1999-12-10|480291976500|220137|Ukraine|Kyiv|Kyiv|Kyiv|вул. Аеродромна|14/2|1|5|3|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "3|Доскоч|Роман|Дмитриевич|M|Belarusian|130|68|2002-05-15|375295553535|220047|Belarus|Mahilyow|Horki|Horki|ул. Сельскохозяйственная|3|1|5|312|\n" +
                "2|Врублевская|Екатерина|Александровна|F|Belarusian|173|50|2002-07-29|375291972233|220102|Belarus|Minsk|Minsk|Dziarzhynsk|пер. Весенний|23|null|5|1|\n" +
                "1|Горбач|Дмитрий|Николаевич|M|Belarusian|182|70|2001-09-13|375291976500|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|21|1|7|80|\n";
        String actualResult = "";
        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsSortedByIdDESC(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsSortedByNameASC() {

        String expectedResult = "1|Горбач|Дмитрий|Николаевич|M|Belarusian|182|70|2001-09-13|375291976500|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|21|1|7|80|\n" +
                "2|Врублевская|Екатерина|Александровна|F|Belarusian|173|50|2002-07-29|375291972233|220102|Belarus|Minsk|Minsk|Dziarzhynsk|пер. Весенний|23|null|5|1|\n" +
                "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "6|Котько|Пётр|Васильевич|M|Ukrainian|175|80|1999-12-10|480291976500|220137|Ukraine|Kyiv|Kyiv|Kyiv|вул. Аеродромна|14/2|1|5|3|\n" +
                "3|Доскоч|Роман|Дмитриевич|M|Belarusian|130|68|2002-05-15|375295553535|220047|Belarus|Mahilyow|Horki|Horki|ул. Сельскохозяйственная|3|1|5|312|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsSortedByNameASC(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsLast5() {

        String expectedResult = "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n" +
                "6|Котько|Пётр|Васильевич|M|Ukrainian|175|80|1999-12-10|480291976500|220137|Ukraine|Kyiv|Kyiv|Kyiv|вул. Аеродромна|14/2|1|5|3|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "3|Доскоч|Роман|Дмитриевич|M|Belarusian|130|68|2002-05-15|375295553535|220047|Belarus|Mahilyow|Horki|Horki|ул. Сельскохозяйственная|3|1|5|312|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsLast5(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsId5() {

        String expectedResult = "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsId5(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsNameLikeM() {

        String expectedResult = "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n";
        String actualResult ="";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsNameLikeM(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsWhereGrade5() {

        String expectedResult = "2|Врублевская|Екатерина|Александровна|F|Belarusian|173|50|2002-07-29|375291972233|220102|Belarus|Minsk|Minsk|Dziarzhynsk|пер. Весенний|23|null|5|1|\n" +
                "3|Доскоч|Роман|Дмитриевич|M|Belarusian|130|68|2002-05-15|375295553535|220047|Belarus|Mahilyow|Horki|Horki|ул. Сельскохозяйственная|3|1|5|312|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|\n" +
                "6|Котько|Пётр|Васильевич|M|Ukrainian|175|80|1999-12-10|480291976500|220137|Ukraine|Kyiv|Kyiv|Kyiv|вул. Аеродромна|14/2|1|5|3|\n" +
                "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsWhereGrade5(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getCountPupilsWithHeightNoLess128() {

        String expectedResult = "6|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetCountPupilsWithHeightNoLess128(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getWeightPupilsWithHeight120() {

        String expectedResult = "34|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetWeightPupilsWithHeight120(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsMinMaxWeight() {

        String expectedResult = "34|80|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsMinMaxWeight(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsAndSchool() {

        String expectedResult = "3|Доскоч|Роман|Дмитриевич|2002-05-15|5|High Klementon|\n" +
                "4|Буль|Мартин|Леонидович|2003-04-15|5|№ 1|\n" +
                "2|Врублевская|Екатерина|Александровна|2002-07-29|5|№ 1|\n" +
                "7|Гобинко|Марвин|Николаевич|2004-04-23|5|№ 5|\n" +
                "5|Коновалова|Мария|Валерьевна|2002-09-27|5|№ 5|\n" +
                "1|Горбач|Дмитрий|Николаевич|2001-09-13|7|null|\n" +
                "6|Котько|Пётр|Васильевич|1999-12-10|5|null|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsAndSchool(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPupilsAndSchoolAllId1and5() {

        String expectedResult = "2|Врублевская|Екатерина|Александровна|F|Belarusian|173|50|2002-07-29|375291972233|220102|Belarus|Minsk|Minsk|Dziarzhynsk|пер. Весенний|23|null|5|1|1|№ 1|Everage high school|\n" +
                "4|Буль|Мартин|Леонидович|M|Belarusian|165|70|2003-04-15|375297258444|220047|Belarus|Minsk|Minsk|Minsk|ул. Нестерова|54/2|47|5|1|1|№ 1|Everage high school|\n" +
                "5|Коновалова|Мария|Валерьевна|F|Belarusian|172|51|2002-09-27|375292003040|220137|Belarus|Brest|Brest|Brest|ул. Польская|35|121|5|5|5|№ 5|Everage middle school|\n" +
                "7|Гобинко|Марвин|Николаевич|M|Belarusian|120|34|2004-04-23|375291979000|220137|Belarus|Minsk|Minsk|Minsk|ул. Ангарская|89|89|5|5|5|№ 5|Everage middle school|\n";
        String actualResult = "";

        try {

            Connection con = DriverManager.getConnection(url, login, password);
            //создание и обработка запросов
            try {
                Statement stmt = con.createStatement();
                actualResult = Queries.GetPupilsAndSchoolAllId1and5(stmt);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, actualResult);
    }
}