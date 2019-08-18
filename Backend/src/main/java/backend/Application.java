package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * class Application
 * @author (Gerjan)
 * @version (09-08-2019)
 * Main met database parameters
 */

@SpringBootApplication
public class Application {
    static String HOST_NAME = "jdbc:mysql://localhost:3306/pensioenaanspraken?serverTimezone=SYSTEM";//hoe heet de tabel
    static String USER_NAME = "hanze";
    static String PASSWORD = "hanze";



    public static void main(String[] args) {SpringApplication.run(Application.class, args);
        OsCheck.getOperatingSystemType();
    }
}
