package com.company.Model;


public class Employee {
    private String emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private String hire_date;

    public String getEmpNo() {
        return emp_no;
    }

    public void setEmpNo(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return hire_date;
    }

    public void setHireDate(String hire_date) {
        this.hire_date = hire_date;
    }

    public Employee (){
        emp_no = "";
        birth_date = "";
        first_name = "";
        last_name = "";
        gender = "";
        hire_date = "";
    }

    public Employee(String emp_no, String birth_date, String first_name,
                    String last_name, String gender, String hire_date){
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
    }

    @Override
    public String toString(){
        return "No: " + emp_no + " First Name: " + first_name + " Last name: " + last_name
        + " Birth date: " + birth_date + " Gender: " + gender + " Hire_date " + hire_date;
    }
}
