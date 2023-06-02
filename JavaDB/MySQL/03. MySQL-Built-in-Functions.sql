        
    -- Lab 1. Find Book Titles
    
    USE book_library;
    
    SELECT title 
    FROM books
    WHERE substring(title, 1, 3) = 'The';
    
    -- Lab 2. Replace Titles
       
    SELECT replace(title, 'The', '***') AS title
    FROM books
    WHERE substr(title, 1, 3) = 'The';
    
    -- Lab 3. Sum Cost of All Books
    
    SELECT round(sum(cost), 2)
    FROM books;
    
    -- Lab 4. Days Lived
    
    SELECT 
		concat_ws(' ', first_name, last_name) AS 'Full Name',
		timestampdiff(day, born, died) AS `Days Lived`
    FROM authors;
    
    -- Lab 5. Harry Potter Books
    
    SELECT title
    FROM books
    WHERE title LIKE 'Harry Potter%';
    
    DROP DATABASE book_library;
    
    
    -- Ex. 1. Find Names of All Employees by First Name
    
USE soft_uni;
    
SELECT first_name, last_name
FROM employees
WHERE first_name LIKE 'Sa%'
ORDER BY employee_id;

-- Ex. 2. Find Names of All Employees by Last Name

SELECT first_name, last_name
FROM employees
WHERE last_name LIKE '%ei%';

-- Ex. 3. Find First Names of All Employees

SELECT first_name
FROM employees
WHERE 
	(department_id = 3 OR department_id = 10) AND
    (extract(year from hire_date) BETWEEN 1995 AND 2005)
ORDER BY employee_id;

-- Ex. 4. Find All Employees Except Engineers

SELECT first_name, last_name
FROM employees
WHERE lower(job_title) NOT LIKE '%engineer%'
ORDER BY employee_id;

-- Ex. 5. Find Towns with Name Length

SELECT name 
FROM towns
WHERE character_length(name) = 5 OR character_length(name) = 6
ORDER BY `name` ASC;

-- Ex. 6. Find Towns Starting With

SELECT *
FROM towns
WHERE name REGEXP '^[MKBE]'
ORDER BY name;

-- Ex. 7. Find Towns Not Starting With

SELECT *
FROM towns
WHERE name NOT REGEXP '^[RBD]'
ORDER BY name;

-- Ex. 8. Create View Employees Hired After 2000 Year

CREATE VIEW v_employees_hired_after_2000
	AS SELECT *
		FROM employees
		WHERE year(hire_date)  > 2000;

SELECT *
FROM employees
WHERE extract(YEAR from hire_date)  > 2000;

ALTER VIEW v_employees_hired_after_2000
AS SELECT first_name, last_name
		FROM employees
		WHERE year(hire_date)  > 2000;
	
-- Ex. 9. Length of Last Name

SELECT first_name, last_name
FROM employees
WHERE character_length(last_name) = 5; 

-- Ex. 10. Countries Holding 'A' 3 or More Times

USE geography;

SELECT country_name, iso_code
FROM countries
WHERE lower(country_name) LIKE '%a%a%a%'
ORDER BY iso_code;

-- Ex. 11. Mix of Peak and River Names

SELECT *
FROM rivers;

SELECT *
FROM peaks;

SELECT peak_name, river_name, (concat(lower(peak_name), lower(substring(river_name, 2)))) AS mix
FROM (peaks, rivers)
WHERE substring(peak_name, -1) = substring(lower(river_name), 1, 1)
ORDER BY mix;

-- Ex. 12. Games from 2011 and 2012 Year

USE diablo;

SELECT `name`, date_format(`start`, '%Y-%m-%d') AS `start`
FROM games
WHERE year(start) BETWEEN 2011 AND 2012
ORDER BY `start` ASC, name ASC
LIMIT 50;

-- Ex. 13. User Email Providers

SELECT user_name, substring(email, locate('@', email) +1 ) AS 'email provider'
FROM users
ORDER BY `email provider`, user_name;

-- Ex. 14. Get Users with IP Address Like Pattern

SELECT user_name, ip_address
FROM users
-- WHERE ip_address REGEXP '^([0-9]{3}\.1[0-9]{0,2}\.[0-9]{0,3}\.[0-9]{3}$)'
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

-- Ex. 15. Show All Games with Duration and Part of the Day

SELECT 
	`name` AS `game`, 
    CASE
		WHEN hour(`start`) BETWEEN 0 AND 11 THEN 'Morning'
		WHEN hour(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
		WHEN hour(`start`) BETWEEN 18 AND 23 THEN 'Evening'
        END AS `Part of the Day`,
   if(
    `duration` >= 0 AND `duration` <= 3, 'Extra Short',
		if(`duration` >3 AND `duration` <= 6, 'Short', 
			if(`duration` > 6 AND `duration` <= 10, 'Long', 
				'Extra Long')
			)
    ) AS `Duration`
FROM games;


-- Part IV – Date Functions Queries 

-- Ex. 16. Orders Table

SELECT 
	product_name, 
    order_date,
    timestampadd(DAY, 3, order_date) AS pay_due,
    date_add(order_date, INTERVAL 1 MONTH) as deliver_due
FROM orders;


