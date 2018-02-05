package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        File fichero = new File("AleatorioEmple.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");

        int idEmp = Integer.parseInt(args[0])-1;
        double imporArg = Double.parseDouble(args[1]);
        int id, dep, posicion;
        double salario;
        char apellido[] = new char[10];

        posicion = idEmp * 36;
        if(posicion < file.length()){
            file.seek(posicion);
            id = file.readInt();
            for (int i = 0; i < apellido.length; i++) {
                apellido[i] = file.readChar();
            }
            String apellidoS = new String(apellido);
            dep = file.readInt();
            salario = file.readDouble();
            System.out.println(
                    "ID: " + id +
                            ", Apellido: " + apellidoS +
                            ", Departamento: " + dep +
                            ", Salario antiguo: " + salario +
                            ", Salario nuevo: " + (salario + imporArg));

            posicion = ((idEmp-1) * 36) + 28;
            file.seek(posicion);
            file.writeDouble(salario + imporArg);
            file.close();
        }else
            System.out.println("Usuario no existente");



    }
}
