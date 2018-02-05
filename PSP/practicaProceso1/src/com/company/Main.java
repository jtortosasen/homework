package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        try {

            ProcessBuilder builder = new ProcessBuilder("/bin/bash");
            builder.redirectErrorStream(true);
            Process process = builder.start();


            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            OutputStream op = process.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(op);

            BufferedReader bread = new BufferedReader(isr);
            BufferedWriter bwrite = new BufferedWriter(osr);

            BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));

            String command;

            command = brConsole.readLine();
            System.out.println(command);
            command = command.concat("\n");
            bwrite.write(command);


            bwrite.flush();

            String outputCommand;

            while ((outputCommand = bread.readLine())!= null){
                System.out.println(outputCommand);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
