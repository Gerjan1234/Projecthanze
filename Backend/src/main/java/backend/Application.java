package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.SQLException;

/**
 * class Application
 * @author (Gerjan)
 * @version (09-08-2019)
 * Main met database parameters
 */
@CrossOrigin(origins = "*")
@SpringBootApplication
public class Application {
    static String HOST_NAME = "jdbc:mysql://localhost:3306/pensioenaanspraken?serverTimezone=UTC";//hoe heet de tabel
    static String USER_NAME = "hanze";
    static String PASSWORD = "hanze";



    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class, args);
        OsCheck.getOperatingSystemType();
        OsCheck.setPathLocal();
        String Test = Database.chkInlog(82369017,"X2369017");
        System.out.println("Aplicatietest: " + Test);
    }
}