package com.wasdf;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            new ServerUDP(args[0],args[1]);
        } else {
            System.err.println("Err. args: [type:int length] [type:int position]");
        }

    }
}


//        else try {
//            // Crea el socket
//            DatagramSocket sSocket = new DatagramSocket();
//            // Construye la dirección del socket del receptor
//            InetAddress machineAddress = InetAddress.getByName("localhost");
//            int Puerto = 1500;
//            // Crea el mensaje
//            byte[] cadena = args[0].getBytes();
//            DatagramPacket mensaje = new DatagramPacket(cadena, args[0].length(), maquina, Puerto);
//            // Envía el mensaje
//            sSocket.send(mensaje);
//            // Cierra el socket
//            sSocket.close();
//        } catch (UnknownHostException e) {
//            System.err.println("Desconocido: " + e.getMessage());
//        } catch (SocketException e) {
//            System.err.println("Socket: " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("E/S: " + e.getMessage());
//        }