package PracticasGestorFichers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GestorFitxer extends JFrame {

    JTextArea textArea;

    public GestorFitxer() {
        super("Gestor fitxers");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton openButton = new JButton("Obrir");
        openButton.addActionListener(e -> openButtonEvent());
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        add(openButton, c);

        JButton closeButton = new JButton("Guardar");
        closeButton.addActionListener(e -> closeButtonEvent());
        c.gridx = 1;
        add(closeButton, c);

        textArea = new JTextArea();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        add(textArea, c);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);

    }

    private void openButtonEvent() {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros de tipo texto", "txt", "text");
        jFileChooser.setFileFilter(filter);
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String line = reader.readLine();
                while (line != null) {
                    textArea.append(line);
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void closeButtonEvent() {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros de tipo texto", "txt", "text");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();;
            try {
                PrintWriter writer = new PrintWriter(f);
                writer.print(textArea.getText());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new GestorFitxer();
    }

}
