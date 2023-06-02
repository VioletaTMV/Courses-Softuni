-- Lab :  Data Aggregation
-- Lab 1. Departments Info

SELECT department_id, count(*)
FROM employees
GROUP BY department_id;

-- Lab 2. Average Salary

SELECT department_id, round(avg(salary), 2) 
FROM employees
GROUP BY department_id;

-- Lab 3. Min Salary

SELECT department_id, min(salary) AS 'Min Salary'
FROM employees
GROUP BY department_id
HAVING `Min Salary` > 800;

-- Lab 4. Appetizers Count
SELECT count(*)
FROM products
WHERE category_id = 2 AND price > 8;

-- Lab 5. Menu Prices

SELECT 
	category_id, 
    round(avg(price), 2) AS 'Average Price',
    round(min(price), 2) AS 'Cheapest Product',
    round(max(price), 2) AS 'Most Expensive Product'
FROM products
GROUP BY category_id;


-- Exercises: Data Aggregation

-- Ex. 1. Records' Count

SELECT count(*)
FROM wizzard_deposits;

-- Ex. 2. Longest Magic Wand

SELECT max(magic_wand_size)
FROM wizzard_deposits;

-- Ex. 3. Longest Magic Wand Per Deposit Groups

SELECT deposit_group, max(magic_wand_size) AS 'longest_magic_wand'
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `longest_magic_wand` ASC, deposit_group ASC;

-- Ex. 4. Smallest Deposit Group Per Magic Wand Size*

SELECT deposit_group /*, avg(magic_wand_size) AS 'avg'*/
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY avg(magic_wand_size) ASC
LIMIT 1;

SELECT deposit_group
	FROM wizzard_deposits
	GROUP BY deposit_group
HAVING avg(magic_wand_size) = 
		(SELECT 
			(avg(magic_wand_size)) 
				FROM wizzard_deposits
				GROUP BY deposit_group
				ORDER BY avg(magic_wand_size) ASC
				LIMIT 1
			)
		;

-- Ex. 5. Deposits Sum

SELECT deposit_group, sum(deposit_amount)
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY sum(deposit_amount);

-- Ex. 6. Deposits Sum for Ollivander Family

SELECT deposit_group, sum(deposit_amount) AS 'total_sum'
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group ASC;

-- Ex. 7. Deposits Filter

SELECT deposit_group, sum(deposit_amount) as 'total_sum'
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

-- Ex. 8. Deposit Charge

SELECT deposit_group, magic_wand_creator, min(deposit_charge)
FROM wizzard_deposits
GROUP BY deposit_group, magic_wand_creator
ORDER BY magic_wand_creator ASC, deposit_group ASC;

-- Ex. 9. Age Groups
        
	SELECT 
		CASE
			WHEN age >= 0 AND age <=10 THEN '[0-10]'
			WHEN age BETWEEN 11 AND 20 THEN '[11-20]'
			WHEN age BETWEEN 21 AND 30 THEN '[21-30]'
			WHEN age BETWEEN 31 AND 40 THEN '[31-40]'
			WHEN age BETWEEN 41 AND 50 THEN '[41-50]'
			WHEN age BETWEEN 51 AND 60 THEN '[51-60]'
			WHEN age >= 61 THEN '[61+]'
			END 
		AS 'age_groups',
        count(first_name) AS 'wizzard_count'
    FROM wizzard_deposits
    GROUP BY `age_groups`
    -- HAVING `age_groups` IS NOT NULL
    ORDER BY `age_groups`;
    
    -- Ex. 10. First Letter
    
    SELECT left(first_name, 1) AS 'first_letter'
    FROM wizzard_deposits
    WHERE deposit_group = 'Troll Chest'
    GROUP BY `first_letter`
    ORDER BY `first_letter` ASC;
    
    -- Ex. 11. Average Interest
    
    SELECT 
		deposit_group,
        is_deposit_expired,
        avg(deposit_interest)
	FROM wizzard_deposits
    WHERE deposit_start_date > '1985-01-01'
    GROUP BY deposit_group, is_deposit_expired
    ORDER BY deposit_group DESC, is_deposit_expired ASC;
    
    -- Ex. 12. Employees Minimum Salaries
    
    USE soft_uni;
    
    SELECT 
		department_id,
        min(salary)
    FROM employees
    WHERE 
		department_id IN (2, 5, 7) AND
        hire_date > '2000-01-01'
    GROUP BY department_id
    ORDER BY department_id ASC;
    
    -- Ex. 13. Employees Average Salaries
    
    SELECT 
		department_id,
        if(department_id = 1, avg(salary + 5000), avg(salary)) AS 'avg_salary'
    FROM employees
    WHERE 
		salary > 30000 AND
        manager_id != 42
	GROUP BY department_id
    ORDER BY department_id;
    
    -- Ex. 14. Employees Maximum Salaries
    
    SELECT 
		department_id,
		max(salary) AS 'max_salary'
	FROM employees
    GROUP BY department_id
    HAVING `max_salary` NOT BETWEEN 30000 AND 70000
    ORDER BY department_id;
    
    -- Ex. 15. Employees Count Salaries
    
    SELECT count(salary)
    FROM employees
    WHERE manager_id IS NULL;
    
    -- Ex. 16. 3rd Highest Salary*

-- (SELECT DISTINCT
-- 		department_id,
-- 		salary,
--       RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) AS 'rank' ,
--       DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) AS 'dense_rank' 
-- 	FROM employees
--     ORDER BY department_id, salary DESC) 
--     ;

SELECT 
	department_id,
    salary
FROM 
(SELECT DISTINCT
		department_id,
		salary,
        DENSE_RANK() OVER(PARTITION BY department_id ORDER BY salary DESC) AS 'third_highest_salary'  
	FROM employees
    ORDER BY department_id, salary DESC) AS `derivedtbl`
WHERE `derivedtbl`.`third_highest_salary` = 3
;

-- втори вариант

SELECT *
FROM 
	(SELECT DISTINCT 
		department_id,
		NTH_VALUE(`helptable`.`salary`, 3) OVER(PARTITION BY `helptable`.department_id ORDER BY `helptable`.`salary` DESC RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS `third_highest_salary` 
	FROM 
		(SELECT DISTINCT
			department_id,
			salary
		FROM employees
		ORDER BY department_id ASC, salary DESC) AS `helptable`) AS `secondhelptable`
WHERE `secondhelptable`. `third_highest_salary` IS NOT NULL
;
 
-- Ex. 17. Salary Challenge**

SELECT
	first_name,
    last_name,
    department_id
FROM
    (SELECT 
		employee_id,
		first_name,
        last_name,
		department_id,
        salary,
		avg(salary) OVER(PARTITION BY `department_id` ORDER BY department_id ) AS `avg_dptm_salary`
	FROM employees) AS `avgs_tbl`
WHERE `avgs_tbl`.`avg_dptm_salary` < `avgs_tbl`.`salary`    
ORDER BY department_id ASC, employee_id ASC
LIMIT 10
; 
 
 -- Ex. 18. Departments Total Salaries
 
 SELECT 
	department_id,
    sum(salary)
FROM employees
GROUP BY department_id
ORDER BY department_id;