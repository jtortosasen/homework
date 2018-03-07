/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.IO;
import Utils.Utilitats;

/**
 * @author joange
 */
public class ClienteFTP {

    private static final int puerto = 5555;

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
        OutputStream salida;
        InputStream entrada;
        try {
            // ens connectem al servidor
            Socket elSocket = new Socket("localhost", puerto);

            entrada = elSocket.getInputStream();
            salida = elSocket.getOutputStream();

            // llegim 2 linies
            String resposta = IO.leeLinea(entrada);
            String inutil = IO.leeLinea(entrada);
            String rutaRemota = "";

            if (resposta.equals("WELCOME")) {
                rutaRemota = IO.leeLinea(entrada);
                System.out.println("Connectat al servidor");
                System.out.println("La ruta remota es <" + rutaRemota + ">");
            } else { //BYE
                System.out.println("Impossible connectar.");
                System.out.println("Prova més tard.");
                entrada.close();
                salida.close();
                elSocket.close();
                System.exit(1);
            }

            boolean acabar = false;

            while (!acabar) {
                // demanar peticio a l'usuari
                String peticio = Utilitats.leerTextoG("Comando a enviar al servidor?");

                // enviar-la al server
                IO.escribeLinea(peticio, salida);


                // llegir resposta del server

                resposta = IO.leeLinea(entrada);
                inutil = IO.leeLinea(entrada);
                System.out.println("El servidor respon <" + resposta + ">");
                //informar a l'usuari

                // Anàlisi de la info de control

                if (resposta.startsWith("4")) {
                    // Algo va mal
                    Utilitats.muestraMensajeG(resposta);
                } else {
                    // 200 PWD OK
                    // 200 DIR OK
                    // 200 LS OK
                    // 200 CD OK
                    // 200 GET OK FITXER tamanyEnBytes
                    // 200 PUT OK
                    String[] parts = resposta.split("[ ]+");
                    String cmd = parts[1];

                    switch (cmd) {
                        case "PWD":
                            rutaRemota = IO.leeLinea(entrada);
                            Utilitats.muestraMensajeG("La ruta remota es " + rutaRemota);
                            break;
                        case "DIR":
                        case "LS":
                            String resp = IO.leeLinea(entrada);
                            System.out.println("El servidor respon <" + resp + ">");
                            String[] elements = resp.split("#");
                            String respUsuari = "";
                            for (String element : elements) {
                                respUsuari += element + "\n";
                            }
                            respUsuari += "\b";
                            Utilitats.muestraMensajeG(respUsuari);
                            break;
                    }

                }

            }
            //  ?????


        } catch (IOException ex) {
            Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
