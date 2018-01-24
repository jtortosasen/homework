package com.company;


public class Barber extends Thread {

    Bench bench;
    boolean run;

    public Barber(Bench bench) {
        this.bench = bench;
        run = true;
    }

    public void run() {
        Client client;
        do {
            client = bench.getClient();

            if (client != null) {
                Service service  = client.getService();
                switch (service) {
                    case CUT: {
                        System.out.println("Cortando el pelo. Tardo " + client.getWorkTime());
                        break;
                    }
                    case SHAVE: {
                        System.out.println("Afeitando la barba. Tardo " + client.getWorkTime());
                        break;
                    }
                    case TINT: {
                        System.out.println("Tintando el pelo. Tardo " + client.getWorkTime());
                        break;
                    }
                }
                try {
                    sleep(client.getWorkTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }while (run || client != null);
    }

    public void finish() {
        run = false;
    }


}
