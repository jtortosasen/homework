package Controller;

import Model.LOGIC.AlphabetSoupLogic;
import View.AlphabetSoupView;

public class AlphabetController implements ControllerSoup{

    AlphabetSoupView alphabetSoupView;
    AlphabetSoupLogic alphabetSoupLogic;

    public AlphabetController(AlphabetSoupView alphabetSoupView, AlphabetSoupLogic alphabetSoupLogic){
        this.alphabetSoupView = alphabetSoupView;
        this.alphabetSoupLogic = alphabetSoupLogic;
        this.alphabetSoupView.setViewController(this);
        this.alphabetSoupView.setArrayLettersSoup(this.alphabetSoupLogic.getLettersSoup());
        this.alphabetSoupView.setArraySolvedWords(this.alphabetSoupLogic.getSolvedWords());
    }

    @Override
    public void resolveSoup(char[] letterWords) {
        alphabetSoupLogic.solveArraySoup(letterWords);
    }

    @Override
    public void getFoundedWord(String word){
        alphabetSoupView.setFoundWord(word);
    }


}
