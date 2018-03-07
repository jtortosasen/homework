/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.IO;

/**
 * @author joange
 */
public class FilServidorFTP extends Thread {

    private Socket elSocket;            //ja està conectat al client
    private ServidorFTP elServidor;     // refernecia al Servidor General

    private OutputStream salida;
    private InputStream entrada;

    private String rutaActual;

    public FilServidorFTP(Socket elSocket, ServidorFTP elServidor) {
        try {
            this.elSocket = elSocket;
            this.elServidor = elServidor;

            salida = elSocket.getOutputStream();
            entrada = elSocket.getInputStream();

        } catch (IOException ex) {
            Logger.getLogger(FilServidorFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        rutaActual = elServidor.getRutaBase();
        //  comprovem si podem entrar
        try {
            if (elServidor.getConnected_users() < elServidor.getMAX_USERS()) {
                IO.escribeLinea("WELCOME", salida);
                IO.escribeLinea("", salida);
                IO.escribeLinea(rutaActual, salida);
                elServidor.setConnected_users(elServidor.getConnected_users() + 1);
            } else {
                IO.escribeLinea("BYE", salida);
                IO.escribeLinea("", salida);
                elSocket.close();
                return;
            }
            // USUARI CONECTAT
            boolean acabar = false;
            while (!acabar) {
                //LLEGIR PETICIÓ DEL CLIENT
                String peticio = IO.leeLinea(entrada);
                System.out.println("El client demana <" + peticio + ">");
                String[] parts = peticio.split("[ ]+");
                // PROCESSEM PETICIO
                String comando = parts[0];
                switch (parts.length) {
                    case 1:
                        switch (comando) {
                            case "PWD":
                                IO.escribeLinea("200 " + comando + " OK", salida);
                                IO.escribeLinea("", salida);
                                IO.escribeLinea(rutaActual, salida);
                                break;
                            case "DIR":
                            case "LS":
                                String resposta = "";
                                File f = new File(rutaActual);
                                File[] elements = f.listFiles();
                                for (File fitxer : elements) {
                                    if (fitxer.isFile()) {
                                        resposta += fitxer.getName() + "#";
                                    } else {
                                        resposta += "<" + fitxer.getName() + ">#";
                                    }
                                }
                                resposta += "\b";

                                IO.escribeLinea("200 " + comando + " OK", salida);
                                IO.escribeLinea("", salida);
                                IO.escribeLinea(resposta, salida);

                                break;
                            case "EXIT":
                                IO.escribeLinea("200 " + comando + " OK", salida);
                                IO.escribeLinea("", salida);
                                IO.escribeLinea("BYE", salida);
                                acabar = true;
                                break;
                            default:
                                IO.escribeLinea("400 PETICIO MAL FORMADA", salida);
                                IO.escribeLinea("", salida);
                        }
                        break;
                    case 2:
                        switch (comando) {
                            case "CD":
                                String carpeta = parts[1];  // serà '.' o '..' o 'carpeta'
                                switch (carpeta) {
                                    case ".":
                                        IO.escribeLinea("200 " + comando + " OK", salida);
                                        IO.escribeLinea("", salida);
                                        IO.escribeLinea(rutaActual, salida);
                                        break;
                                    case "..":
                                        if (rutaActual.equals(elServidor.getRutaBase())) {
                                            IO.escribeLinea("405 CANNOT GO UP TO BASEPATH", salida);
                                            IO.escribeLinea("", salida);
                                        } else {
                                            File f = new File(rutaActual);
                                            IO.escribeLinea("200 " + comando + " OK", salida);
                                            IO.escribeLinea("", salida);
                                            rutaActual = f.getParent();
                                            IO.escribeLinea(rutaActual, salida);
                                        }
                                        break;
                                    default:  // CD 'algoMes'
                                        String arxiu = rutaActual + "/" + carpeta;
                                        File f = new File(arxiu);
                                        if (!f.exists()) {
                                            IO.escribeLinea("404 NOT FOUND", salida);
                                            IO.escribeLinea("", salida);
                                        } else if (f.isFile()) {
                                            IO.escribeLinea("406 IS FILE", salida);
                                            IO.escribeLinea("", salida);
                                        } else {  //es un directori
                                            IO.escribeLinea("200 " + comando + " OK", salida);
                                            IO.escribeLinea("", salida);
                                            rutaActual = arxiu;
                                            IO.escribeLinea(rutaActual, salida);
                                        }

                                }
                                break;
                            case "GET":
                                String arxiu = rutaActual + "/" + parts[1];
                                File f = new File(arxiu);
                                if (!f.exists()) {
                                    IO.escribeLinea("404 NOT FOUND", salida);
                                    IO.escribeLinea("", salida);
                                } else if (f.isDirectory()) {
                                    IO.escribeLinea("405 IS DIRECTORY", salida);
                                    IO.escribeLinea("", salida);
                                } else {
                                    IO.escribeLinea("200 GET OK " + arxiu + " " + f.length(), salida);
                                    IO.escribeLinea("", salida);
                                    // enviar fitxer per el socket
                                    long tam = IO.copia(new FileInputStream(f), salida);
                                    if (tam != f.length()) {
                                        System.out.println("Error en la transferencia");
                                    }
                                }
                                break;
                            default:
                                IO.escribeLinea("400 PETICIO MAL FORMADA", salida);
                                IO.escribeLinea("", salida);
                        }

                        break;
                    case 3:

                        break;
                    default:
                        IO.escribeLinea("400 PETICIO MAL FORMADA", salida);
                        IO.escribeLinea("", salida);
                }
            }

            // Hem acabat
            salida.close();
            entrada.close();
            elSocket.close();
            elServidor.setConnected_users(elServidor.getConnected_users() - 1);

        } catch (IOException ex) {
            Logger.getLogger(FilServidorFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
