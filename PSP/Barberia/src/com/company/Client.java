package com.company;

public class Client {

    private Service service;
    private int workTime;

    public Client (Service service, int workTime){
        this .service = service;
        this.workTime = workTime;
    }

    public Service getService() {
        return service;
    }

    public int getWorkTime() {
        return workTime;
    }
}
