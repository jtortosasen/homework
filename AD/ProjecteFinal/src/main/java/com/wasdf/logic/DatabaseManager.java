package com.wasdf.logic;

import com.wasdf.model.Departments;
import com.wasdf.model.Employees;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private Session session;

    public DatabaseManager(Session session) {
        this.session = session;
    }

    @Transactional
    public ArrayList<Departments> getDepartments() {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Departments> criteria = builder.createQuery(Departments.class);
        criteria.from(Departments.class);
        List<Departments> departmentsList = session.createQuery(criteria).getResultList();
        return new ArrayList<>(departmentsList);
    }

    @Transactional
    public ArrayList<Employees> getEmployeesFromDepartment(String actualDepartment) {
        try {
            TypedQuery<Employees> query = session.createQuery("SELECT emp FROM Employees emp LEFT JOIN emp.deptEmps dept ON dept.employees = emp LEFT JOIN dept.departments depart ON dept.departments = depart where depart.deptNo = :dep");
            query.setParameter("dep", actualDepartment);
            List<Employees> list = query.getResultList();

            return new ArrayList<>(list);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean createEmployee(Employees employee) {
        try {
            session.save(employee);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean modifyEmployee(Employees employee) {
        try{
            session.update(employee);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteEmployee(String empNo) {
        try {
            Employees employee = session.get(Employees.class,empNo);
            session.delete(employee);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean close() {
        try{
            session.close();
            return true;
        }catch (Exception e){
            return false;

        }
    }
}
