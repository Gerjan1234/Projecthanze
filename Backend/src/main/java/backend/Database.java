package backend;


import java.sql.*;
import java.util.ArrayList;

/**
 * class Database
 * @author (Gerjan)
 * @version (09-08-2019)
 * class voor de database selects en put
 */
public class Database {

    private static Connection conn = null;
    private static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(Application.HOST_NAME, Application.USER_NAME, Application.PASSWORD);
        }
        return conn;
    }

    /**
     * Voorbeeld methode voor een insert naar database
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */

    protected static int putdata(employees putData) throws SQLException {
        String sql = "insert into tabel values (?,?,?);";  //tabel vul hier naam van tabel in
        int rv = -1;
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, putData.first_name);   //tabel kolom1 invullen
            stmt.setString(2, putData.last_name);   //tabel kolom2 invullen
            stmt.setString(3, putData.gender);   //tabel kolom3 invullen
            stmt.execute();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);
        }
        return rv;
    }

    /**
     * Voorbeeld methode voor een select van database
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */

    protected static ArrayList getdata(int limit) throws SQLException {
        ArrayList<salary> results = new ArrayList<>();
        String sql = "select * from tabel order by date desc limit ?;";  //vul hier tabelnaam in
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, limit);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                salary r = new salary();
                r.socialsecurity_id = res.getDouble(1); //tabel kolom1 invullen
                r.Franchise = res.getDouble(5); //tabel kolom2 invullen
                r.max_pension_salary = res.getBigDecimal("max_pension_salary"); //tabel kolom3 invullen
                results.add(r);
            }
        }
        return results;

    }
}

