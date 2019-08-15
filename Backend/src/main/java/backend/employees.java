package backend;

import java.util.Date;

/**
 pojo class employees

 Table: employees
 Columns:
 socialsecurity_id double PK
 employer_id double
 first_name varchar(255)
 last_name varchar(255)
 date_of_birth date
 status varchar(255)
 gender varchar(255)
 adress_id int(11)
 communication_type varchar(255)
 hire_date date
 @author (Teo)
 @version (15-08-2019)
 */

public class employees {
        public double socialsecurity_id;
        public double employer_id;
        public String first_name;
        public String last_name;
        public Date date_of_birth;
        public String status;
        public String gender;
        public int adress_id;
        public String communication_type;
        public Date hire_date;
    }
