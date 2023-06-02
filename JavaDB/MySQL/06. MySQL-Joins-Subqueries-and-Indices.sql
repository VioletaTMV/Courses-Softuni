-- Lab 1. Managers

USE soft_uni;

SELECT
	e.employee_id,
    concat(e.first_name, ' ', e.last_name) as 'full_name',
   d.department_id,
   d.`name`
FROM departments AS d
JOIN employees AS e ON d.manager_id = e.employee_id
ORDER BY employee_id
LIMIT 5;

-- Lab 2. Towns Addresses

SELECT
	t.town_id,
    t.`name`,
    address_text
FROM towns AS t
JOIN addresses AS a ON a.town_id = t.town_id
WHERE `name` IN ('San Francisco', 'Sofia', 'Carnation')
ORDER BY town_id ASC, address_id ASC;

-- Lab 3. Employees Without Managers

SELECT
	employee_id,
    first_name,
    last_name,
    department_id,
    salary
FROM employees AS e
WHERE manager_id IS NULL;

-- Lab 4. Higher Salary

SELECT
	count(first_name)
FROM employees
WHERE salary > (
				SELECT
					avg(salary)
				FROM employees
				);
                
-- Ex. 1. Employee Address

SELECT 
	employee_id,
    job_title,
    e.address_id,
    address_text
FROM employees AS e
JOIN addresses AS a ON a.address_id = e.address_id
ORDER BY address_id ASC
LIMIT 5;

-- Ex. 2. Addresses with Towns

SELECT
	e.first_name,
    e.last_name,
    t.name,
    a.address_text
FROM employees AS e
JOIN addresses AS a ON a.address_id = e.address_id
JOIN towns AS t ON t.town_id = a.town_id
ORDER BY first_name ASC, last_name ASC
LIMIT 5;

-- Ex. 3. Sales Employee

SELECT
	e.employee_id,
    e.first_name,
    e.last_name,
    d.name
FROM employees AS e
JOIN departments AS d ON d.department_id = e.department_id
WHERE d.name = 'Sales'
ORDER BY employee_id DESC;

-- Ex. 4. Employee Departments

SELECT
	employee_id,
    e.first_name,
    e.salary,
    d.name
FROM employees AS e
JOIN departments AS d ON d.department_id = e.department_id
WHERE salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

-- Ex. 5. Employees Without Project

SELECT
	e.employee_id,
    e.first_name
FROM employees AS e
LEFT JOIN employees_projects AS ep ON ep.employee_id = e.employee_id
LEFT JOIN projects AS p ON p.project_id = ep.project_id
WHERE ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

SELECT * FROM employees_projects;
SELECT * FROM projects;

-- Ex. 6. Employees Hired After

SELECT
	first_name,
    last_name,
    hire_date,
    d.name
FROM employees AS e
JOIN departments AS d ON d.department_id = e.department_id
WHERE e.hire_date > '1999-01-01' AND d.name IN ('Sales', 'Finance')
ORDER BY e.hire_date ASC;

-- Ex. 7. Employees with Project

SELECT
	e.employee_id,
    first_name,
    p.name
FROM employees AS e
JOIN employees_projects AS ep ON ep.employee_id = e.employee_id
JOIN projects AS p ON p.project_id = ep.project_id
WHERE Date(p.start_date) > '2002-08-13' AND p.end_date IS NULL
ORDER BY e.first_name ASC, p.name ASC
LIMIT 5;

SELECT * FROM projects
WHERE name = 'Classic vest';

SELECT * FROM employees
WHERE first_name = 'Alice';

SELECT * FROM employees_projects
WHERE employee_id = 44;

SELECT * FROM departments
WHERE department_id = 7;

-- Ex. 8. Employee 24

SELECT
	e.employee_id,
    first_name,
    if(year(p.start_date) >= 2005, NULL, p.name)
FROM employees AS e
JOIN employees_projects AS ep ON ep.employee_id = e.employee_id
JOIN projects AS p ON p.project_id = ep.project_id
WHERE e.employee_id = 24
ORDER BY p.name ASC;

-- Ex. 9. Employee Manager

SELECT
	e.employee_id,
    e.first_name,
    e.manager_id,
    m.first_name AS 'manager_name'
FROM employees AS e
JOIN employees AS m ON m.employee_id = e.manager_id
WHERE e.manager_id IN (3, 7)
ORDER BY e.first_name ASC;

-- Ex. 10. Employee Summary

SELECT 
	e.employee_id,
    concat(e.first_name, ' ', e.last_name) AS 'employee_name',
    concat(m.first_name, ' ', m.last_name) AS 'manager_name',
    d.name
