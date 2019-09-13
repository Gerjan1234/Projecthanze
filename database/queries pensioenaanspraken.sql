use pensioenaanspraken;
SELECT * FROM security;

SELECT * FROM security WHERE security.security_id = '2369302';

-- zoek adres gegevens werkgever op ID werkgever--
select company_name,government_id,communication_type,street_name,street_number,postal_code,city
from employers join adress where employers.employer_id = '2369302' and employers.adress_id = adress.adress_id;

-- zoek adres gegevens werknemer op ID werknemer--
select socialsecurity_id,employer_id,first_name,last_name,street_name,street_number,postal_code,city
from employees join adress where employees.socialsecurity_id = '510640555' and employees.adress_id = adress.adress_id;

-- zoek adres gegevens alle werknemers op ID werkgever--
select employer_id,first_name,last_name,street_name,street_number,postal_code,city
from employees join adress where employees.employer_id = '2369302' and employees.adress_id = adress.adress_id;

-- aantal werknemers per werkgever --
SELECT employer_id , count(employer_id) as `count`
FROM pensioenaanspraken.employees
GROUP BY employer_id;

-- toevoegen nieuwe invoice --
INSERT INTO `pensioenaanspraken`.`invoice` (`employer_id`, `invoice_period`, `start_date`, `calculating_date`) VALUES ('2580324', 'K', '2019-12-12', '2019-12-13');

-- Werkgevers zonder personeel -- 
SELECT employers.employer_id
FROM employers LEFT JOIN employees ON employers.employer_id = employees.employer_id
GROUP BY employers.employer_id, employees.socialsecurity_id
HAVING (((Count(employees.socialsecurity_id))=0))
ORDER BY employers.employer_id;

-- werkgevers zonder personeel-2 --
SELECT employers.employer_id, employers.company_name, employers.adress_id, employers.government_id, employers.communication_type
FROM employers LEFT JOIN employees ON employers.employer_id = employees.employer_id
GROUP BY employers.employer_id, employees.socialsecurity_id, employers.company_name, employers.adress_id, employers.government_id, employers.communication_type
HAVING (((Count(employees.socialsecurity_id))=0))
ORDER BY employers.company_name;

-- werknemers zonder werkgever (werkgeverid komt niet voor in employers maar wel in employees) --
SELECT employees.socialsecurity_id, employees.employer_id, employees.first_name, employees.last_name, employees.date_of_birth, employees.status, employees.gender, employees.adress_id, employees.communication_type, employees.hire_date
FROM employers RIGHT JOIN employees ON employers.employer_id = employees.employer_id
GROUP BY employers.employer_id, employees.socialsecurity_id, employees.employer_id, employees.first_name, employees.last_name, employees.date_of_birth, employees.status, employees.gender, employees.adress_id, employees.communication_type, employees.hire_date
HAVING (((Count(employers.employer_id))=0));

-- verwijderen regel uit invoice -- 
DELETE FROM `pensioenaanspraken`.`invoice` WHERE (`invoice_id` = '1385');

-- werknemers op invoice nr -- 
SELECT employers.employer_id, employers.company_name, salary.invoice_id, employees.employer_id, employees.first_name, employees.last_name, employees.date_of_birth, salary.salary, salary.parttime_factor, salary.max_pension_salary, salary.Franchise
FROM (salary INNER JOIN (employers INNER JOIN invoice ON employers.employer_id = invoice.employer_id) ON salary.invoice_id = invoice.invoice_id) INNER JOIN employees ON salary.socialsecurity_id = employees.socialsecurity_id
WHERE (((salary.invoice_id)=103))
ORDER BY employers.employer_id, employees.last_name;

-- invoices van een werkgever met een startdatum in een bepaald jaar --
SELECT invoice.start_date, employers.employer_id, employers.company_name, salary.invoice_id, employees.employer_id, employees.first_name, employees.last_name, employees.date_of_birth, salary.salary, salary.parttime_factor, salary.max_pension_salary, salary.Franchise
FROM (salary INNER JOIN (employers INNER JOIN invoice ON employers.employer_id = invoice.employer_id) ON salary.invoice_id = invoice.invoice_id) INNER JOIN employees ON salary.socialsecurity_id = employees.socialsecurity_id
WHERE (((invoice.start_date) Like '%2016%') AND ((employers.employer_id)=2369302))
ORDER BY employers.employer_id, salary.invoice_id, employees.last_name;

-- invoices van een werkgever  met een startdatum in alle jaren --
SELECT invoice.start_date, employers.employer_id, employers.company_name, salary.invoice_id, employees.employer_id, employees.first_name, employees.last_name, employees.date_of_birth, salary.salary, salary.parttime_factor, salary.max_pension_salary, salary.Franchise
FROM (salary INNER JOIN (employers INNER JOIN invoice ON employers.employer_id = invoice.employer_id) ON salary.invoice_id = invoice.invoice_id) INNER JOIN employees ON salary.socialsecurity_id = employees.socialsecurity_id
WHERE (((invoice.start_date) Like '%') AND ((employers.employer_id)=2369302))
ORDER BY employers.employer_id, salary.invoice_id, employees.last_name;

-- test premie --
SELECT salary.socialsecurity_id, salary.invoice_id, salary.salary, salary.parttime_factor, salary.Franchise, 
truncate((salary*parttime_factor)-franchise,2) AS Premie
FROM salary;

-- test premie-2--
SELECT salary.socialsecurity_id, salary.invoice_id, salary.salary, salary.parttime_factor, salary.Franchise, truncate(((salary/parttime_factor)-franchise),2) AS premie
FROM salary
WHERE (((salary.invoice_id)=103));

-- test leeftijd --
SELECT employees.socialsecurity_id, employees.first_name, employees.last_name, employees.date_of_birth, truncate((datediff(Now(),date_of_birth)/'365,25'),0) AS Leeftijd, 
year(date_of_birth) AS jaar, month(date_of_birth) AS mnd, day(date_of_birth) AS dag
FROM employees
WHERE (((employees.date_of_birth) Like "%1962%"));

-- Geeft de leeftijd van vandaag() en contributiepercentage weer per bsn nummer--
SELECT employees.socialsecurity_id, employees.first_name, employees.last_name, employees.date_of_birth, truncate((datediff(Now(),date_of_birth)/'365,25'),0) AS Leeftijd, contribution.contribution_percentage
FROM employees, contribution
WHERE (((employees.socialsecurity_id) Like "%113%") AND ((contribution.contribution_age)=(truncate((datediff(Now(),date_of_birth)/'365,25'),0))))
ORDER BY employees.last_name;

