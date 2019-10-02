package backend;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
    private static double MaxInvId = 0;
    private static invoice inv = new invoice();



    private static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(Application.HOST_NAME, Application.USER_NAME, Application.PASSWORD);
        }
        return conn;
    }

    private static void setInv(){

        inv.employer_id = security.IngelogdID;
        inv.invoice_id = MaxInvId;
        inv.invoice_period = "JAAR";
        inv.start_date = "2019-01-01";
        inv.calculating_date = "2019-12-31";
        inv.max_pension_salary = 107953.00;
        inv.franchise = 20209.00;
        inv.claim_percentage = 0.015;

        }

    /**
     * Methode afronden van een double
     *  @author (Teo)
     *  @version (28-09-2019)
     */

    public static double roundDBL(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;}

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
        setMaxInvoiceId();
        String StrInvID = Double.toString(MaxInvId);
        addInvoice();
        if (allelinenoke == true) {
            Iterator it2 = lines.iterator();
            while (it2.hasNext()) {
                String line2 = (String) it2.next();
                test2 = line2.split(";");
                String sql = "insert into salary values (?,?,?,?);";
                try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, test2[0]);
                    stmt.setString(2, StrInvID);
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
            setMaxInvoiceId();
            String StrInvID = Double.toString(MaxInvId);
            addInvoice();

            String sql5 = "insert into salary values (?,?,?,?);";
            try (PreparedStatement stmt = getConnection().prepareStatement(sql5, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, test[0]);
                stmt.setString(2, StrInvID);
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
     * Methode check inlognaam
     * * @author (Teo)
     * * @version (02-09-2019)
     */

    protected static String chkInlog(double usr, String psw) throws SQLException {
        ArrayList<security> results = new ArrayList<>();
        String oke = "Combinatie gebruiker en wachtwoord is fout !!";
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
            return oke = "Combinatie gebruiker en wachtwoord is fout !!";
        }
    }
        return oke;
    }

    /**
     * Methode set ingelogde gebruiker
     *  @author (Teo)
     *  @version (28-09-2019)
     *  hiermee worden 2 variabelen gevuld met de naam en id van de gebruiker die is ingelogd
     *  deze naam of id kan bij alle html pagina's gebruikt worden of de gebruiker wel is ingelogd en wie dat dan is
     */

    protected static void setNameIngelogdAls(Double gebruiker) throws SQLException  {

        String sql = "SELECT employers.company_name FROM security INNER JOIN employers ON security.security_id = employers.employer_id WHERE security.security_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, gebruiker);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                security.IngelogdNaam = res.getString(1);
                security.IngelogdID = gebruiker;
                }
        }
        System.out.println("naam van ingelogde persoon : " + security.IngelogdNaam);
        System.out.println("ID code van ingelogde persoon : " + security.IngelogdID);
    }


     /**
     * Methode aanspraken lijst maken
     *  @author (Teo)
     *  @version (28-09-2019)
     */

    protected static ArrayList<aanspraak> getAanspraken(double usr) throws SQLException {
        ArrayList<aanspraak> results = new ArrayList<>();
        String oke = "inittekst";
        System.out.println("Controlleruser = " + usr);

        String sql = "SELECT employees.socialsecurity_id, invoice.calculating_date, employees.first_name, employees.last_name, employees.date_of_birth, adress.street_name, adress.street_number, adress.postal_code, \n" +
                "        adress.city, salary.salary, salary.parttime_factor, invoice.franchise, (salary.salary/salary.parttime_factor-invoice.franchise)*salary.parttime_factor AS grondslag, \n" +
                "        ((salary.salary/salary.parttime_factor-invoice.franchise)*salary.parttime_factor)*invoice.claim_percentage AS aanspraak\n" +
                "        FROM adress INNER JOIN employers INNER JOIN employees ON employers.employer_id = employees.employer_id INNER JOIN invoice ON employers.employer_id = invoice.employer_id \n" +
                "        INNER JOIN salary ON (invoice.invoice_id = salary.invoice_id) AND (employees.socialsecurity_id = salary.socialsecurity_id) ON adress.adress_id = employees.adress_id\n" +
                "        WHERE (((employers.employer_id)=?))\n" +
                "        ORDER BY employees.socialsecurity_id, invoice.calculating_date;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, usr);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                aanspraak r = new aanspraak();

                r.socialsecurity_id = (int) res.getDouble(1);
                r.calculating_date = res.getDate(2);
                r.first_name = res.getString(3);
                r.last_name = res.getString(4);
                r.date_of_birth = res.getDate(5);
                r.street_name = res.getString(6);
                r.street_number = res.getInt(7);
                r.postal_code = res.getString(8);
                r.city = res.getString(9);
                r.salary = res.getDouble(10);
                r.parttime_factor = res.getDouble(11);
                r.franchise = res.getDouble(12);
                r.grondslag = roundDBL(res.getDouble(13),2);
                r.aanspraak = roundDBL(res.getDouble(14),2);


                results.add(r);
            }

        }
        int tst = results.size();
        System.out.println("Lengte van de retour array: " + tst);

        if (tst > 0) {
            System.out.println("sql resultaat Id: " + results.get(0).socialsecurity_id);
            System.out.println("sql resultaat achternaam: " + results.get(0).last_name);
            }

        return results;
    }


    /**
     * Methode premie lijst maken
     *  @author (Teo)
     *  @version (28-09-2019)
     */

    protected static ArrayList<premie> getPremies(double usr) throws SQLException {
        ArrayList<premie> results = new ArrayList<>();
        String oke = "inittekst";
        System.out.println("Controlleruser = " + usr);

        String sql = "SELECT employees.socialsecurity_id, invoice.calculating_date, employees.first_name, employees.last_name, employees.date_of_birth, adress.street_name, adress.street_number, adress.postal_code, \n" +
                "adress.city, salary.salary, salary.parttime_factor, invoice.franchise, ((salary.salary/salary.parttime_factor-invoice.franchise)*salary.parttime_factor)/4 AS jaarpremie, \n" +
                "((salary.salary/salary.parttime_factor-invoice.franchise)*salary.parttime_factor)/4/12 AS maandpremie\n" +
                "FROM adress \n" +
                "INNER JOIN employers \n" +
                "INNER JOIN employees ON employers.employer_id = employees.employer_id\n" +
                "INNER JOIN invoice ON employers.employer_id = invoice.employer_id\n" +
                "INNER JOIN salary ON (invoice.invoice_id = salary.invoice_id) AND (employees.socialsecurity_id = salary.socialsecurity_id) ON adress.adress_id = employees.adress_id\n" +
                "WHERE (((employers.employer_id)=?))\n" +
                "ORDER BY employees.socialsecurity_id, invoice.calculating_date;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, usr);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                premie r = new premie();

                r.socialsecurity_id = (int) res.getDouble(1);
                r.calculating_date = res.getDate(2);
                r.first_name = res.getString(3);
                r.last_name = res.getString(4);
                r.date_of_birth = res.getDate(5);
                r.street_name = res.getString(6);
                r.street_number = res.getInt(7);
                r.postal_code = res.getString(8);
                r.city = res.getString(9);
                r.salary = res.getDouble(10);
                r.parttime_factor = res.getDouble(11);
                r.franchise = res.getDouble(12);
                r.jaarpremie = roundDBL(res.getDouble(13),2);
                r.maandpremie = roundDBL(res.getDouble(14),2);


                results.add(r);
            }

        }
        int tst = results.size();
        System.out.println("Lengte van de retour array: " + tst);

        if (tst > 0) {
            System.out.println("sql resultaat Id: " + results.get(0).socialsecurity_id);
            System.out.println("sql resultaat achternaam: " + results.get(0).last_name);
        }

        return results;
    }

    /**
     * Methode werknemers lijst maken
     *  @author (Teo)
     *  @version (28-09-2019)
     */

    protected static ArrayList<werknemer> getWerknemers(double usr) throws SQLException {
        ArrayList<werknemer> results = new ArrayList<>();
        String oke = "inittekst";
        System.out.println("ControllerWerknemers = " + usr);

        String sql = "SELECT socialsecurity_id, first_name, last_name, date_of_birth, status, gender, communication_type, hire_date, street_name, street_number, postal_code, city\n" +
                "FROM employees\n" +
                "JOIN adress\n" +
                "WHERE employees.employer_id = ? and employees.adress_id = adress.adress_id\n" +
                "ORDER BY employees.last_name, employees.first_name;";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, usr);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                werknemer r = new werknemer();

                r.socialsecurity_id = res.getDouble(1);
                r.first_name = res.getString(2);
                r.last_name = res.getString(3);
                r.date_of_birth = res.getDate(4);
                r.status = res.getString(5);
                r.gender = res.getString(6);
                r.communication_type = res.getString(7);
                r.hire_date = res.getDate(8);
                r.street_name = res.getString(9);
                r.street_number = res.getInt(10);
                r.postal_code = res.getString(11);
                r.city = res.getString(12);

                results.add(r);
            }

        }
        int tst = results.size();
        System.out.println("Lengte van de retour array: " + tst);

        if (tst > 0) {
            System.out.println("sql resultaat Id: " + results.get(0).socialsecurity_id);
            System.out.println("sql resultaat achternaam: " + results.get(0).last_name);
        }

        return results;
    }

    protected static void setMaxInvoiceId() throws SQLException  {

        String sql = "SELECT max(invoice_id) from invoice;";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                MaxInvId = res.getDouble(1);
                MaxInvId += 1;
                setInv();

            }
        }
        System.out.println("MaxInvId : " + MaxInvId);
        System.out.println("Inv : " + inv.invoice_id);


    }

    protected static void addInvoice() throws SQLException  {

            String sqlInv = "insert into invoice values (?,?,?,?,?,?,?,?);";
            try (PreparedStatement stmt2 = getConnection().prepareStatement(sqlInv, Statement.RETURN_GENERATED_KEYS)) {
                
                stmt2.setDouble(1,inv.employer_id);
                stmt2.setDouble(2,inv.invoice_id);
                stmt2.setString(3,inv.invoice_period);
                stmt2.setString(4,inv.start_date);
                stmt2.setString(5,inv.calculating_date);
                stmt2.setDouble(6,inv.max_pension_salary);
                stmt2.setDouble(7,inv.franchise);
                stmt2.setDouble(8,inv.claim_percentage);
                stmt2.execute();
            }

        }
}

