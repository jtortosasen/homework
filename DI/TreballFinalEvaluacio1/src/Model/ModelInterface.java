package Model;

import Controller.Controller;
import Model.Entity.Owner;
import Model.Entity.Parrot;

interface ModelInterface {
    Parrot getEntityParrot(int id);
    Owner getEntityOwner(int id);
    void setController(Controller controller);
    int getSizeEntities();
}
