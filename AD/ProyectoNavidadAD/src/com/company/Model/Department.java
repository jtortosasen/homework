/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Model;

/**
 *
 * @author professor
 */
public class Department {
    private String no;
    private String name;
    
    public Department(){
        no = "";
        name = "";
    }
    
    public Department(String no, String name){
        this.no = no;
        this.name = name;
    }
    
    public String getNo() { return no;}
    public String getName() {return name;}
    public void setNo(String no) { this.no = no;}
    public void setName(String name) {this.name = name;}
    
    @Override
    public String toString() {
        return "No [" + getNo() + "] Name [" + getName() + "]";
    }
}
