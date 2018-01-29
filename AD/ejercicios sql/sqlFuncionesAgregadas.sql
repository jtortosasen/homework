-- a
SELECT count(emp_no) FROM employees.dept_emp where to_date = '9999-01-01';
-- b
select avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01';
-- c
select employees.gender, avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01'
group by employees.gender;
-- d
select dept_emp.dept_no, avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01'
group by dept_emp.dept_no;
-- e
select dept_emp.dept_no, employees.gender, avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01'
group by dept_emp.dept_no, employees.gender;
-- f
select dept_emp.dept_no, count(dept_emp.dept_no) as 'N. empleados' from dept_emp
where dept_emp.to_date != '9999-01-01'
group by dept_emp.dept_no;
-- g
select employees.first_name, employees.last_name, salaries.salary
from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where salaries.salary > (select avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01');
-- h
select employees.emp_no, employees.first_name, employees.last_name, count(dept_emp.dept_no)
from employees
join dept_emp on employees.emp_no = dept_emp.emp_no
group by (employees.emp_no)
having count(dept_emp.emp_no) > 1;
-- i
select dept_emp.dept_no, employees.emp_no, employees.first_name, max(salaries.salary) as salary from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01'
group by(dept_emp.dept_no);
