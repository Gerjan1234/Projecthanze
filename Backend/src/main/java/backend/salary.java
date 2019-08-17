package backend;

import java.math.BigDecimal;

/**
 pojo class salary

 Table: salary
 Columns:
 socialsecurity_id double
 salary decimal(10,2)
 parttime_factor decimal(10,5)
 max_pension_salary decimal(10,2)
 Franchise decimal(10,2)
 @author (Teo)
 @version (15-08-2019)
 */

public class salary {
        public double socialsecurity_id;
        public BigDecimal salary;
        public BigDecimal parttime_factor ;
        public BigDecimal max_pension_salary;
        public double Franchise;
    }
