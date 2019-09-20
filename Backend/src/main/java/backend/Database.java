package backend;


import java.sql.*;
import java.util.ArrayList;

/**
 * class Database
 *
 * @author (Gerjan)
 * @version (09 - 08 - 2019)
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
     * * @author (Gerjan)
     * * @version (09-08-2019)
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

//    protected static ArrayList getdata(int limit) throws SQLException {
//        ArrayList<salary> results = new ArrayList<>();
//        String sql = "select * from tabel order by date desc limit ?;";  //vul hier tabelnaam in
//        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setInt(1, limit);
//            ResultSet res = stmt.executeQuery();
//            while (res.next()) {
//                salary r = new salary();
//                r.socialsecurity_id = res.getDouble(1); //tabel kolom1 invullen
//                r.Franchise = res.getDouble(5); //tabel kolom2 invullen
//                r.max_pension_salary = res.getBigDecimal("max_pension_salary"); //tabel kolom3 invullen
//                results.add(r);
//            }
//        }
//        return results;
//
//    }

    /**
     * Methode check inlognaam
     * * @author (Teo)
     * * @version (02-09-2019)
     */

    protected static String chkInlog(double usr, String psw) throws SQLException {
        ArrayList<security> results = new ArrayList<>();
        String oke = "init";
        String sql = "SELECT * FROM security WHERE security.security_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, usr);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                security r = new security();
                r.security_id = (int) res.getDouble(1);
                r.password = res.getString(2);
                r.mailadress = res.getString(3);
                results.add(r);
            }

        }
        int tst = results.size();
        System.out.println("Lengte van de retour array: " + tst);

        if (tst > 0) {
            System.out.println("sql resultaat Id: " + results.get(0).security_id);
            System.out.println("sql resultaat psw: " + results.get(0).password);
        }

        if ((int) usr == results.get(0).security_id && psw.equals(results.get(0).password)) {
            System.out.println("chkLogin-Id: " + results.get(0).security_id);
            System.out.println("chkLogin-psw: " + results.get(0).password);
            return oke = "Combinatie gebruiker en wachtwoord is correct";
        } else {
            return oke = "Combinatie gebruiker en wachtwoord is fout!";
        }

    }

<<<<<<< HEAD
  /*  static int getAantalWerknemersPerWerkgever(String employerId) {

=======
 //   static int getAantalWerknemersPerWerkgever(String employerId) {
//
>>>>>>> 04f5bbd4227a242e2d48f0c4793dc6609ff0475b
//        -- aantal werknemers per werkgever --
 //       SELECT employer_id , count(employer_id) as `count`
 //       FROM pensioenaanspraken.employees
   //     GROUP BY employer_id;


<<<<<<< HEAD
    }*/
=======
   // }
>>>>>>> 04f5bbd4227a242e2d48f0c4793dc6609ff0475b
}

