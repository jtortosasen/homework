package com.company;


import java.util.LinkedList;
import java.util.Queue;

public class Bench {

    private Queue<Client> bench;
    private boolean run;
    private int count;

    public Bench(){
        bench = new LinkedList<Client>();
        count = 0;
    }

    public synchronized Client getClient(){

        count--;
        return bench.remove();

    }

    public synchronized  void  setClient(Client client){
        if(count < 6){
            bench.add(client);
            count ++;
            notifyAll();
        }
    }
}
