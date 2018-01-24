package com.company;

import java.util.Random;

public class Door extends Thread {

    private Bench bench;
    private boolean run;

    public Door(Bench bench) {
        this.bench = bench;
        run = true;
    }

    public void run() {
        while (run) {
            try {
                sleep((long) (Math.random() * 700));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Service service = Service.randomService();
            int time = Service.getTimeService(service);
            bench.addClient(new Client(service, time));
        }
    }

    public void finish() {
        run = false;
    }


}
