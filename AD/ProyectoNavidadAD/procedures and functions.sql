DELIMITER $$
CREATE FUNCTION DepartmentCounts() RETURNS int(11)
begin
    declare myCount int;
    select count(*) into myCount from employees.departments;
    return myCount;
end

DELIMITER $$
CREATE FUNCTION EmployeesCount() RETURNS int(11)
begin
    declare myCount int;
    select count(*) into myCount from employees;
    return myCount;
end

DELIMITER $$
CREATE FUNCTION EmployeesDeptCount(Dept varchar(4)) RETURNS int(11)
begin
    declare myCount int;
    select count(*) into myCount from employees 
	join dept_emp  on employees.emp_no = dept_emp.emp_no
	where dept_emp.dept_no = Dept;
    return myCount;
end

DROP PROCEDURE IF EXISTS getEmpData;
DELIMITER $$
CREATE PROCEDURE getDeptData(Dept varchar(4))
begin
    select * from departments where  departments.dept_no = Dept;
end

DROP PROCEDURE IF EXISTS getEmpData;
DELIMITER $$
CREATE PROCEDURE getEmpData(Emp integer)
begin
    select * from employees where employees.emp_no = Emp;
end


DROP PROCEDURE IF EXISTS getEmpData;
DELIMITER $$
CREATE PROCEDURE getAvgDept(Dept varchar(4))
begin
    select avg(salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.dept_no = Dept;
end


