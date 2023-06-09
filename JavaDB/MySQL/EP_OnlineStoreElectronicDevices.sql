-- 1 DDL
DROP DATABASE online_store;

CREATE DATABASE online_store;
USE online_store;

CREATE TABLE brands (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews (
id INT PRIMARY KEY AUTO_INCREMENT,
content TEXT,
rating DECIMAL(10, 2) NOT NULL,
picture_url VARCHAR(80) NOT NULL,
published_at DATETIME NOT NULL
);

CREATE TABLE products (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
price DECIMAL(19, 2) NOT NULL,
quantity_in_stock INT,
`description` TEXT,
brand_id INT NOT NULL,
category_id INT NOT NULL,
review_id INT,
CONSTRAINT fk_products_brand
FOREIGN KEY (brand_id) REFERENCES brands(id),
CONSTRAINT fk_products_category
FOREIGN KEY (category_id) REFERENCES categories(id),
CONSTRAINT fk_products_review
FOREIGN KEY (review_id) REFERENCES reviews(id)
);

CREATE TABLE customers (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone VARCHAR(30) NOT NULL UNIQUE,
address VARCHAR(60) NOT NULL,
discount_card BIT(1) NOT NULL DEFAULT 0
);

CREATE TABLE orders (
id INT PRIMARY KEY AUTO_INCREMENT,
order_datetime DATETIME NOT NULL,
customer_id INT NOT NULL,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE orders_products (
order_id INT,
product_id INT,
CONSTRAINT fk_orders_products_orders
FOREIGN KEY (order_id) REFERENCES orders(id),
CONSTRAINT fk_orders_products_products
FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 2 Insert

USE online_store;

INSERT INTO reviews (content, picture_url, published_at, rating)
(SELECT 
	substring(p.description, 1, 15),
    reverse(name),
    '2010-10-10',
    price / 8
FROM products AS p
WHERE id >= 5);

-- 3 Update

UPDATE products
SET quantity_in_stock = quantity_in_stock -5
WHERE quantity_in_stock BETWEEN 60 AND 70;

-- 4 Delete

DELETE FROM customers
WHERE id NOT IN (
	SELECT o.customer_id
	FROM orders AS o
	);
 
-- 5 Categories

SELECT * 
FROM categories
ORDER BY name DESC;

-- 6 Quantity

SELECT 
	id,
    brand_id,
    name,
    quantity_in_stock
FROM products
WHERE price > 1000 AND quantity_in_stock < 30
ORDER BY quantity_in_stock ASC, id ASC;

-- 7 Review

SELECT *
FROM reviews
WHERE content LIKE 'My%' 
	AND char_length(content) > 61
ORDER BY rating DESC;

-- 8 First customers

SELECT 
	concat(first_name, ' ', last_name) AS full_name,
    c.address,
    o.order_datetime
FROM customers AS c
JOIN orders AS o ON o.customer_id = c.id
WHERE year(o.order_datetime) <= 2018
ORDER BY `full_name` DESC;

-- 9 Best categories

SELECT 
	count(p.id) AS items_count,
    c.name,
    sum(p.quantity_in_stock) AS total_quantity
FROM categories AS c
JOIN products AS p ON p.category_id = c.id
GROUP BY c.id
ORDER BY items_count DESC, total_quantity ASC
LIMIT 5;

-- 10 Extract client cards count

DELIMITER $$
CREATE FUNCTION udf_customer_products_count(`name` VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (
		SELECT 
			count(op.product_id)
		FROM customers AS c
		JOIN orders AS o ON o.customer_id = c.id
		JOIN orders_products AS op ON op.order_id = o.id
		WHERE first_name = `name`
		GROUP BY c.id);
END $$
DELIMITER ;

SELECT udf_customer_products_count('Shirley');

DROP FUNCTION udf_customer_products_count;

SELECT 
	count(op.product_id)
FROM customers AS c
JOIN orders AS o ON o.customer_id = c.id
JOIN orders_products AS op ON op.order_id = o.id
WHERE first_name = 'Shirley'
GROUP BY c.id
;

-- 11 Reduce price

DELIMITER $$
CREATE PROCEDURE udp_reduce_price(`category_name` VARCHAR(50))
BEGIN
UPDATE products AS p1
SET p1.price = p1.price * 0.7
	WHERE p1.category_id IN ( 
				SELECT c.id
				FROM categories AS c
				WHERE c.name = `category_name`
				)
		AND p1.review_id IN (
				SELECT r.id
				FROM reviews AS r
				WHERE r.rating < 4
				)
	;
END $$
DELIMITER ;

DROP PROCEDURE udp_reduce_price;

CALL udp_reduce_price('Phones and tablets');

SELECT *
FROM categories AS c
	JOIN products AS p ON p.category_id = c.id
	JOIN reviews AS r ON r.id = p.review_id
WHERE r.rating < 4 AND c.name = 'Phones and tablets';

