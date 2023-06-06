-- Lab. 1. Count Employees by Town

DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(`town_name` VARCHAR(20))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE e_count INT;
    SET e_count := (SELECT count(*) FROM employees AS e 
						JOIN addresses AS a ON a.address_id = e.address_id
						JOIN towns AS t ON t.town_id = a.town_id
					WHERE `town_name` = t.name);
    RETURN e_count;
END$$
DELIMITER ;
;

SELECT ufn_count_employees_by_town('Sofia');
SELECT ufn_count_employees_by_town('Berlin');
	
DROP FUNCTION ufn_count_employees_by_town;

-- Lab. 2. Employees Promotion

DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR (50))
BEGIN
	UPDATE employees AS e
		JOIN departments AS d ON e.department_id = d.department_id
    SET salary = salary * 1.05
    WHERE d.name = department_name;
END$$
DELIMITER ;
;

SELECT employee_id, salary
FROM employees AS e
JOIN departments AS d
WHERE d.department_id = e.department_id;

CALL usp_raise_salaries('Sales');

-- Lab. 3. Employees Promotion by ID

DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	START TRANSACTION;
    UPDATE employees
    SET salary = salary * 1.05
    WHERE id = employees.employee_id;
    
		IF (id NOT IN (SELECT employee_id FROM employees) OR id = 1)
		THEN 
			ROLLBACK;
		-- ELSE
		--  	COMMIT;
		END IF;
END$$
DELIMITER ;
;

DROP PROCEDURE usp_raise_salary_by_id;

SELECT salary FROM employees AS e WHERE e.employee_id = 17;
CALL usp_raise_salary_by_id(17);

-- Lab. 4. Triggered

CREATE TABLE deleted_employees (
employee_id INT PRIMARY KEY AUTO_INCREMENT, 
first_name VARCHAR(50),
last_name VARCHAR (50),
middle_name VARCHAR(50),
job_title VARCHAR(50),
department_id INT,
salary DECIMAL (19, 4)
);


DROP trigger tr_deleted_employees;
DELIMITER $$
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON employees
FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees (first_name, last_name, middle_name, job_title, 
									department_id, salary)
	VALUES (OLD.first_name, OLD.last_name, OLD.middle_name, OLD.job_title, 
			OLD.department_id, OLD.salary);
END$$

DELIMITER ;
;

DELETE FROM employees
WHERE employee_id = 1;

UPDATE employees_projects
SET employee_id = 2
WHERE employee_id = 1;

SELECT * FROM employees;
SELECT * FROM deleted_employees;

-- Part I – Queries for SoftUni Database
-- Ex 1. Employees with Salary Above 35000

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT 
		first_name,
		last_name
	FROM employees AS e
	WHERE e.salary > 35000
	ORDER BY first_name, last_name, employee_id;
END$$
DELIMITER ;
;

CALL usp_get_employees_salary_above_35000;

SELECT *
FROM employees;

-- Ex. 2. Employees with Salary Above Number

DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_value DECIMAL (10, 4))
BEGIN
	SELECT first_name, last_name
	FROM employees
	WHERE salary >= salary_value
	ORDER BY first_name, last_name, employee_id DESC;
END$$
DELIMITER ;
;

CALL usp_get_employees_salary_above(45000);

-- Ex. 3. Town Names Starting With

DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(start_of_town VARCHAR (20))
BEGIN
-- DECLARE town_start_given VARCHAR (20);
-- SET town_start_given = start_of_town;
	SELECT towns.name
	FROM towns
	WHERE lower(`name`) LIKE concat(start_of_town, '%')
    ORDER BY name;
END$$
DELIMITER ;
;

DROP PROCEDURE usp_get_towns_starting_with;

CALL usp_get_towns_starting_with('b');

-- Ex. 4. Employees from Town

DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT 
		e.first_name,
		e.last_name
	FROM employees AS e
	JOIN addresses AS a ON a.address_id = e.address_id
	JOIN towns AS t ON t.town_id = a.town_id
	WHERE t.name = town_name
	ORDER BY first_name, last_name, employee_id;
END$$
DELIMITER ;
;

CALL usp_get_employees_from_town('Sofia')

-- Ex. 5. Salary Level Function

DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary_of_employee DECIMAL (10, 2))
RETURNS VARCHAR (20)
DETERMINISTIC
BEGIN
	DECLARE `level` VARCHAR(20);
    SET `level` := (CASE 
					WHEN (salary_of_employee < 30000) THEN 'Low'
					WHEN (salary_of_employee >= 30000 AND salary_of_employee <=50000) THEN 'Average' 
					WHEN (salary_of_employee > 50000) THEN 'High'
				END) ;
	RETURN 	`level`;
END$$
DELIMITER ;
;

DROP FUNCTION ufn_get_salary_level;

SELECT ufn_get_salary_level(13500);

-- Ex. 6. Employees by Salary Level

DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(sal_level VARCHAR(20))
BEGIN
	SELECT 
		first_name,
		last_name
	FROM employees
	WHERE ufn_get_salary_level(salary) = sal_level
	ORDER BY first_name DESC, last_name DESC;
END$$
DELIMITER ;
;

CALL usp_get_employees_by_salary_level('High');

-- Ex. 7. Define Function

DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
		DECLARE is_true BOOLEAN;
		DECLARE current_letter VARCHAR (5);
        DECLARE remaining_word VARCHAR (50);
        DECLARE letters_count INT;
        DECLARE remaining_charset VARCHAR (50);
        DECLARE position_of_first_occurence INT;
		SET is_true = TRUE;
        SET letters_count = char_length(word);
        SET remaining_word = lower(word);
        SET remaining_charset = lower(set_of_letters);
		
	WHILE (letters_count > 0 AND is_true = TRUE) DO
        SET current_letter = LEFT(remaining_word, 1);
		IF (remaining_charset LIKE concat('%', current_letter, '%'))
        THEN 
			SET letters_count = letters_count - 1;
			SET remaining_word = right(remaining_word, letters_count);
           --  SET position_of_first_occurence = locate(current_letter, remaining_charset);
			-- SET remaining_charset = concat(LEFT (remaining_charset, position_of_first_occurence -1), SUBSTRING(remaining_charset, position_of_first_occurence + 1));
        ELSE 
			SET is_true = FALSE;
		END IF;
	END WHILE;
RETURN is_true;
END$$
DELIMITER ;
;

DROP FUNCTION ufn_is_word_comprised;

SELECT ufn_is_word_comprised('nn', 'NNnn');

-- или по-лесното решение на лектора:
DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised2(set_of_letters varchar(50), word VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN word REGEXP (concat('^[', set_of_letters, ']+$'));
END $$
DELIMITER ;

SELECT ufn_is_word_comprised2('nn', 'NNnn');

-- PART II – Queries for Bank Database
-- Ex. 8. Find Full Name

DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT 
		concat(first_name, ' ', last_name) AS full_name
	FROM account_holders
	ORDER BY full_name, id;
END $$
DELIMITER ;

CALL usp_get_holders_full_name();

-- Ex. 9. People with Balance Higher Than

DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(num DECIMAL(10, 2))
BEGIN
	SELECT 
		first_name,
		last_name
	FROM account_holders AS ah
	JOIN accounts AS a ON a.account_holder_id = ah.id
	GROUP BY ah.id
	HAVING sum(balance) > num
	ORDER BY ah.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);

-- Ex. 10. Future Value Function

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(I DECIMAL(19, 4), R DECIMAL(19, 4), T INT)
RETURNS DECIMAL (19, 4)
DETERMINISTIC
BEGIN
	RETURN I*(pow(1+R, T));
END $$
DELIMITER ;

DROP FUNCTION ufn_calculate_future_value;

