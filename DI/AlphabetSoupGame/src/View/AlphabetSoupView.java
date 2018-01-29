package View;

import Controller.AlphabetController;

import javax.swing.*;
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
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        panelLetters = new JPanel();
        panelLetters.setLayout(new GridLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(panelLetters, constraints);

        buttonSolve = new JButton();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        add(buttonSolve, constraints);

        JList listSolvedWords = new JList(modelSolvedWords);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        add(listSolvedWords, constraints);

        JList listFoundWords = new JList(modelFoundWords);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        add(listFoundWords, constraints);

        buttonSolve.addActionListener(new ListenButon());
    }

    class ListenButon implements ActionListener{
        public void actionPerformed(ActionEvent ae){
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
        inflateLettersList(arrayLetterSoup);
    }

    @Override
    public void setFoundWord(String word) {
        addToFoundWords(word);
    }

    private void inflateLettersList(char[] arrayLetterSoup){
        for (char array : arrayLetterSoup) {
            panelLetters.add(new JLabel(String.valueOf(array)));
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
