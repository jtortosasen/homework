package com.company;

public class Barbershop {

    public static void main(String[] args) {
	// write your code here
        Bench bench = new Bench();
        Barber barber1 = new Barber(bench);
        Barber barber2 = new Barber(bench);
        Door door = new Door(bench);

        int workTime = 15000;

        System.out.println("####### Iniciando barbería...");

        barber1.start();
        barber2.start();
        door.start();

        try {
            Thread.sleep(workTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("####### Finalizando barbería...");

        barber1.finish();
        barber2.finish();
        door.finish();

    }
}
