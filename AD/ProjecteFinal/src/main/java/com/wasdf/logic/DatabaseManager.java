package com.wasdf.logic;

import com.wasdf.Util.Util;
import com.wasdf.model.Departments;
import com.wasdf.model.DeptEmp;
import com.wasdf.model.DeptEmpId;
import com.wasdf.model.Employees;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DatabaseManager {

    private Session session;

    public DatabaseManager(Session session) {
        this.session = session;
    }

    @Transactional
    public ArrayList<Departments> getDepartments() {

        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Departments> criteria = builder.createQuery(Departments.class);
        criteria.from(Departments.class);
        List<Departments> departmentsList = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        return new ArrayList<>(departmentsList);
    }

    @Transactional
    public ArrayList<Employees> getEmployeesFromDepartment(String actualDepartment) {
        try {
            session.getTransaction().begin();
            TypedQuery<Employees> query = session.createQuery("SELECT emp FROM Employees emp LEFT JOIN emp.deptEmps dept ON dept.employees = emp LEFT JOIN dept.departments depart ON dept.departments = depart where depart.deptNo = :dep");
            query.setParameter("dep", actualDepartment);
            List<Employees> list = query.getResultList();
            session.getTransaction().commit();

            return new ArrayList<>(list);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean createEmployee(Employees employee) {
        try {
            session.getTransaction().begin();
            session.persist(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Transactional
    public boolean modifyEmployee(Employees employee) {
        try {
            session.getTransaction().begin();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteEmployee(Employees employee) {
        try {
            session.getTransaction().begin();
            session.delete(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    public boolean deleteDepartment(Departments department) {
        try {
            session.getTransaction().begin();
            session.delete(department);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean close() {
        try {
            session.close();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Transactional
    public Departments getDepartment(String depNo) {
        try {
            session.getTransaction().begin();
            TypedQuery<Departments> query = session.createQuery("SELECT depart FROM Departments depart where depart.deptNo = :dep");
            query.setParameter("dep", depNo);
            List<Departments> list = query.getResultList();
            session.getTransaction().commit();
            if (!list.isEmpty()) {
                return list.get(0);
            } else
                return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Employees getEmployee(int empNo) {
        try {
            session.getTransaction().begin();
            TypedQuery<Employees> query = session.createQuery("SELECT emp FROM Employees emp where emp.empNo = :emp");
            query.setParameter("emp", empNo);
            List<Employees> list = query.getResultList();
            session.getTransaction().commit();
            if (!list.isEmpty()) {
                return list.get(0);
            } else
                return null;

        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void recordEmployee(int empNo, String deptNo) {
        try {
            DeptEmpId deptEmpId = new DeptEmpId(empNo, deptNo);
            DeptEmp deptEmp = new DeptEmp(deptEmpId, getDepartment(deptNo), getEmployee(empNo), Util.stringToDate("1212-12-12"), Util.stringToDate("9999-01-01"));
            session.getTransaction().begin();
            session.persist(deptEmp);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public Departments getDepartmentFromEmployee(int empNo){
        Employees employees = session.get(Employees.class,empNo);
        Set<DeptEmp> deptEmp = employees.getDeptEmps();
        if(!deptEmp.isEmpty()){
            ArrayList<Departments> departments = new ArrayList<>();
            for(DeptEmp emp : deptEmp){
                departments.add(emp.getDepartments());
            }
            return departments.get(departments.size()-1);
        }
        return null;
    }


    @Transactional
    public boolean updateDepartment(Departments department) {
        try{
            session.getTransaction().begin();
            session.merge(department);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
