package com.company;


import java.util.LinkedList;
import java.util.Queue;

public class Bench {

    private Queue<Client> bench;

    public Bench() {
        bench = new LinkedList<>();
    }

    public synchronized Client getClient() {
        Client client = null;
        do {
            if (bench.isEmpty()) {
                System.out.println("El barbero " + Thread.currentThread().getName() + " se sienta.");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("El barbero " + Thread.currentThread().getName() + " atiende a cliente.");
                client = bench.remove();
                notifyAll();
            }
        } while (client == null);
        return client;
    }

    public synchronized void addClient(Client client) {
        if (!isFull()) {
            bench.add(client);
            System.out.println("A;ade cliente");
            notifyAll();
        } else {
            try {
                System.out.println("El cliente se queda fuera");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isFull() {
        return bench.size() >= 6;
    }
}
