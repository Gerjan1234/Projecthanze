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

    private static String IngelogdNaam = "nietingelogd";
    private static Double IngelogdID = 9999.99;

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
            String sql3 = "select employer_id from employees where socialsecurity_id like '"+test[0]+"';";
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
     *  update tabel employees en salary
     * * @author (Gerjan)
     * * @version (21-09-2019)
     */

    protected static int addsenddata(List lines) throws SQLException {
        boolean allelinenoke = false;
        boolean bestaat_al = false;
        int tellerinsert = 0;
        int tellerupdate = 0;
        String[] test;
        int K = 0; //totaal aantal records
        int L = 0;
        Double employer_id = 0.0;
        String sql2 = "SELECT COUNT(socialsecurity_id) FROM employees;";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                K = res.getInt(1);
            }
        }
        System.out.println("stap voor de iteratr");
//chcek of socialid al bestaat:
        Iterator it = lines.iterator();
        int aantaloke = 0;
        while (it.hasNext()) {
            String line = (String) it.next();
            test = line.split(";");
            System.out.println(test);
            String sql3 = "select employer_id from employees where socialsecurity_id like '"+test[0]+"';";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    employer_id = res.getDouble(1);
                    if (employer_id.toString().substring(0, employer_id.toString().length() - 2).equals(test[1].replaceAll(" ", ""))) {
                        aantaloke = aantaloke + 1;
                        bestaat_al = false;
                        String sql = "update employees set first_name = ?, last_name = ?, date_of_birth = ?, status = ?, gender = ?, adress_id = ?, communication_type = ?, hire_date = ? where socialsecurity_id = ?;";
                        System.out.println("update gedaan");
                        try (PreparedStatement stmt3 = getConnection().prepareStatement(sql)) {
                            stmt3.setString(1, test[5]);
                            stmt3.setString(2, test[6]);
                            stmt3.setString(3, test[7]);
                            stmt3.setString(4, test[8]);
                            stmt3.setString(5, test[9]);
                            stmt3.setString(6, test[10]);
                            stmt3.setString(7, test[11]);
                            stmt3.setString(8, test[12]);
                            stmt3.setString(9, test[0]);
                            tellerupdate = stmt3.executeUpdate();
                        }
                    }
                }
            }

            String sql4 = "select employer_id from employees where socialsecurity_id like '"+test[0]+"';";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                if (res.next() == false) {
                        bestaat_al = true;
                        String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?);";
                        System.out.println("insert gedaan");
                        try (PreparedStatement stmt2 = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                            stmt2.setString(1, test[0]);
                            stmt2.setString(2, test[1]);
                            stmt2.setString(3, test[5]);
                            stmt2.setString(4, test[6]);
                            stmt2.setString(5, test[7]);
                            stmt2.setString(6, test[8]);
                            stmt2.setString(7, test[9]);
                            stmt2.setString(8, test[10]);
                            stmt2.setString(9, test[11]);
                            stmt2.setString(10, test[12]);
                            tellerinsert = stmt2.executeUpdate();
                    }

                }
            }
            try (PreparedStatement stmt = getConnection().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    L = res.getInt(1);
                }
            }
            String sql5 = "insert into salary values (?,?,?,?);";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql5, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, test[0]);
                stmt.setString(2, test[2]);
                stmt.setString(3, test[3]);
                stmt.setString(4, test[4]);
                stmt.execute();
            }
            int M = L - K;
            System.out.println(L + " ; " + K);
            System.out.println(tellerinsert + " en " + tellerupdate);
            if(bestaat_al == false) {
                return tellerupdate + tellerinsert;
            }else {
                return M;
            }

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
            setNameIngelogdAls(results.get(0).security_id); //vul de variabele met de naam van de gebruiker die is ingelogd
            return oke = "Combinatie gebruiker en wachtwoord is correct";
        } else {
            return oke = "Combinatie gebruiker en wachtwoord is fout!";
        }
    }
        return oke;
    }

    // hiermee worden 2 variabelen gevuld met de naam en id van de gebruiker die is ingelogd
    // deze naam og id kan bij alle html pagina's gebruikt worden of de gebruiker wel is ingelogd en wie dat dan is
    protected static void setNameIngelogdAls(Double gebruiker) throws SQLException  {

        String sql = "SELECT employers.company_name FROM security INNER JOIN employers ON security.security_id = employers.employer_id WHERE security.security_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, gebruiker);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                IngelogdNaam = res.getString(1);
                IngelogdID = gebruiker;
                }
        }
        System.out.println("naam van ingelogde persoon : " + IngelogdNaam);
        System.out.println("ID code van ingelogde persoon : " + IngelogdID);
    }
}

