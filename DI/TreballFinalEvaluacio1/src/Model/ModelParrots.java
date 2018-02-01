package Model;

import Controller.Controller;
import Model.Entity.Owner;
import Model.Entity.Parrot;

import java.util.ArrayList;

public class ModelParrots implements ModelInterface{

    Controller controller;
    ArrayList<Parrot> parrots = new ArrayList<>();
    ArrayList<Owner> owners = new ArrayList<>();

    public ModelParrots(){
        parrots.add(new Parrot("1","Bromiko","Carronyer","blau","1"));
        parrots.add(new Parrot("2","Batman","Huma","negre","2"));
        parrots.add(new Parrot("3","Goku","Saiyan","groc","3"));
        parrots.add(new Parrot("4","Vegeta","Saiyan","groc","4"));
        parrots.add(new Parrot("5","Piccolo","Namekian","verd","5"));

        owners.add(new Owner("1", "name1", "lastname1", "12"));
        owners.add(new Owner("2", "name2", "lastname2", "11"));
        owners.add(new Owner("3", "name3", "lastname3", "12"));
        owners.add(new Owner("4", "name4", "lastname4", "21"));
        owners.add(new Owner("5", "name5", "lastname5", "31"));
    }

    @Override
    public Parrot getEntityParrot(int id) {
        return parrots.get(id);
    }

    @Override
    public Owner getEntityOwner(int id) {
        return owners.get(id);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public int getSizeEntities() {
        return parrots.size();
    }
}
