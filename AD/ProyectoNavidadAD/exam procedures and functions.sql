DROP FUNCTION IF EXISTS salary_avg_by_gender;
DELIMITER $$
CREATE FUNCTION salary_avg_by_gender(dept CHAR(4), gender CHAR(1)) RETURNS double
begin
    declare myCount double;
    select avg(salaries.salary) into myCount from salaries
	join employees on salaries.emp_no = employees.emp_no
	join dept_emp on employees.emp_no = dept_emp.emp_no
	where dept_emp.dept_no = dept and employees.gender = gender;
    return myCount;
end

DROP FUNCTION IF EXISTS salary_diff;
DELIMITER $$
CREATE FUNCTION salary_diff(dept CHAR(4)) RETURNS double
begin
    declare female double;
    declare male double;
    declare difference double;
    select salary_avg_by_gender(dept,'F') into female;
    select salary_avg_by_gender(dept,'M') into male;
    set difference = 100 -( (female / male) * 100);
    return difference;
end