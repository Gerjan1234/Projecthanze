package backend;

/**
 pojo class security

 Table: security
 Columns:
 security_id double
 password varchar(12)
 mailadress varchar(255)
 @author (Teo)
 @version (15-08-2019)
 */

public class security {
        // voor sql
        public double security_id;
        public String password;
        public String mailadress;

        //voor chk wie is ingelogd
        public static String IngelogdNaam = "nietingelogd";
        public static Double IngelogdID = 9999.99;
}







