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
    public static String HOST_NAME = "jdbc:mysql://localhost:3306/pensioenaanspraken?serverTimezone=UTC";//hoe heet de tabel
    public static String USER_NAME = "hanze";
    public static String PASSWORD = "hanze";



    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class, args);
        OsCheck.getOperatingSystemType();
        OsCheck.setPathLocal();
    }
}