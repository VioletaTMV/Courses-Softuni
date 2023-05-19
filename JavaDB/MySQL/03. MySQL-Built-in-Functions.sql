CREATE DATABASE book_library;
USE book_library;

CREATE TABLE authors (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL,
	middle_name VARCHAR(30),
	last_name VARCHAR(30) NOT NULL,
	born DATETIME NOT NULL,
	died DATETIME
);

INSERT INTO authors(id,first_name, middle_name, last_name, born, died) VALUES
	(1,'Agatha', 'Mary Clarissa','Christie', '1890-09-15', '1976-01-12'),
	(2,'William', NULL,'Shakespeare', '1564-04-26', '1616-04-23'),
	(3,'Danielle', 'Fernandes Dominique', 'Schuelein-Steel', '1947-07-14', NULL),
	(4,'Joanne', NULL,'Rowling' , '1965-07-31', NULL),
	(5,'Lev', 'Nikolayevich', 'Tolstoy', '1828-09-09', '1910-11-20'),
	(6,'Paulo', 'Coelho de', 'Souza', '1947-08-24', NULL),
	(7,'Stephen', 'Edwin', 'King', '1947-09-21', NULL),
	(8,'John', 'Ronald Reuel', 'Tolkien', '1892-01-03', '1973-09-02'),
	(9,'Erika', NULL, 'Mitchell', '1963-03-07', NULL);
	
CREATE TABLE books (
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	author_id INT NOT NULL,
	year_of_release datetime,
	cost DOUBLE NOT NULL,
	CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO books(author_id,title, year_of_release,cost) VALUES
	(1,'Unfinished Portrait', '1930-01-01', 15.99),
	(1,'The Mysterious Affair at Styles', '1920-01-01',17.99),
	(1,'The Big Four', '1927-01-01',14.99),
	(1,'The Murder at the Vicarage', '1930-01-01',13.99),
	(1,'The Mystery of the Blue Train', '1928-01-01',12.99),
	(2,'Julius Caesar', '1599-01-01',11.99),
	(2,'Timon of Athens', '1607-01-01',13.99),
	(2,'As You Like It', '1600-01-01',18.99),
	(2,'A Midsummer Night\'s Dream', '1595-01-01',15.99),
	(3,'Going Home', '1973-01-01',15.99),
	(3,'The Ring', '1980-01-01',14.99),
	(3,'Secrets', '1985-01-01',15.99),
	(3,'Message From Nam', '1990-01-01',13.99),
	(4,'Career of Evil', '2015-01-01',15.99),
	(4, 'Harry Potter and the Philosopher\'s Stone','1997-01-01',19.99),
	(4,'Harry Potter and the Chamber of Secrets','1998-01-01',19.99),
	(4,'Harry Potter and the Prisoner of Azkaban','1999-01-01',19.99),
	(4,'Harry Potter and the Goblet of Fire','2000-01-01',19.99),
	(4,'Harry Potter and the Order of the Phoenix','2003-01-01',19.99),
	(4,'Harry Potter and the Half-Blood Prince','2005-01-01',19.99),
	(4,'Harry Potter and the Deathly Hallows','2007-01-01',19.99),
	(4,'Harry Potter and the Deathly Hallows','2007-01-01',15.99),
	(5,'Anna Karenina','1877-01-01',15.99),
	(5,'War And Peace','1869-01-01',30),
	(5,'Boyhood','1854-01-01',15.99),
	(6,'By the River Piedra I Sat Down and Wept','1994-01-01',15.99),
	(6,'The Alchemist','1988-01-01',15.99),
	(6,'The Fifth Mountain','1996-01-01',15.99),
	(6,'The Zahir','2005-01-01',15.99),
	(7,'Rage','1977-01-01',13.99),
	(7,'The Dead Zone','1979-01-01',13.99),
	(7,'It','1986-01-01',13.99),
	(7,'It','1986-01-01',13.99),	
	(8,'The Hobbit','1937-01-01',20.99),	
	(8,'The Adventures of Tom Bombadil','1962-01-01',13.99),	
	(9,'Fifty Shades of Grey','2011-01-01',13.99),	
	(9,'Fifty Shades Darker','2012-01-01',13.99),	
	(9,'Fifty Shades Freed','2012-01-01',13.99);
    
        
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

CREATE DATABASE orders;

USE orders;

CREATE TABLE orders
(
id INT NOT NULL,
product_name VARCHAR(50) NOT NULL,
order_date DATETIME NOT NULL,
CONSTRAINT pk_orders PRIMARY KEY (id)
);

INSERT INTO orders (id, product_name, order_date) VALUES (1, 'Butter', '20160919');
INSERT INTO orders (id, product_name, order_date) VALUES (2, 'Milk', '20160930');
INSERT INTO orders (id, product_name, order_date) VALUES (3, 'Cheese', '20160904');
INSERT INTO orders (id, product_name, order_date) VALUES (4, 'Bread', '20151220');
INSERT INTO orders (id, product_name, order_date) VALUES (5, 'Tomatoes', '20150101');
INSERT INTO orders (id, product_name, order_date) VALUES (6, 'Tomatoe2', '20150201');
INSERT INTO orders (id, product_name, order_date) VALUES (7, 'Tomatoess', '20150401');
INSERT INTO orders (id, product_name, order_date) VALUES (8, 'Tomatoe3', '20150128');
INSERT INTO orders (id, product_name, order_date) VALUES (9, 'Tomatoe4', '20150628');
INSERT INTO orders (id, product_name, order_date) VALUES (10, 'Tomatoe44s', '20150630');
INSERT INTO orders (id, product_name, order_date) VALUES (11, 'Tomatoefggs', '20150228');
INSERT INTO orders (id, product_name, order_date) VALUES (12, 'Tomatoesytu', '20160228');
INSERT INTO orders (id, product_name, order_date) VALUES (13, 'Toyymatddoehys', '20151231');
INSERT INTO orders (id, product_name, order_date) VALUES (14, 'Tom443atoes', '20151215');
INSERT INTO orders (id, product_name, order_date) VALUES (15, 'Tomat65434foe23gfhgsPep', '20151004');

-- Ex. 16. Orders Table

SELECT 
	product_name, 
    order_date,
    timestampadd(DAY, 3, order_date) AS pay_due,
    date_add(order_date, INTERVAL 1 MONTH) as deliver_due
FROM orders;


