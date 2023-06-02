-- Lab 1. Mountains and Peaks

USE camp;

CREATE TABLE mountains (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR (50)
);

CREATE TABLE peaks (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR (50),
mountain_id INT,
CONSTRAINT fk_peaks_mountains
FOREIGN KEY (mountain_id) REFERENCES mountains (id)
);

-- Lab 2. Trip Organization

SELECT * FROM campers;
SELECT * FROM rooms;
SELECT * FROM routes;
SELECT * FROM vehicles;

SELECT
	driver_id,
    vehicle_type,
    concat(first_name, ' ', last_name)
FROM vehicles
 JOIN campers ON 
 	campers.id = vehicles.driver_id;
    
-- Lab. 3. SoftUni Hiking

SELECT 
	starting_point AS route_starting_point,
    end_point AS route_ending_point,
    leader_id,
   concat(first_name, ' ', last_name) AS leader_name
FROM routes
JOIN campers ON
	routes.leader_id = campers.id;
    
-- Lab. Delete Mountains

ALTER TABLE peaks
DROP CONSTRAINT fk_peaks_mountains;

ALTER TABLE peaks
ADD CONSTRAINT fk_peaks_mountains
FOREIGN KEY (mountain_id)
REFERENCES mountains (id)
on DELETE CASCADE;

-- Ex. 1. One-To-One Relationship

CREATE DATABASE table_relations;
USE table_relations;

CREATE TABLE people (
person_id INT NOT NULL,
first_name VARCHAR (30),
salary DOUBLE,
passport_id INT
);

CREATE TABLE passports (
passport_id INT,
passport_number varchar (10)
);

ALTER TABLE people
CHANGE COLUMN person_id
person_id INT NOT NULL AUTO_INCREMENT,
ADD CONSTRAINT pk_people_person_id
PRIMARY KEY (person_id);

ALTER TABLE passports
ADD CONSTRAINT pk_passports_id
PRIMARY KEY (passport_id);

ALTER TABLE people
ADD CONSTRAINT fk_people_passports
FOREIGN KEY (passport_id) REFERENCES passports (passport_id);

ALTER TABLE passports
CHANGE COLUMN passport_number
passport_number VARCHAR (10) UNIQUE;

ALTER TABLE passports
MODIFY COLUMN passport_number VARCHAR (10);
DROP INDEX passport_number ON passports;

ALTER TABLE people
MODIFY COLUMN salary DECIMAL (10,2);

ALTER TABLE people
CHANGE COLUMN passport_id
passport_id INT UNIQUE;

ALTER TABLE passports
CHANGE COLUMN passport_id
passport_id INT UNIQUE NOT NULL;

INSERT INTO passports (passport_id, passport_number)
VALUES (101, 'N34FG21B'), (102, 'K65LO4R7'), (103, 'ZE657QP2');

INSERT INTO people (first_name, salary, passport_id)
VALUES ('Roberto', 43300.00, 102), ('Tom', 56100.00, 103), ('Yana', 60200.00, 101);

-- Ex. 2. One-To-Many Relationship

CREATE TABLE manufacturers (
manufacturer_id INT NOT NULL,
name VARCHAR(30),
established_on DATE
);

CREATE TABLE models (
model_id INT UNIQUE NOT NULL,
name VARCHAR (30),
manufacturer_id INT
);

ALTER TABLE manufacturers
MODIFY manufacturer_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL;

ALTER TABLE models
MODIFY model_id INT PRIMARY KEY NOT NULL UNIQUE,
ADD CONSTRAINT fk_models_manufacturers
FOREIGN KEY (manufacturer_id) REFERENCES manufacturers (manufacturer_id);

INSERT INTO manufacturers (name, established_on)
VALUES ('BMW', '1916-03-01'), ('Tesla', '2003-01-01'), ('Lada', '1966-05-01');

INSERT INTO models (model_id, name, manufacturer_id)
VALUES (101, 'X1', 1), (102, 'i6', 1), (103, 'Model S', 2), (104, 'Model X', 2), (105, 'Model 3', 2), (106, 'Nova', 3);

-- Ex. 3. Many-To-Many Relationship

CREATE TABLE students (
student_id INT,
name VARCHAR (30)
);

CREATE TABLE exams (
exam_id INT,
name VARCHAR (30)
);

CREATE TABLE students_exams (
student_id INT,
exam_id INT
);

INSERT INTO students (student_id, name)
VALUES (1, 'Mila'), (2, 'Toni'), (3, 'Ron');

INSERT INTO exams (exam_id, name)
VALUES (101, 'Spring MVC'), (102, 'Neo4j'), (103, 'Oracle 11g');

