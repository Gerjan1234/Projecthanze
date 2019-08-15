package backend;

/**
 pojo class employers

 Table: employers
 Columns:
 employer_id double PK
 company_name varchar(255)
 adress_id int(11)
 government_id double
 communication_type varchar(255)
 @author (Teo)
 @version (15-08-2019)
 */

public class employers {
        public double employer_id;
        public String company_name;
        public int adress_id;
        public double government_id;
        public String communication_type;
    }
