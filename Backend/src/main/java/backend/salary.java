package backend;

import java.math.BigDecimal;

/**
 pojo class salary

 Table: salary
 Columns:
 socialsecurity_id double
 salary double
 parttime_factor varchar(255)
 max_pension_salary double
 Franchise double
 @author (Teo)
 @version (15-08-2019)
 */

public class salary {
        public double socialsecurity_id;
        public BigDecimal salary;
        parttime_factor varchar(255) // dit nog wijzigen in double ook in mysql script en model
        public BigDecimal max_pension_salary;
        public double Franchise;
    }
