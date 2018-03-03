package com.wasdf.model;
// Generated Feb 23, 2018 5:49:30 PM by Hibernate Tools 4.3.1


import com.wasdf.Util.Util;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employees generated by hbm2java
 */
@Entity
@Table(name = "employees"
        , catalog = "employees"
)
public class Employees implements java.io.Serializable {


    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
    private Set<Titles> titleses = new HashSet<Titles>(0);
    private Set<Salaries> salarieses = new HashSet<Salaries>(0);
    private Set<DeptEmp> deptEmps = new HashSet<DeptEmp>(0);
    private Set<DeptManager> deptManagers = new HashSet<DeptManager>(0);

    public Employees() {
    }


    public Employees(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Employees(int empNo, Date birthDate, String firstName, String lastName, String gender, Date hireDate, Set<Titles> titleses, Set<Salaries> salarieses, Set<DeptEmp> deptEmps, Set<DeptManager> deptManagers) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
        this.titleses = titleses;
        this.salarieses = salarieses;
        this.deptEmps = deptEmps;
        this.deptManagers = deptManagers;
    }

    @Id


    @Column(name = "emp_no", unique = true, nullable = false)
    public int getEmpNo() {
        return this.empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false, length = 10)
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Column(name = "first_name", nullable = false, length = 14)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "last_name", nullable = false, length = 16)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "gender", nullable = false, length = 2)
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date", nullable = false, length = 10)
    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    public Set<Titles> getTitleses() {
        return this.titleses;
    }

    public void setTitleses(Set<Titles> titleses) {
        this.titleses = titleses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    public Set<Salaries> getSalarieses() {
        return this.salarieses;
    }

    public void setSalarieses(Set<Salaries> salarieses) {
        this.salarieses = salarieses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    public Set<DeptEmp> getDeptEmps() {
        return this.deptEmps;
    }

    public void setDeptEmps(Set<DeptEmp> deptEmps) {
        this.deptEmps = deptEmps;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    public Set<DeptManager> getDeptManagers() {
        return this.deptManagers;
    }

    public void setDeptManagers(Set<DeptManager> deptManagers) {
        this.deptManagers = deptManagers;
    }


    @Override
    public String toString() {
        return String.valueOf(empNo) + " " + firstName + " " + lastName + " " + gender + " " + Util.dateToString(birthDate) + " " + Util.dateToString(hireDate);
    }


}


