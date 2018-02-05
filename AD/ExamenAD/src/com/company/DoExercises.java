package com.company;

import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DoExercises {

    static String nameFileTxtData = "e-datos.txt";
    static String nameFileXMLData = "datos.xml";
    static String nameFileTxtModifications = "modificaciones.txt";
    static String nameNode = "alumno";
    static String nameRandomFile = "alumnos.dat";

    Document document;
    DoExercises myExercises = new DoExercises();

    public static void getOptionMenu(int option){
        switch (option){
            case 1:
                exercise1();
                break;
            case 2:
                exercise2();
                break;
            case 3:
//                exercise3();
                break;
            default:
                System.out.println("Error selección opción");
        }
    }

    private static void exercise2() {
        try {
            IOTools.exportToDirectAccessFile(nameFileTxtData, nameRandomFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void exercise1(){
        String separator = ",";
        try {
            IOTools.dumpToXML(nameFileTxtData,separator, nameFileXMLData, nameNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
