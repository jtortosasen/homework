package com.company;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) throws IOException{
        File fichero = new File("AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        int  id, dep, posicion=0; //para situarnos al principio del fichero
        Double salario;
        char apellido[] = new char[10], aux;

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document =
                    implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            for(;;) {
                file.seek(posicion); //nos posicionamos
                id=file.readInt();   // obtengo id de empleado
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux;
                }
                String apellidos = new String(apellido);
                dep = file.readInt();
                salario = file.readDouble();

                if(id>0) { //id validos a partir de 1
                    Element raiz =
                            document.createElement("empleado"); //nodo empleado
                    document.getDocumentElement().appendChild(raiz);

                    //a�adir ID
                    CrearElemento("id",Integer.toString(id), raiz, document);
                    //Apellido
                    CrearElemento("apellido",apellidos.trim(), raiz, document);
                    //a�adir DEP
                    CrearElemento("dep",Integer.toString(dep), raiz, document);
                    //a�adir salario
                    CrearElemento("salario",Double.toString(salario), raiz,
                            document);
                }
                posicion= posicion + 36; // me posiciono para el sig empleado
                if (file.getFilePointer() == file.length()) break;

            }//fin del for que recorre el fichero

            Source source = new DOMSource(document);
            Result result =
                    new StreamResult(new java.io.File("Empleados.xml"));
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        }catch(Exception e){ System.err.println("Error: "+e); }

        file.close();  //cerrar fichero
    }//fin de main

    //Inserci�n de los datos del empleado
    static void  CrearElemento(String datoEmple, String valor,
                               Element raiz, Document document){
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor
    }
}
