package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int option;
        do{
            System.out.println("Bienvenido al programa.");
            System.out.println("Selecciona las opciones secuencialmente: ");
            System.out.println("1. Lectura del archivo principal y carga en DOM");
            System.out.println("2. Volcado a archivo.dat");
            System.out.println("4. Exit");
            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            if(option<4)
                DoExercises.getOptionMenu(option);
        }while(option != 4);
    }

}
