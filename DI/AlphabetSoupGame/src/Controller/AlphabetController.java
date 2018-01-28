package Controller;

import Model.AlphabetSoupModel;
import View.AlphabetSoupView;

public class AlphabetController implements ControllerSoup{

    AlphabetSoupView alphabetSoupView;
    AlphabetSoupModel alphabetSoupModel;

    public AlphabetController(AlphabetSoupView alphabetSoupView, AlphabetSoupModel alphabetSoupModel){
        this.alphabetSoupView = alphabetSoupView;
        this.alphabetSoupModel = alphabetSoupModel;
        alphabetSoupView.setViewController(this);
    }





    @Override
    public String[] getSoupLetters() {
        return new String[0];
    }

    @Override
    public void resolveList(String[] letterWords) {

    }

}
