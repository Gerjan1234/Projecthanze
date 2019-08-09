package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    static String HOST_NAME = "jdbc:mysql://localhost:3306/hoeheetdetabel?serverTimezone=EST";//hoe heet de tabel
    static String USER_NAME = "hanze";
    static String PASSWORD = "hanze";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
