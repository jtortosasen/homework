package Main;

import Controller.AlphabetController;
import Model.AlphabetSoupModel;
import View.AlphabetSoupView;

import javax.swing.*;

public class Main {

    private static final String WINDOW_TITLE = "Sopa de letras";
    private static final String FILE_LETTERS_NAME = "letters.txt";
    public static final String FILE_WORDS_NAME = "words.txt";

    public static void main(String[] args) {
        AlphabetSoupModel alphabetSoupModel = new AlphabetSoupModel(FILE_LETTERS_NAME,FILE_WORDS_NAME);
        AlphabetSoupView alphabetSoupView = new AlphabetSoupView();
        AlphabetController alphabetController = new AlphabetController(alphabetSoupView, alphabetSoupModel);

        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(alphabetSoupView);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
