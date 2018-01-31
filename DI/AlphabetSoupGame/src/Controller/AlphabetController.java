package Controller;

import Model.LOGIC.AlphabetSoupLogic;
import View.AlphabetSoupView;

public class AlphabetController implements ControllerSoup{

    AlphabetSoupView alphabetSoupView;
    AlphabetSoupLogic alphabetSoupLogic;

    public AlphabetController(AlphabetSoupView alphabetSoupView, AlphabetSoupLogic alphabetSoupLogic){
        this.alphabetSoupView = alphabetSoupView;
        this.alphabetSoupView.setViewController(this);

        this.alphabetSoupLogic = alphabetSoupLogic;
        this.alphabetSoupLogic.setModelController(this);
        this.alphabetSoupView.setArrayLettersSoup(this.alphabetSoupLogic.getLettersSoup());
        this.alphabetSoupView.setArraySolvedWords(this.alphabetSoupLogic.getSolvedWords());
    }


    //called from view
    @Override
    public void resolveSoup(char[] letterWords) {
        alphabetSoupLogic.solveArraySoup(letterWords);
    }

    //called from model
    @Override
    public void getFoundedWord(String word){
        System.out.println(word);
        alphabetSoupView.setFoundWord(word);
    }

    //called from view
    @Override
    public int getRowsSoup() {
        return alphabetSoupLogic.getRowsSoup();
    }

    //called from view
    @Override
    public int getColsSoup() {
        return alphabetSoupLogic.getColsSoup();
    }


}