INSERT INTO students_exams (student_id, exam_id)
VALUES (1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);

ALTER TABLE students
MODIFY COLUMN student_id INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE exams
MODIFY COLUMN exam_id INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE students_exams
-- ADD CONSTRAINT pk_students_exams
-- PRIMARY KEY (student_id, exam_id),
ADD CONSTRAINT fk_students_exams_studens
FOREIGN KEY (student_id) REFERENCES students (student_id),
ADD CONSTRAINT fk_students_exams_exams
FOREIGN KEY (exam_id) REFERENCES exams (exam_id);

ALTER TABLE students_exams
ADD CONSTRAINT pk_students_exams
PRIMARY KEY (student_id, exam_id);

-- извън задачата
SELECT s.name, exams.name
FROM students AS s
JOIN students_exams JOIN exams ON
students_exams.student_id = s.student_id AND
exams.exam_id = students_exams.exam_id
ORDER BY exams.name;

-- Ex 4. Self-Referencing

CREATE TABLE teachers(
teacher_id INT,
name varchar (30),
manager_id INT
);

INSERT INTO teachers (teacher_id, name, manager_id)
VALUES (101, 'John',NULL), (102, 'Maya', 106), (103, 'Silvia', 106), (104, 'Ted', 105),
(105, 'Mark', 101), (106, 'Greta', 101);

ALTER TABLE teachers
MODIFY teacher_id INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE teachers
ADD CONSTRAINT fk_teachers_manager_id_teacher_id
FOREIGN KEY (manager_id) REFERENCES teachers (teacher_id);

-- Ex 5. Online Store Database

CREATE TABLE item_type (
item_type INT(11) PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50)
);

ALTER TABLE item_type
CHANGE COLUMN item_type
item_type_id INT(11);

CREATE TABLE cities (
city_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50)
);

CREATE TABLE customers (
customer_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50),
birthday DATE,
city_id INT,
CONSTRAINT fk_customers_cities
FOREIGN KEY (city_id) REFERENCES cities (city_id)
);

CREATE TABLE orders (
order_id INT PRIMARY KEY AUTO_INCREMENT,
customer_id INT,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE items (
item_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50),
item_type_id INT(11),
CONSTRAINT fk_items_item_types
FOREIGN KEY (item_type_id) REFERENCES item_type (item_type_id)
);

RENAME TABLE item_type TO item_types;

CREATE TABLE order_items (
order_id INT,
item_id INT(11),
CONSTRAINT pk_order_items
PRIMARY KEY (order_id, item_id),
CONSTRAINT fk_order_items_items
FOREIGN KEY (item_id) REFERENCES items (item_id),
CONSTRAINT fk_order_items_orders
FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

-- Ex 6. University Database

CREATE DATABASE university;
USE university;

CREATE TABLE majors (
major_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50)
);

CREATE TABLE payments (
payment_id INT PRIMARY KEY AUTO_INCREMENT,
payment_date DATE,
payment_amount DECIMAL(8,2),
student_id INT
);

CREATE TABLE students (
student_id INT PRIMARY KEY AUTO_INCREMENT,
student_number VARCHAR (12),
student_name VARCHAR (50),
major_id INT,
CONSTRAINT fk_students_majors
FOREIGN KEY (major_id) REFERENCES majors (major_id)
);

ALTER TABLE payments
ADD CONSTRAINT fk_payments_students
FOREIGN KEY (student_id) REFERENCES students (student_id);

CREATE TABLE subjects (
subject_id INT PRIMARY KEY AUTO_INCREMENT,
subject_name VARCHAR (50)
);

CREATE TABLE agenda (
student_id INT,
subject_id INT,
CONSTRAINT pk_agenda_students_subjects
PRIMARY KEY (student_id, subject_id),
CONSTRAINT fk_agenda_students
FOREIGN KEY (student_id) REFERENCES students (student_id),
CONSTRAINT fk_agenda_subjects
FOREIGN KEY (student_id) REFERENCES subjects (subject_id)
);

ALTER TABLE agenda
DROP CONSTRAINT fk_agenda_subjects;

ALTER TABLE agenda
ADD CONSTRAINT fk_agenda_subjects
FOREIGN KEY (subject_id) REFERENCES subjects (subject_id);

-- Ex 9. Peaks in Rila

USE geography;

SELECT * FROM mountains;
SELECT * FROM peaks;

SELECT 
	mountain_range,
    peak_name,
    elevation AS 'peak_elevation'
FROM mountains
JOIN peaks ON
	peaks.mountain_id = mountains.id
WHERE mountain_range = 'Rila'
ORDER BY `peak_elevation` DESC;