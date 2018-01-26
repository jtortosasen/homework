-- a
SELECT count(emp_no) FROM employees.dept_emp where to_date = '9999-01-01';
-- b
select avg (salaries.salary) from salaries
join employees on salaries.emp_no = employees.emp_no
join dept_emp on employees.emp_no = dept_emp.emp_no
where dept_emp.to_date = '9999-01-01';
-- c