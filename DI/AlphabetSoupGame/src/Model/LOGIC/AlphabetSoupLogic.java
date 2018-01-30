package Model.LOGIC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.ControllerSoup;
import Model.DAO.DataAccess;

public class AlphabetSoupLogic implements ModelSoup {

    DataAccess dataAccess;
    ControllerSoup controller;
    private static final Logger LOG = Logger.getLogger(AlphabetSoupLogic.class.getName());


    public AlphabetSoupLogic(String file_words, String file_letters) {
        dataAccess = new DataAccess(file_words, file_letters);
    }

    @Override
    public void setModelController(ControllerSoup controller) {
        this.controller = controller;
    }

    @Override
    public char[] getLettersSoup() {
        ArrayList<Character> arrayList = dataAccess.getFileLettersAsArray();
        if (arrayList != null) {
            char[] array = new char[arrayList.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = arrayList.get(i);
            }
            return array;
        } else {
            LOG.log(Level.WARNING, "ArrayList letters is null");
            return new char[0];
        }
    }

    @Override
    public String[] getSolvedWords() {
        ArrayList<String> arrayList = dataAccess.getFileWordsAsArray();
        if (arrayList != null) {
            String[] array = new String[arrayList.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = arrayList.get(i);
            }
            return array;
        } else {
            LOG.log(Level.WARNING, "ArrayList words is null");
            return new String[0];
        }
    }

    @Override
    public void solveArraySoup(char[] array) {
        int rows = dataAccess.getRowsLettersFile();
        int cols = dataAccess.getColsLettersFile();
        String[] solvedWords = getSolvedWords();

        //horizontal
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            for (int k = 0; i < cols; i++) {
                line.append(array[(i * cols) + k]);
            }
            for (int j = 0; j < solvedWords.length; j++) {
                String[] search = line.toString().split("\\b+");
                if (Arrays.asList(search).contains(solvedWords[j])) {
                    LOG.log(Level.INFO, "Found word on row: " + i);
                    controller.getFoundedWord(solvedWords[j]);
                }
            }
        }

        //horizontal reverse
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            for (int k = 0; i < cols; i++) {
                line.append(array[(i * cols) + k]);
            }
            for (int j = 0; j < solvedWords.length; j++) {
                String[] search = line.toString().split("\\b+");
                Collections.reverse(Arrays.asList(search));
                if (Arrays.asList(search).contains(solvedWords[j])) {
                    LOG.log(Level.INFO, "Found word on row: " + i);
                    controller.getFoundedWord(solvedWords[j]);
                }
            }
        }

        //vertical
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            line.append(array[(i * cols)]);

            for (int j = 0; j < solvedWords.length; j++) {
                String[] search = line.toString().split("\\b+");
                Collections.reverse(Arrays.asList(search));
                if (Arrays.asList(search).contains(solvedWords[j])) {
                    LOG.log(Level.INFO, "Found word on row: " + i);
                    controller.getFoundedWord(solvedWords[j]);
                }
            }
        }


        //vertical reverse
        for (int i = 0; i < rows; i++) {
            StringBuilder line = new StringBuilder();
            line.append(array[(i * cols)]);

            for (int j = 0; j < solvedWords.length; j++) {
                String[] search = line.toString().split("\\b+");
                Collections.reverse(Arrays.asList(search));
                if (Arrays.asList(search).contains(solvedWords[j])) {
                    LOG.log(Level.INFO, "Found word on row: " + i);
                    controller.getFoundedWord(solvedWords[j]);
                }
            }
        }

        //diagonal
//        for(int i = 0; i < rows; i++){
//            for(int k = 0; i<rows; i)
//        }

        //diagonal reverse




    }

    @Override
    public int getRowsSoup() {
        int rows = dataAccess.getRowsLettersFile();
        if (rows == 0)
            LOG.log(Level.WARNING, "error rows 0");
        return rows;
    }

    @Override
    public int getColsSoup() {
        int cols = dataAccess.getColsLettersFile();
        if (cols == 0)
            LOG.log(Level.WARNING, "error cols 0");
        return cols;
    }

}
