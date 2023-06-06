CREATE DATABASE restaurant_db;
USE restaurant_db;

CREATE TABLE products (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (30) NOT NULL UNIQUE,
type VARCHAR (30) NOT NULL,
price DECIMAL(10, 2) NOT NULL);

CREATE TABLE clients (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
card VARCHAR(50),
review TEXT (5000)
);

CREATE TABLE `tables` (
id INT PRIMARY KEY AUTO_INCREMENT,
floor INT NOT NULL,
reserved TINYINT(1),
capacity INT NOT NULL
);

ALTER TABLE `tables`
MODIFY COLUMN reserved TINYINT(1);

CREATE TABLE waiters (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR(50),
salary DECIMAL(10, 2)
);

CREATE TABLE orders (
id INT PRIMARY KEY AUTO_INCREMENT,
table_id INT NOT NULL,
waiter_id INT NOT NULL,
order_time TIME NOT NULL,
payed_status TINYINT(1),
CONSTRAINT fk_orders_waiters
FOREIGN KEY (waiter_id) REFERENCES waiters(id),
CONSTRAINT fk_orders_tables
FOREIGN KEY (table_id) REFERENCES tables(id)
);

ALTER TABLE `orders`
MODIFY COLUMN payed_status TINYINT(1);

CREATE TABLE orders_clients (
order_id INT,
client_id INT,
-- CONSTRAINT pk_orders_clients
-- PRIMARY KEY (order_id, client_id),
CONSTRAINT fk_orders_clients_order_id_orders_id
FOREIGN KEY (order_id) REFERENCES orders(id),
CONSTRAINT fk_orders_clients_client_id_clients_id
FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE orders_products (
order_id INT,
product_id INT,
-- CONSTRAINT pk_orders_products
-- PRIMARY KEY (order_id, product_id),
CONSTRAINT fk_orders_products_order_id_orders_id
FOREIGN KEY (order_id) REFERENCES orders(id),
CONSTRAINT fk_orders_products_priduct_id_products_id
FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 2 Insert

INSERT INTO products (`name`, `type`, `price`)
SELECT 
	concat(w.last_name, ' ', 'specialty'),
	'Cocktail',
    ceiling(w.salary * 0.01)
FROM waiters AS w 
WHERE w.id > 6;

-- 3 Update

UPDATE orders
SET table_id = table_id -1
WHERE id BETWEEN 12 AND 23;

-- 4 Delete

DELETE FROM waiters
WHERE id NOT IN (
			SELECT o.waiter_id
			FROM orders AS o
			);
            
-- 5 Clients

DROP DATABASE restaurant_db;

SELECT *
FROM clients
ORDER BY birthdate DESC, id DESC;

-- 6 Birthdate

SELECT 
	first_name,
    last_name,
    birthdate,
    review
FROM clients
WHERE card IS NULL 
	AND (year(birthdate) >= 1978 AND year(birthdate) <= 1993)
ORDER BY last_name DESC, id ASC
LIMIT 5;

-- 7 Accounts

SELECT 
	concat(last_name, first_name, char_length(first_name), 'Restaurant') AS username,
	reverse(substring(email, 2, 12)) AS `password`
FROM waiters
WHERE salary IS NOT NULL
ORDER BY `password` DESC;

-- 8 Top from menu

SELECT 
	p.id,
    p.name,
    count(op.product_id) AS 'count'
FROM products AS p
JOIN orders_products AS op ON op.product_id = p.id
GROUP BY p.id
HAVING `count` >= 5
ORDER BY `count` DESC, name ASC;

-- 9 Availability

SELECT 
	t.id AS table_id,
    t.capacity,
    count(client_id) AS count_clients,
    (
    CASE 
		WHEN count(client_id) = capacity THEN 'Full'
        WHEN count(client_id) < capacity THEN 'Free seats'
        ELSE 'Extra seats'
	END 
    ) AS availability
FROM tables AS t
JOIN orders AS o ON o.table_id = t.id
JOIN orders_clients AS oc ON oc.order_id = o.id
WHERE floor = 1
GROUP BY table_id
ORDER BY t.id DESC;

-- 10 Extract bill

DELIMITER $$
CREATE FUNCTION udf_client_bill (full_name VARCHAR(50))
RETURNS DECIMAL (19, 2)
DETERMINISTIC
BEGIN
	RETURN (
		SELECT 
			sum(p.price) AS bill
		FROM clients AS c
		JOIN orders_clients AS oc ON oc.client_id = c.id
		JOIN orders AS o ON o.id = oc.order_id
		JOIN orders_products AS op ON op.order_id = o.id
		JOIN products AS p ON p.id = op.product_id
		WHERE concat(c.first_name, ' ', c.last_name) = full_name
		GROUP BY c.id
	);
END $$
DELIMITER ;

SELECT c.first_name,c.last_name, udf_client_bill('Silvio Blyth') as 'bill' FROM clients c
WHERE c.first_name = 'Silvio' AND c.last_name= 'Blyth'; 

SELECT 
    sum(p.price) AS bill
FROM clients AS c
JOIN orders_clients AS oc ON oc.client_id = c.id
JOIN orders AS o ON o.id = oc.order_id
JOIN orders_products AS op ON op.order_id = o.id
JOIN products AS p ON p.id = op.product_id
WHERE c.first_name = 'Silvio'
GROUP BY c.id;

-- 11 Happy hour

DELIMITER $$
CREATE PROCEDURE udp_happy_hour(prod_type VARCHAR(50))
BEGIN
	UPDATE products
	SET price = price * 0.8
	WHERE `type` = prod_type 
		AND price >= 10;
END $$
DELIMITER ;

CALL udp_happy_hour('Cognac');

SELECT *
FROM products
WHERE type = 'Cognac' AND price >= 10;