SELECT ufn_calculate_future_value(1000, 0.5, 5);

-- Ex. 11. Calculating Interest

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(acc_id INT, yearly_interest_rate DECIMAL(19, 4))
BEGIN
	SELECT 
		a.id AS account_id,
		ah.first_name,
		ah.last_name, 
		a.balance AS current_balance,
		(SELECT ufn_calculate_future_value(current_balance, yearly_interest_rate, 5)) AS balance_in_5_years
	FROM account_holders AS ah
	JOIN accounts AS a ON a.account_holder_id = ah.id
	WHERE a.id = acc_id;
END $$
DELIMITER ;

DROP PROCEDURE usp_calculate_future_value_for_account;

CALL usp_calculate_future_value_for_account(1, 0.1);

-- Ex. 12. Deposit Money

DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	CASE 
		WHEN money_amount > 0
    THEN 
		UPDATE accounts
		SET balance = balance + money_amount
		WHERE accounts.id = account_id;
	ELSE
			BEGIN
            END;
	END CASE;
END $$
DELIMITER ;

DROP PROCEDURE usp_deposit_money;

CALL usp_deposit_money(1, 10);

SELECT *
FROM accounts AS a
WHERE a.id = 1;

-- Ex. 13. Withdraw Money

DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	CASE
		WHEN 
			(SELECT balance FROM accounts WHERE accounts.id = account_id) >= money_amount
            AND
            money_amount > 0
		THEN
			UPDATE accounts
			SET balance = balance - money_amount
            WHERE accounts.id = account_id;
		ELSE
			BEGIN
            END;
	END CASE;
END $$
DELIMITER ;

DROP PROCEDURE usp_withdraw_money;

CALL usp_withdraw_money(1, 10);

SELECT * FROM accounts
WHERE id = 1;

-- Ex. 14. Money Transfer

DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    CALL usp_withdraw_money(from_account_id, amount);
    CALL usp_deposit_money(to_account_id, amount);
    IF (from_account_id = to_account_id 
		OR (SELECT balance FROM accounts WHERE accounts.id = from_account_id) < amount 
        OR amount <= 0
        OR from_account_id NOT IN (SELECT id FROM accounts)
        OR to_account_id NOT IN (SELECT id FROM accounts))
	THEN ROLLBACK;
	ELSE COMMIT;
    END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(2, 1, 10);

SELECT *
FROM accounts
WHERE id IN (1, 2);

-- Ex. 15. Log Accounts Trigger

CREATE TABLE logs (
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT, 
old_sum DECIMAL (19, 4), 
new_sum DECIMAL (19, 4)
);

DELIMITER $$
CREATE TRIGGER tr_after_account_change 
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	INSERT INTO logs (account_id, old_sum, new_sum)
	VALUES (OLD.id, 
    OLD.balance, NEW.balance);
END $$
DELIMITER ;

DROP TRIGGER tr_after_account_change;


SELECT * FROM logs;

-- Ex. 16. Emails Trigger

CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT, 
recipient INT NOT NULL, 
subject VARCHAR(100) NOT NULL, 
body TEXT (1000)
);

DROP TABLE notification_emails;

DELIMITER $$
CREATE TRIGGER tr_email_after_insert_in_logs
AFTER INSERT
ON logs
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails (recipient, subject, body)
    VALUES ( 
 --    (SELECT accounts.account_holder_id FROM accounts
--     WHERE NEW.account_id = accounts.id
--    ),
	NEW.account_id,
    concat('Balance change for account: ', NEW.account_id),
    concat('On ', DATE_FORMAT(NOW(), '%b %d %Y at %r'), ' your balance was changed from ', round(NEW.old_sum, 0), ' to ', round(NEW.new_sum, 0), '.'));
END $$
DELIMITER ;

DROP TRIGGER tr_email_after_insert_in_logs;

SELECT * FROM notification_emails
ORDER BY id DESC;
