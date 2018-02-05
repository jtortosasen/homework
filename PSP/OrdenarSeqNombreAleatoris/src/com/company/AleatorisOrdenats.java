package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AleatorisOrdenats {

    static String classPath = "/home/wasdf/IdeaProjects/OrdenarSeqNombreAleatoris/out/production/OrdenarSeqNombreAleatoris/:.";
    static String programAleatori = "com.company.ProgramaAleatori";
    static String programOrdenat = "com.company.ProgramaOrdenat";

    public static void main(String[] args) {
        // write your code here
        int nRandoms;
        char outputCharType;
        Process[] process = new Process[2];
        process[0] = null;
        process[1] = null;

        if (args.length == 2) {
            if (Integer.parseInt(args[0]) <= 1000) {
                nRandoms = Integer.parseInt(args[0]);
                outputCharType = args[1].charAt(0);
                startProcesses(nRandoms, outputCharType, process);
            } else System.err.println("Max number less or equals than 1000");
        } else System.err.println("Invalid args");
    }


    private static void startProcesses(int nRandoms, char outputCharType, Process[] process) {

        String streamLine;

        try {
            ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", classPath, programAleatori, String.valueOf(nRandoms), String.valueOf(outputCharType));
            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", classPath, programOrdenat, String.valueOf(nRandoms));

            process[0] = pb1.start();
            process[1] = pb2.start();

            InputStream inputStream = process[0].getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = process[1].getOutputStream();
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            PrintStream ps = new PrintStream(outputStream);



            while ((streamLine = bufferedReader.readLine()) != null) {
                ps.println(streamLine);
                ps.flush();
                System.out.println(streamLine);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            inputStream = process[1].getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            while ((streamLine = bufferedReader.readLine())!= null){
                System.out.println(streamLine);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
