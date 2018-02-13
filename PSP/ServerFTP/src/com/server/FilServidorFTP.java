package com.server;

import java.net.Socket;

public class FilServidorFTP extends Thread{
    private Socket socket;
    private ServidorFTP servidor;

    public FilServidorFTP(Socket socket, ServidorFTP servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }
}
