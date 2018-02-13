package com.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorFTP {

    public static void main(String[] args) {
	// write your code here
        try{
            ServerSocket servidor = new ServerSocket(2000);
            System.out.println("Escucho al puerto " + 2000);
            int nCli=0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
