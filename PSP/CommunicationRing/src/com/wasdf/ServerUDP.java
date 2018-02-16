package com.wasdf;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerUDP {

    private String messagePost;
    private String tokenName;
    private int portListener;
    private int ringPosition;

    private ListenerToken listenerToken;

    private int ringLength;

    public void setToken(String tokenName){
        this.tokenName = tokenName + "_" + ringPosition;
        System.out.println(tokenName);
    }

    public ServerUDP(String ringLength, String ringPosition) {
        portListener = 10000;
        tokenName = "TOKEN";
        this.ringLength = Integer.parseInt(ringLength);
        this.ringPosition = Integer.parseInt(ringPosition);
        checkStatus(Integer.parseInt(ringPosition));
    }

    private void checkStatus(int ringPosition) {
        if (ringPosition == 1) {
            portListener += ringPosition;
            listenerToken = new ListenerToken(ringPosition,ringLength, portListener, this);
            listenerToken.start();
        } else {
            System.out.println("entrando en el else");
            portListener += ringPosition;
            messagePost = String.valueOf(ringPosition);
            listenerToken = new ListenerToken(ringPosition, ringLength, portListener, this);
            listenerToken.start();
            getToken();
        }
    }

    private void getToken() {
        try {
            DatagramSocket sSocket = new DatagramSocket();
            InetAddress machineAddress = InetAddress.getByName("localhost");
            byte[] string = messagePost.getBytes();
            int portToSend = portListener -1;
            if(ringPosition == ringLength){
                portToSend = (portListener - ringLength) + 1 ;
            }
            DatagramPacket datagramPacket = new DatagramPacket(string, messagePost.length(), machineAddress, portToSend);
            System.out.println("enviando mensaje a " + portToSend);
            sSocket.send(datagramPacket);
            System.out.println("mensaje enviado");
            sSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Desconocido: " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("E/S: " + e.getMessage());
        }
    }

    public void sendToken(int port){
        try {
            DatagramSocket sSocket = new DatagramSocket();
            InetAddress machineAddress = InetAddress.getByName("localhost");
            byte[] string = tokenName.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(string, string.length, machineAddress, port);
            sSocket.send(datagramPacket);
            sSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Desconocido: " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("E/S: " + e.getMessage());
        }
    }
}
