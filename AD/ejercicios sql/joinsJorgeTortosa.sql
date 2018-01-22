use employees;
insert into departments(dept_no, dept_name) values('d999', 'empty');
-- a
select * from departments order by dept_no;
-- b
select departments.dept_no, dept_manager.emp_no from departments 
join dept_manager on departments.dept_no = dept_manager.dept_no
order by dept_manager.dept_no, dept_manager.to_date;
-- c
select departments.dept_no, employees.* from departments
join dept_manager on departments.dept_no = dept_manager.dept_no
join employees on dept_manager.emp_no = employees.emp_no
order by departments.dept_no, dept_manager.to_date;
-- d
select departments.dept_no, dept_manager.emp_no from departments 
left join dept_manager on departments.dept_no = dept_manager.dept_no
order by dept_manager.dept_no, dept_manager.to_date;
-- e
select departments.*, employees.* from departments
left join dept_manager on departments.dept_no = dept_manager.dept_no
left join employees on dept_manager.emp_no = employees.emp_no
order by departments.dept_no, dept_manager.to_date;
-- f
select * from employees;
-- g
select employees.*, departments.* from employees
join dept_manager on employees.emp_no = dept_manager.emp_no
join departments on dept_manager.dept_no = departments.dept_no;
-- h
select departments.*, employees.*, titles.title from departments
join dept_manager on departments.dept_no = dept_manager.dept_no
join employees on dept_manager.emp_no = employees.emp_no
join titles on employees.emp_no = titles.emp_no order by departments.dept_no, employees.hire_date;
-- i
select departments.*, employees.*, titles.title, salaries.salary from departments
join dept_manager on departments.dept_no = dept_manager.dept_no
join employees on dept_manager.emp_no = employees.emp_no
join titles on employees.emp_no = titles.emp_no
join salaries on employees.emp_no = salaries.emp_no order by departments.dept_no, employees.hire_date;
-- j
select employees.*, departments.* from employees
join dept_manager on employees.emp_no = dept_manager.emp_no
join departments on dept_manager.dept_no = departments.dept_no
where dept_manager.to_date = '9999-01-01';

select departments.*, employees.*, titles.title from departments
join dept_manager on departments.dept_no = dept_manager.dept_no
join employees on dept_manager.emp_no = employees.emp_no
join titles on employees.emp_no = titles.emp_no
where dept_manager.to_date = '9999-01-01' order by departments.dept_no, employees.hire_date;

select departments.*, employees.*, titles.title, salaries.salary from departments
join dept_manager on departments.dept_no = dept_manager.dept_no
join employees on dept_manager.emp_no = employees.emp_no
join titles on employees.emp_no = titles.emp_no
join salaries on employees.emp_no = salaries.emp_no
where dept_manager.to_date = '9999-01-01'
order by departments.dept_no, employees.hire_date;
-- k
delete from departments
where departments.dept_no = 'd001';

