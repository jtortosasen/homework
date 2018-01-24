package com.company;

import java.util.Random;

public class Door extends Thread{

    private Bench bench;
    private boolean run;

    public  Door(Bench bench){
        this.bench = bench;
        run = true;
    }

    public void run(){
        do{
            try {
                sleep((long)(Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Service service = Service.randomService();
            int time = Service.getTimeService(service);
            bench.addClient(new Client(service,time));
        }while (run);
    }

    public void finish(){
        run = false;
    }


}
