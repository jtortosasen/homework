package View;

import Controller.Controller;
import Model.Entity.Owner;
import Model.Entity.Parrot;

public interface ViewInterface {
    void setController(Controller controller);
    void setStartup(Parrot parrot, Owner owner);
}
