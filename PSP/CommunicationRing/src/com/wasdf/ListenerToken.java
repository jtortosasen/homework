package com.wasdf;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListenerToken extends Thread {

    private int ringPosition;
    private int ringLength;

    private int portListener;

    ServerUDP serverUDP;


    public ListenerToken(int ringPosition, int ringLength, int portListener, ServerUDP serverUDP) {
        this.ringPosition = ringPosition;
        this.portListener = portListener;
        this.serverUDP = serverUDP;
        this.ringLength = ringLength;
        System.out.println("Soy " + ringPosition + " y escucho en : " + portListener);
    }

    public void run() {
        try {
            while (true) {
                System.out.println("entrando en thread");
                DatagramSocket socket = new DatagramSocket(portListener);
                byte[] string = new byte[1000];
                DatagramPacket messagePacket = new DatagramPacket(string, string.length);
                socket.receive(messagePacket);
                System.out.println("mensaje recibido");
                String message = new String(messagePacket.getData(), 0, messagePacket.getLength());
                socket.close();
                if (message.contains("TOKEN")) {
                    System.out.println("Token recibido");
                    serverUDP.setToken(message);
                } else {
                    System.out.println("Voy a enviar el token a: " + ((portListener - ringPosition) + Integer.parseInt(message)));
                    serverUDP.sendToken((portListener - ringPosition) + Integer.parseInt(message));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
