package server;

import util.IO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

    ServerSocket socket;

    private int MAX_USERS;
    private int currentActiveUsers;

    private boolean exitFlag = false;


    public ServerListener() {

        MAX_USERS = Main.getMaxUsers();
        currentActiveUsers = 0;
        try {
            socket = new ServerSocket(Main.getListenerPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        while (!exitFlag) {
            if (socket != null) {
                System.out.println("Waiting...");
                try {
                    Socket clientSocket = socket.accept();
                    if (currentActiveUsers == MAX_USERS) {
                        IO.sendLine("BYE", clientSocket.getOutputStream());
                        IO.sendLine("", clientSocket.getOutputStream());
                        clientSocket.close();
                    } else {
                        currentActiveUsers++;
                        new ServerCommunication(clientSocket, this).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Error, can't init socket listener");
                ;
            }
        }
    }

    public void endSession() {
        currentActiveUsers--;
    }
}
