/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author joange
 */
public class ServidorFTP {

    private final int Puerto = 5555;
    private final int MAX_USERS = 4;
    private final String rutaBase = "/home/joange/serverFTP";


    private int connected_users = 0;

    public ServidorFTP() {
        try {
            ServerSocket sServidor = new ServerSocket(Puerto);
            System.out.println("Escucho el puerto " + Puerto);

            while (true) {
                System.out.println("\tEsperando conexion");
                Socket sCliente = sServidor.accept();
                System.out.println("Conexión recibida de " + sCliente.getRemoteSocketAddress());
                new FilServidorFTP(sCliente, this).start();
            }
            // System.out.println("Se han atendido los clientes");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        ServidorFTP elServer = new ServidorFTP();
    }

    public int getMAX_USERS() {
        return MAX_USERS;
    }

    public String getRutaBase() {
        return rutaBase;
    }

    public synchronized int getConnected_users() {
        return connected_users;
    }

    public synchronized void setConnected_users(int connected_users) {
        this.connected_users = connected_users;
    }


}
