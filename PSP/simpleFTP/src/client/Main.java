package client;

import util.IO;
import util.Utils;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        OutputStream out;
        InputStream in;

        try {

            Socket socket = new Socket("localhost", 5555);

            in = socket.getInputStream();
            out = socket.getOutputStream();

            String reply = IO.getLine(in);
            IO.getLine(in);
            String remotePath;

            if (reply.equals("WELCOME")) {
                remotePath = IO.getLine(in);
                System.out.println("Connectat al servidor");
                System.out.println("La ruta remota es <" + remotePath + ">");
            } else {
                System.out.println("Impossible connectar.");
                System.out.println("Prova més tard.");
                in.close();
                out.close();
                socket.close();
                System.exit(1);
            }

            boolean finishFlag = false;
            while (!finishFlag) {
                // demanar peticio a l'usuari
                String command = Utils.readTextG("Comando a enviar al servidor?");

                // enviar-la al server
                IO.sendLine(command, out);

                // llegir resposta del server
                reply = IO.getLine(in);
                IO.getLine(in);
                System.out.println("El servidor respon <" + reply + ">");
                //informar a l'usuari

                // Anàlisi de la info de control
                if (reply.startsWith("4")) {
                    // Algo va mal
                    Utils.showMessageG(reply);
                } else {
                    // 200 PWD OK
                    // 200 DIR OK
                    // 200 LS OK
                    // 200 CD OK
                    // 200 GET OK FITXER tamanyEnBytes
                    // 200 PUT OK
                    String[] parts = reply.split("[ ]+");
                    String cmd = parts[1];

                    switch (cmd) {
                        case "PWD":
                            remotePath = IO.getLine(in);
                            Utils.showMessageG("La ruta remota es " + remotePath);
                            break;
                        case "DIR":
                        case "LS":
                            String resp = IO.getLine(in);
                            System.out.println("El servidor respon <" + resp + ">");
                            String[] elements = resp.split("#");
                            String respUsuari = "";
                            for (String element : elements) {
                                respUsuari += element + "\n";
                            }
                            respUsuari += "\b";
                            Utils.showMessageG(respUsuari);
                            break;

                        case "GET":
                        File file = new File("/home/wasdf/ftp/" + parts[3]);
                        FileOutputStream fout = new FileOutputStream(file);
                        IO.copy(in, fout, Long.parseLong(parts[4]));

                        case "PUT":
                            String[] name = command.split(" ");
                            File sendFile = new File("/home/wasdf/ftp/" + name[1]);
                            FileInputStream fin = new FileInputStream(sendFile);
                            IO.copy(fin, out, sendFile.length());
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
