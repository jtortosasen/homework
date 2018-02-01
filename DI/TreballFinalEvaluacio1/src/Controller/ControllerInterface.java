package Controller;

import Model.Entity.Owner;
import Model.Entity.Parrot;

public interface ControllerInterface {

    int getSizeEntities();
    Owner getOwner(int id);
    Parrot getParrot(int id);
}
