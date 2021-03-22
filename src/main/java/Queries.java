
import java.sql.ResultSet;
import java.sql.Statement;


public class Queries {

    private static String GetData(Statement stmt,String s){

        String result = "";

        try {
            ResultSet rs = stmt.executeQuery(s);
            //Retrieving the ResultSetMetaData object
            //getting the column type
            int column_count = (rs.getMetaData()).getColumnCount();


            while (rs.next()) {
                for(int i=1;i<=column_count;i++) {
                    result+=rs.getString(i)+'|';
                }
                result+='\n';
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String GetAllPupilsInfo(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils");
    }
    public static String GetPupilsSortedByIdDESC(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils ORDER BY id DESC");
    }
    public static String GetPupilsSortedByNameASC(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils ORDER BY name ASC");
    }
    public static String GetPupilsLast5(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils ORDER BY id DESC LIMIT 5");
    }
    public static String GetPupilsId5(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils WHERE id = 5");
    }
    public static String GetPupilsNameLikeM(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils WHERE name LIKE 'М%'");
    }
    public static String GetPupilsWhereGrade5(Statement stmt) {
        return GetData(stmt,"SELECT * FROM pupils WHERE grade = 5");
    }
    public static String GetCountPupilsWithHeightNoLess128(Statement stmt) {
        return GetData(stmt,"SELECT COUNT(id) FROM pupils WHERE height > 128");
    }
    public static String GetWeightPupilsWithHeight120(Statement stmt) {
        return GetData(stmt,"SELECT SUM(weight) FROM pupils WHERE height = 120");
    }
    public static String GetPupilsMinMaxWeight(Statement stmt) {
        return GetData(stmt,"SELECT MIN(weight),MAX(weight) FROM pupils");
    }
    public static String GetPupilsAndSchool(Statement stmt) {
        return GetData(stmt,"SELECT pupils.id, pupils.surname, pupils.name, pupils.fathername, pupils.birthday, pupils.grade, school.sch_name\n" +
                        "FROM pupils\n" +
                        "LEFT JOIN school\n" +
                        "ON pupils.school_id = school.id");
    }
    public static String GetPupilsAndSchoolAllId1and5(Statement stmt) {
        return GetData(stmt,"SELECT * \n" +
                        "FROM pupils\n" +
                        "INNER JOIN school\n" +
                        "ON pupils.school_id = school.id\n" +
                        "WHERE school.id IN (1, 5)");
    }
}


