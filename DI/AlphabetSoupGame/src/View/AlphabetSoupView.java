package View;

import Controller.AlphabetController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlphabetSoupView extends JPanel implements ViewControllerSoup{

    private AlphabetController controller;

    private JPanel panelLetters;
    private JButton buttonSolve;

    private DefaultListModel<String> modelSolvedWords = new DefaultListModel<>();
    private DefaultListModel<String> modelFoundWords = new DefaultListModel<>();
    private char[] arrayLetters;

    public AlphabetSoupView(){
        super();
        setBorder(new EmptyBorder(10,10,10,10));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        panelLetters = new JPanel();
        panelLetters.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(panelLetters, constraints);

        buttonSolve = new JButton("Solucionar");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 1.0;
        add(buttonSolve, constraints);

        JList listSolvedWords = new JList(modelSolvedWords);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        add(listSolvedWords, constraints);

        JList listFoundWords = new JList(modelFoundWords);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1.0;
        add(listFoundWords, constraints);

        buttonSolve.addActionListener(new ListenButon());
    }

    class ListenButon implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            buttonSolve.setEnabled(false);
            controller.resolveSoup(arrayLetters);
        }
    }

    @Override
    public void setViewController(AlphabetController controller) {
        this.controller = controller;
    }

    @Override
    public void setArraySolvedWords(String[] arraySolvedWords) {
        inflateSolvedList(arraySolvedWords);
    }

    @Override
    public void setArrayLettersSoup(char[] arrayLetterSoup) {
        inflateLetterSoup(arrayLetterSoup);
    }

    @Override
    public void setFoundWord(String word) {
        addToFoundWords(word);
    }

    private void inflateLetterSoup(char[] arrayLetterSoup){
        int rows = controller.getRowsSoup();
        int cols = controller.getColsSoup();

        for(int i = 0; i<rows; i++){
            for(int k = 0; k<cols; k++){
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = k;
                constraints.gridy = i;
                constraints.gridheight = 1;
                constraints.gridwidth = 1;
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                panelLetters.add(new JLabel(String.valueOf(arrayLetterSoup[i*cols+k])), constraints);
            }
        }

        // so that the actionListener can access the array to send to controller
        arrayLetters = arrayLetterSoup;
    }

    private void inflateSolvedList(String[] arraySolvedWords) {
        for(String array : arraySolvedWords)
            modelSolvedWords.addElement(array);
    }

    private void addToFoundWords(String word){
        modelFoundWords.addElement(word);
    }

}
