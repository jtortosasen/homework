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
            for (int k = 0; k < cols; k++) {
                line.append(array[(i * cols) + k]);
            }
            checkLine(solvedWords, line);
        }

        //vertical
        for (int k = 0; k < cols; k++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                line.append(array[(i * cols) + k]);
            }
            checkLine(solvedWords, line);
        }

        //diagonal
        //transform array to adapt algorithm
        char[][] array1 = new char[rows][cols];
        //array2 for reverse
        char[][] array2 = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < cols; k++) {
                array1[i][k] = array[(i * cols) + k];
            }
        }
        //reverse rows
        for (int i = 0; i < rows; i++) {
            for (int k = 1; k < cols; k++) {
                array2[i][k] = array1[i][cols - k];
            }
        }
        moveDiagonals(array1, cols, solvedWords);
        moveDiagonals(array2, cols, solvedWords);
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

    private void checkLine(String[] solvedWords, StringBuilder line) {
        for (int j = 0; j < solvedWords.length; j++) {
            String searchVerse = line.toString();
            String searchReverse = new StringBuilder(searchVerse).reverse().toString();
            if (searchVerse.contains(solvedWords[j]) || searchReverse.contains(solvedWords[j])) {
                LOG.log(Level.INFO, "Found word: " + solvedWords[j]);
                controller.getFoundedWord(solvedWords[j]);
            }
        }
    }

    private void moveDiagonals(char[][] array, int cols, String[] solvedWords) {
        int c = 0;
        int count = array.length + array[0].length - 1;
        int i = 0, j = 0;
        //There can be at most m + n -1 diagonals to be printed
        while (c < count) {
            //Start getting diagonals from i and j
            getDiagonal(i, j, array, solvedWords);
            if (i < array.length - 1) {
                //We increment row index until we reach the max number of rows
                i++;
            } else if (j < array[0].length - 1) {
                //We are at maximum index of row; so its time to increment col index
                //We increment column index until we reach the max number of columns
                j++;
            }
            c++;
        }
    }

    private void getDiagonal(int i, int j, char[][] array, String[] solvedWords) {
        while (i >= 0 && j < array[0].length) {
            StringBuilder line = new StringBuilder();
            line.append(array[i][j]);
            checkLine(solvedWords, line);
            i--;
            j++;
        }
    }


}
