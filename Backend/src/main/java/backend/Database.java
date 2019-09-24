package backend;


import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
     *  methode voor een insert naar database vanuit de salaris uploader.
     *  telt eerst aantal regels in database
     *  dan contorle in 2 tabel of employee  nummer oke is niet oke geen insert
     *  wel oke dan  insert
     *  indien geen insert dan return 0
     * * @author (Gerjan)
     * * @version (21-09-2019)
     */

    protected static int addsalarismutatie(List lines) throws SQLException {
        boolean allelinenoke = false;
        String[] test;
        String[] test2;
        test = new String[8];
        int K = 0;
        int L = 0;
        int rv = -1;
        long key = -1L;
        Double employer_id = 0.0;
        String sql2 = "SELECT COUNT(socialsecurity_id) FROM salary;";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                K = res.getInt(1);
            }
        }
        Iterator it = lines.iterator();
        int aantaloke = 0;
        while (it.hasNext()) {
            String line = (String) it.next();
            test = line.split(";");
            String sql3 = "select employer_id from employees where socialsecurity_id like '344996808';";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    employer_id = res.getDouble(1);
                    if (employer_id.toString().substring(0, employer_id.toString().length() - 2).equals(test[1].replaceAll(" ", ""))) {
                        System.out.println(" oke");
                        aantaloke = aantaloke + 1;
                    }

                }
            }
            System.out.println(lines.size());
            System.out.println(aantaloke);
            if (lines.size() == aantaloke) {
                allelinenoke = true;
            }

        }
        if (allelinenoke == true) {
            Iterator it2 = lines.iterator();
            while (it2.hasNext()) {
                String line2 = (String) it2.next();
                test2 = line2.split(";");
                String sql = "insert into salary values (?,?,?,?);";
                try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, test2[0]);
                    stmt.setString(2, test2[2]);
                    stmt.setString(3, test2[3]);
                    stmt.setString(4, test2[4]);
                    stmt.execute();
                    ResultSet keys = stmt.getGeneratedKeys();
                    System.out.println(keys);
                    if (keys.next()) rv = keys.getInt(1);
                }
            }
            try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    L = res.getInt(1);
                }
            }
            int M = L - K;
            System.out.println(L + " ; " + K);
            return M;
        }
        return 0;
    }

    /**
     *  methode voor een check op bestaan van ID of EMPOYEE_ID
     * * @author (Gerjan)
     * * @version (23-09-2019)
     */

    protected static Boolean checkIDorEmplo_id(String Value, String Id) throws SQLException {
        boolean oke = false;
        Long ID;
        String sql8 = "select " + Id + " from employees where " + Id + " like '" + Value.replaceAll(" ", "") + "';";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql8, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println(sql8);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                ID = res.getLong(1);
                if (ID.toString().equals(Value.replaceAll(" ", ""))) {
                    oke = true;
                }
            }
        }
        return oke;
    }

    /**
     *  methode voor een insert naar database vanuit de senddata uploader.
     * * @author (Gerjan)
     * * @version (21-09-2019)
     */

    protected static int addsenddata(List lines) throws SQLException {
        boolean allelinenoke = false;
        String[] test;
        String[] test2;
        test = new String[15];
        int K = 0; //totaal aantal records
        int L = 0;
        int rv = -1;
        long key = -1L;
        Double employer_id = 0.0;
        String sql2 = "SELECT COUNT(socialsecurity_id) FROM employees;";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                K = res.getInt(1);
            }
        }

            Iterator it2 = lines.iterator();
            while (it2.hasNext()) {
                String line2 = (String) it2.next();
                test2 = line2.split(";");
                String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?);";
                try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, test2[0]);
                    stmt.setString(2, test2[1]);
                    stmt.setString(3, test2[5]);
                    stmt.setString(4, test2[6]);
                    stmt.setString(5, test2[7]);
                    stmt.setString(6, test2[8]);
                    stmt.setString(7, test2[9]);
                    stmt.setString(8, test2[10]);
                    stmt.setString(9, test2[11]);
                    stmt.setString(10, test2[12]);
                    //stmt.setString(11, test2[13]);
                    stmt.execute();
                    ResultSet keys = stmt.getGeneratedKeys();
                    System.out.println(keys);
                    if (keys.next()) rv = keys.getInt(1);
                }

            try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    L = res.getInt(1);
                }
            }
            int M = L - K;
            System.out.println(L + " ; " + K);
            return M;
        }
        return 0;
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


        if ((int) usr == results.get(0).security_id && psw.equals(results.get(0).password)) {
            System.out.println("chkLogin-Id: " + results.get(0).security_id);
            System.out.println("chkLogin-psw: " + results.get(0).password);
            return oke = "Combinatie gebruiker en wachtwoord is correct";
        } else {
            return oke = "Combinatie gebruiker en wachtwoord is fout!";
        }
    }
        return oke;
    }


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

   // }

}

