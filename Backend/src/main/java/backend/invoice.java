package backend;

import java.util.Date;

/**
 pojo class invoice

 Table: invoice
 Columns:
 employer_id double
 invoice_id double PK
 invoice_period varchar(255)
 start_date date
 calculating_date date
 @author (Teo)
 @version (15-08-2019)
 */

public class invoice {
        public double employer_id;
        public double invoice_id;
        public String invoice_period;
        public Date start_date;
        public Date calculating_date;
}