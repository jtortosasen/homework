package Controller;

import Model.Entity.Owner;
import Model.Entity.Parrot;
import View.PanelInfo;
import Model.ModelParrots;

import java.awt.*;

public class Controller implements ControllerInterface{

    ModelParrots model;
    PanelInfo view;

    public Controller(ModelParrots model, PanelInfo view){
        this.model = model;
        this.view = view;
        view.setController(this);
        model.setController(this);
        view.setStartup(getParrot(0),getOwner(0));
    }

    @Override
    public int getSizeEntities() {
        return model.getSizeEntities();
    }

    @Override
    public Owner getOwner(int id) {
        return model.getEntityOwner(id);
    }

    @Override
    public Parrot getParrot(int id) {
        return model.getEntityParrot(id);
    }
}
