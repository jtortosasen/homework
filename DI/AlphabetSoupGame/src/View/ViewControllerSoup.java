package View;

import Controller.AlphabetController;

public interface ViewControllerSoup {

    void setViewController(AlphabetController controller);
    void setArraySolvedWords(String[] arraySolvedWords);
    void setArrayLettersSoup(char[] arrayLettersSoup);
    void setFoundWord(String word);

}
