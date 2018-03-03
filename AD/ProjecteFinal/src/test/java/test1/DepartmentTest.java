/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import com.wasdf.model.Departments;
import com.wasdf.model.DeptManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author professor
 */

public class DepartmentTest {

    private SessionFactory factory = null;
    private Session sesion = null;

    public DepartmentTest() {

        System.out.println("DepartmentTest()...");
        factory = HibernateUtil.getSessionFactory();
        sesion = factory.openSession();

    }

    public void close() {
        sesion.close();
    }

    public void test() {
        System.out.println("Test...");
        addDepartment("d100", "Departamento 100");
        listDepartments();
        updateDepartment("d100", "Departamento 100 modificado");
        listDepartments();
        removeDepartment("d100");
        listDepartments();
        listManager("d001");
    }

    @Transactional
    public void addDepartment(String cod, String name) {
        Departments dpt = null;
        System.out.println("Insertar un departamento...");
        try {
            dpt = new Departments(cod, name);
            System.out.println("save() --> " + (String) sesion.save(dpt));
            System.out.println("departamento " + cod + " insertado...");
        } catch (Exception ex) {
            System.out.println("No se ha podido guadar el departamento:" + dpt.toString());
            ex.printStackTrace();
        }
    }


    @Transactional
    public void removeDepartment(String cod) {
        System.out.println("Eliminar un departamento...");
        try {
            Departments dpt = this.load(cod);
            sesion.delete(dpt);
            System.out.println("departamento " + cod + " eliminado...");
        } catch (Exception e) {
            System.out.println("Error al eeliminar en departamento " + cod);
            e.printStackTrace();
        }

    }

    @Transactional
    public void updateDepartment(String cod, String name) {
        System.out.println("modificar un departamento...");
        Departments dpt = null;
        dpt = this.load(cod);
        dpt.setDeptName(name);
        try {
            sesion.update(dpt);
        } catch (Exception ex) {
            System.out.println("Error al modificar " + cod);
            ex.printStackTrace();
        }
        sesion.update(dpt);
        System.out.println("departamento() " + cod + " modificado...");
    }

    @Transactional
    public void listDepartments() {
        System.out.println("Listar departamentos ...");
        Query query = sesion.createQuery("SELECT d FROM Departments d");
        List<Departments> depts = query.list();
        for (Departments dept : depts) {
            System.out.println(dept.toString());
        }
    }

    @SuppressWarnings("unchecked")
    private Departments getDeptByNum(String depcod) {
        Departments d = null;

        Query query = sesion.createQuery(
                "SELECT d FROM Departments d WHERE dept_no = ?");
        query.setString(0, depcod);
        query.uniqueResult();
        List<Departments> depts = query.list();
        if (depts.size() == 1) {
            d = depts.get(0);
        }
        return d;
    }

    public Departments load(String depno) {
        Departments d;
        System.out.print("load( " + depno + ")-->");
        d = new Departments();
        /* utilizamos get() y no load() ...
         * load() da un error de hibernate si la fila buscada no existe
         * get() en cambio devuelve null
         */
        d = (Departments) sesion.get(Departments.class, (String) depno);
        if (d != null) {
            System.out.println("Departamento encontrado --> " + d.toString());
        } else System.out.println("Departamento no encontrado");
        System.out.println("-->load( " + depno + ")");
        return d;
    }

    @SuppressWarnings("unchecked")
    public void listManager(String depcod) {
        System.out.println("ListManager() -->");
        Departments d = load(depcod);
        if (d != null) {
            sesion.persist(d);
            Set<DeptManager> managers = d.getDeptManagers();
            if (!managers.isEmpty()) {
                System.out.println("[" + managers.size() + "] Managers");
                Iterator<DeptManager> iterator = managers.iterator();
                while (iterator.hasNext()) {
                    DeptManager dm = iterator.next();
                    System.out.print(dm.getEmployees().getFirstName());
                    System.out.print(", " + dm.getEmployees().getLastName());
                    System.out.print(" - " + dm.getFromDate());
                    System.out.print(" <-> " + dm.getToDate());
                    System.out.println("");
                }
            }
        } else {
            System.out.println("No hay managers....");
        }
        System.out.println("--> ListManagers()");
    }
}
