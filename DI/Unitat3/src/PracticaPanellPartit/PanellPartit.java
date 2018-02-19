package PracticaPanellPartit;

import javax.swing.*;

public class PanellPartit extends JFrame{

    private DefaultListModel<String> modelImagesList = new DefaultListModel<>();
    JLabel image;

    public PanellPartit(){
        super("Panell Partit");
        JPanel imagePanel = new JPanel();
        image = new JLabel();
        imagePanel.add(image);
        JPanel selectionPanel = new JPanel();
        modelImagesList.addElement("Image1");
        modelImagesList.addElement("Image2");
        modelImagesList.addElement("Image3");
        JList imageList = new JList(modelImagesList);
        imageList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()){
                JList source = (JList)event.getSource();
                int selected = source.getSelectedIndex();
                switch (selected){
                    case 0:{
                        image.setIcon(new ImageIcon(getClass().getResource("Image1.jpg")));
                        break;
                    }
                    case 1:{
                        image.setIcon(new ImageIcon(getClass().getResource("Image2.jpg")));
                        break;
                    }
                    case 2:{
                        image.setIcon(new ImageIcon(getClass().getResource("Image3.jpg")));
                        break;
                    }
                }
            }
        });
        selectionPanel.add(imageList);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, selectionPanel);
        getContentPane().add(splitPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PanellPartit();
    }


}
