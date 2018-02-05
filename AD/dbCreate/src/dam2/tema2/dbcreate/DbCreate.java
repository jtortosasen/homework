/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.tema2.dbcreate;

/**
 *
 * @author professor
 */
public class DbCreate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        Conn condb = new Conn();
        if (condb.getSuccess()) condb.createDataBase("EJEMPLO");
        if(condb.createTable1("departamentos"))
            System.out.println("Tabla departamentos creada");
        else System.out.println("error al crear tabla departamentos");
        if(condb.createTable2("empleados"))
            System.out.println("Tabla empleados creada");
        else System.out.println("error al crear tabla empleados");

        if(condb.insertDep("10","CONTABILIDAD","SEVILLA"))
            System.out.println("succes1");
        if(condb.insertDep("20","INVESTIGACION","MADRID"))
            System.out.println("succes2");
        if(condb.insertDep("30","VENTAS","BARCELONA"))
            System.out.println("succes3");
        if(condb.insertDep("40","PRODUCCION","BILBAO"))
            System.out.println("succes4");
        condb.visualizeTable("departamentos");

        condb.closeConn();
    }
    
}
