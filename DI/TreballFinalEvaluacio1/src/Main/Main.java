package Main;

import javax.swing.JFrame;

import View.PanelInfo;

public class Main {

    private static final String WINDOW_TITLE = "Treball Final eva";
    private static final int WIDTH_WINDOW = 700;
    private static final int HEIGHT_WINDOW = 500;



    public static void main(String[] args) {
//        AlphabetSoupLogic alphabetSoupLogic = new AlphabetSoupLogic(FILE_WORDS_NAME,FILE_LETTERS_NAME);
//        AlphabetSoupView alphabetSoupView = new AlphabetSoupView();
//        new AlphabetController(alphabetSoupView, alphabetSoupLogic);
        PanelInfo panelInfo = new PanelInfo();

        JFrame jFrame = new JFrame(WINDOW_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setContentPane(panelInfo);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        jFrame.setVisible(true);
    }
}
