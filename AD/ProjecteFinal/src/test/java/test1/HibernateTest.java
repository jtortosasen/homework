/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

/**
 *
 * @author professor
 */
public class HibernateTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("main()-->");
        DepartmentTest dtest = new DepartmentTest();
        dtest.test();
        dtest.close();
        System.out.println("--> main()");
    }
    
}
