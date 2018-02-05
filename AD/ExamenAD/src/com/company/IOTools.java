package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class IOTools {

    public static void dumpToXML(String nameFileTxtData, String txtSeparator,
                                 String nameFileXMLData, String nameNode) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nameNode, null);
            document.setXmlVersion("1.0");

            File file = new File(nameFileTxtData);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            String[] itemsFromFile;
            if (IOTools.getItems(randomAccessFile, txtSeparator) != null)
                itemsFromFile = IOTools.getItems(randomAccessFile, txtSeparator);
            else {
                itemsFromFile = new String[0];
                System.out.println("On function dumpToXML txt file is empty");
                return;
            }
            try {
                int counterLoop = 0;
                int counterNullItem = 0;
                String temp = "";
                String[] currentLine;
                ArrayList<String> notNullItem= new ArrayList<>();
                Element root = document.createElement(nameNode);
                document.getDocumentElement().appendChild(root);
                try {
                    do {
                        if(counterLoop == 0)
                            randomAccessFile.seek(0);
                        if(randomAccessFile.getFilePointer() + temp.length() >= randomAccessFile.length())
                            break;
                        temp = randomAccessFile.readLine();
                        currentLine = temp.split(txtSeparator);
                        for (int i = 0; i < currentLine.length; i++) {
                            if(counterLoop==0){
                                if(currentLine[i].indexOf('+')>0){
                                    notNullItem.add(currentLine[i]);
                                    currentLine[i] = currentLine[i].replace("+", "");
                                    itemsFromFile[i] = itemsFromFile[i].replace("+", "");
                                }
                            }
                            if(itemsFromFile[i].equals(notNullItem.get(counterNullItem))){
                                if(notNullItem.size()-1 > counterLoop)
                                    counterNullItem++;
                                if(currentLine[i].equals("null")){
                                    System.out.println("Campo " + itemsFromFile[i] + " no puede ser nulo");
                                    break;
                                }
                            }
                            if(!currentLine[i].equals("null"))
                                IOTools.createElement(itemsFromFile[i], currentLine[i], root, document);
                        }
                        counterLoop++;
                    } while (true);
                } catch (EOFException ex) {
                    System.out.println("Dump finished");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File(nameFileXMLData));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            randomAccessFile.close();

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }


    private static void createElement(String title, String value, Element root, Document document) {


        Element element = document.createElement(title);
        Text text = document.createTextNode(value);
        root.appendChild(element);
        element.appendChild(text);

    }

    private static String[] getItems(RandomAccessFile randomAccessFile, String txtSeparator) {
        try {
            randomAccessFile.seek(0);
            String temp = randomAccessFile.readLine();
            String[] nItems = temp.split(txtSeparator);
            return nItems;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Document getDOMFromFile(String nameFileXMLData) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new File("Empleados.xml"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println("On getDOMFromFile XML not found");
        return null;
    }

    public static void exportToDirectAccessFile(String nameFileTxtData, String nameRandomFile) throws IOException{
        File fileToWrite = new File(nameRandomFile);
        File fileToRead = new File(nameFileTxtData);

        RandomAccessFile randomAccessFileToWrite = new RandomAccessFile(fileToWrite,  "rw");
        RandomAccessFile randomAccessFileToRead = new RandomAccessFile(fileToRead, "rw");

        randomAccessFileToRead.seek(0);
        System.out.println(randomAccessFileToRead.length());
        for(int i = 0; i < randomAccessFileToRead.length(); i++){
            randomAccessFileToWrite.writeByte(randomAccessFileToRead.read());
        }

        randomAccessFileToRead.close();
        randomAccessFileToWrite.close();
    }

}