FROM employees AS e
JOIN employees AS m ON m.employee_id = e.manager_id
JOIN departments AS d ON d.department_id = e.department_id
ORDER BY e.employee_id
LIMIT 5;

-- Ex. 11. Min Average Salary

SELECT
	min(averages.`average_salary`)
from (
		SELECT
			avg(salary) AS 'average_salary'
		FROM employees
		GROUP BY department_id 
	)  AS `averages` ;


SELECT
	avg(salary) AS 'average_salary'
FROM employees
GROUP BY department_id
ORDER BY average_salary ASC
LIMIT 1;

-- Ex. 12. Highest Peaks in Bulgaria

SELECT
	c.country_code,
    m.mountain_range,
    p.peak_name,
    p.elevation
FROM countries AS c
JOIN mountains_countries AS mc ON mc.country_code = c.country_code
JOIN mountains AS m ON m.id = mc.mountain_id
JOIN peaks AS p ON p.mountain_id = m.id
WHERE c.country_name = 'Bulgaria' AND p.elevation > 2835
ORDER BY p.elevation DESC;

-- Ex. 13. Count Mountain Ranges

SELECT
	c.country_code,
   count(m.mountain_range) AS 'mountain_range_count'
FROM countries AS c
JOIN mountains_countries AS mc ON c.country_code = mc.country_code
JOIN mountains AS m ON m.id = mc.mountain_id
WHERE c.country_name IN ('United States', 'Russia', 'Bulgaria')
GROUP BY country_code
ORDER BY mountain_range_count DESC;

-- Ex. 14. Countries with Rivers

SELECT
	c.country_name,
    r.river_name
FROM countries AS c
LEFT JOIN countries_rivers AS cr ON cr.country_code = c.country_code
LEFT JOIN rivers AS r ON r.id = cr.river_id
JOIN continents AS con ON con.continent_code = c.continent_code
WHERE con.continent_name = 'Africa'
ORDER BY country_name ASC
LIMIT 5;

-- Ex. 15. *Continents and Currencies

SELECT
	cont.continent_code AS 'cont_c',
    curr.currency_code AS 'curr_c',
    COUNT(*) AS currency_count
FROM continents AS cont
JOIN countries AS c ON c.continent_code = cont.continent_code
JOIN currencies AS curr ON curr.currency_code = c.currency_code
GROUP BY cont.continent_code, curr.currency_code
HAVING currency_count > 1 
		AND currency_count = (
							SELECT count(*) AS count_of_currency_in_continent
                            FROM countries AS c2
                            WHERE c2.continent_code = cont_c
                            GROUP BY c2.currency_code
                            ORDER BY count_of_currency_in_continent DESC
                            LIMIT 1
								)
;

-- Ex. 16. Countries Without Any Mountains

SELECT 
	count(*)
FROM countries AS c
LEFT JOIN mountains_countries AS mc ON c.country_code = mc.country_code
WHERE mc.mountain_id IS NULL
;

-- Ex. 17. Highest Peak and Longest River by Country

SELECT 
	c.country_name,
   max(p.elevation) AS highest_peak_elevation,
	max(r.length) AS longest_river_length
FROM countries AS c
LEFT JOIN countries_rivers AS cr ON cr.country_code = c.country_code
LEFT JOIN mountains_countries AS mc ON mc.country_code = c.country_code
LEFT JOIN peaks AS p ON p.mountain_id = mc.mountain_id
LEFT JOIN rivers AS r ON cr.river_id = r.id
GROUP BY country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, country_name ASC
LIMIT 5;

-- експеримент за горното но и с имена на върхове и реки

SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

SELECT 
	c.country_name,
   max(p.elevation) AS highest_peak_elevation,
   (
   SELECT 
	p2.peak_name
   FROM peaks AS p2
   JOIN mountains AS m2 ON p2.mountain_id = m2.id
   JOIN mountains_countries AS mc2 ON mc2.mountain_id = m2.id
   WHERE mc2.country_code = c.country_code 
   ORDER BY p2.elevation DESC
   LIMIT 1   
   ) 
   AS hihest_peak_name,
	max(r.length) AS longest_river_length,
    (
    SELECT 
		r2.river_name
	FROM rivers AS r2
    JOIN countries_rivers AS cr2 ON cr2.river_id = r2.id
    WHERE cr2.country_code = c.country_code
    ORDER BY r2.length DESC
    LIMIT 1
    )
    AS longest_river_name
FROM countries AS c
LEFT JOIN countries_rivers AS cr ON cr.country_code = c.country_code
LEFT JOIN mountains_countries AS mc ON mc.country_code = c.country_code
LEFT JOIN peaks AS p ON p.mountain_id = mc.mountain_id
LEFT JOIN rivers AS r ON cr.river_id = r.id
GROUP BY country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, country_name ASC
LIMIT 5;
