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
     * Methode voor een insert naar database
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */

    protected static int putdata(Pojo putData) throws SQLException {
        String sql = "insert into tabel values (?,?,?);";  //tabel vul hier naam van tabel in
        int rv = -1;
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, putData.kolom1);   //tabel kolom1 invullen
            stmt.setString(2, putData.kolom2);   //tabel kolom2 invullen
            stmt.setString(3, putData.kolom3);   //tabel kolom3 invullen
            stmt.execute();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) rv = keys.getInt(1);
        }
        return rv;
    }

    /**
     * Methode voor een select van database
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */

    protected static ArrayList getdata(int limit) throws SQLException {
        ArrayList<Pojo> results = new ArrayList<>();
        String sql = "select * from tabel order by date desc limit ?;";  //vul hier tabelnaam in
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, limit);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Pojo r = new Pojo();
                r.kolom1 = res.getString("kolom1"); //tabel kolom1 invullen
                r.kolom2 = res.getString("kolom3"); //tabel kolom2 invullen
                r.kolom3 = res.getString("kolom2"); //tabel kolom3 invullen
                results.add(r);
            }
        }
        return results;

    }
}

