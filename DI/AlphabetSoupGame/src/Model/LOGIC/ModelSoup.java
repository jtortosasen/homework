package Model.LOGIC;

import Controller.ControllerSoup;

public interface ModelSoup {
    char[] getLettersSoup();
    String[] getSolvedWords();
    void solveArraySoup(char[] array);
    int getRowsSoup();
    int getColsSoup();
    void setModelController(ControllerSoup controller);
}
