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
            Service service = Service.getService();
            int time = Service.getTimeService(service);
            bench.setClient(new Client(service,time));
            try {
                sleep((long)(Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (run);
    }

    public void finish(){
        run = false;
    }


}
