package Main;

import Controller.AlphabetController;
import Model.LOGIC.AlphabetSoupLogic;
import View.AlphabetSoupView;

import javax.swing.*;

public class Main {

    private static final String WINDOW_TITLE = "Sopa de letras";
    private static final String FILE_LETTERS_NAME = "letters.txt";
    private static final String FILE_WORDS_NAME = "words.txt";

    public static void main(String[] args) {
        AlphabetSoupLogic alphabetSoupLogic = new AlphabetSoupLogic(FILE_LETTERS_NAME,FILE_WORDS_NAME);
        AlphabetSoupView alphabetSoupView = new AlphabetSoupView();
        new AlphabetController(alphabetSoupView, alphabetSoupLogic);

        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(alphabetSoupView);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
