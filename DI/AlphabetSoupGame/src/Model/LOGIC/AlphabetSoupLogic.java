package Model.LOGIC;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.DAO.DataAccess;
import Model.ModelSoupLogic;

public class AlphabetSoupLogic implements ModelSoup {

    DataAccess dataAccess;
    private static final Logger LOG = Logger.getLogger(AlphabetSoupLogic.class.getName());



    public AlphabetSoupLogic(String file_words, String file_letters){
        dataAccess = new DataAccess(file_words, file_letters);
    }

    @Override
    public char[] getLettersSoup() {
        ArrayList<Character> arrayList = dataAccess.getFileLetters();
        if(arrayList != null){
            char[] array = new char[arrayList.size()];
            for(int i = 0; i<array.length; i++){
                array[i] = arrayList.get(i);
            }
            return array;
        }
        else{
            LOG.log(Level.WARNING, "ArrayList letters is null");
            return new char[0];
        }
    }

    @Override
    public String[] getSolvedWords() {
        String [] array = dataAccess.getFileWordsAsArray();
        return array;
    }

    @Override
    public void solveArraySoup(char[] array) {

    }

}
