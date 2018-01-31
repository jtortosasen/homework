package Main;

import java.awt.FlowLayout;

import Controller.AlphabetController;
import Model.LOGIC.AlphabetSoupLogic;
import View.AlphabetSoupView;

import javax.swing.*;

public class Main {

    private static final String WINDOW_TITLE = "Sopa de letras";
    private static final int WIDTH_WINDOW = 580;
    private static final int HEIGHT_WINDOW = 340;
    private static final String FILE_LETTERS_NAME = "letters.txt";
    private static final String FILE_WORDS_NAME = "words.txt";


    public static void main(String[] args) {
        AlphabetSoupLogic alphabetSoupLogic = new AlphabetSoupLogic(FILE_WORDS_NAME,FILE_LETTERS_NAME);
        AlphabetSoupView alphabetSoupView = new AlphabetSoupView();
        new AlphabetController(alphabetSoupView, alphabetSoupLogic);

        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(alphabetSoupView);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        jFrame.setVisible(true);
    }
}
