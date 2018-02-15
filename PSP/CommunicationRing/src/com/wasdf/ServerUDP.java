package com.wasdf;

public class ServerUDP {

    private int ringLength;
    private int ringPosition;

    private String message;
    private int portListener;



    public ServerUDP(String arg, String arg1) {
        ringLength = Integer.parseInt(arg);
        ringPosition = Integer.parseInt(arg1);
        checkStatus();
    }

    private void checkStatus(){
        if(ringPosition == 0){
            message = "TOKEN";
            port = 10000 + Integer.parseInt(ringPosition)
        }else if(ringPosition == ringLength - 1){

        }else{

        }
    }
}
