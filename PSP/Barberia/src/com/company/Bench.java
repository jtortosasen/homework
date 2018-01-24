package com.company;


import java.util.LinkedList;
import java.util.Queue;

public class Bench {

    private Queue<Client> bench;
    private boolean run;

    public Bench(){
        bench = new LinkedList<>();
        run = true;
    }

    public synchronized Client getClient(){
        Client client = null;
        do{
            if(bench.isEmpty()){
                System.out.println("El barbero se sienta.");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                client = bench.remove();
                notifyAll();
            }
        }while (client == null|| !run);
        return client;
    }

    public synchronized  void addClient(Client client){
        boolean exitFlag = false;
        do{
            if(!isFull()){
                bench.add(client);
                exitFlag = true;
                notifyAll();
            }else {
                try {
                    System.out.println("El cliente se queda fuera");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }while (exitFlag || !run);

    }

    private boolean isFull(){
        return bench.size() >= 6;
    }
}
