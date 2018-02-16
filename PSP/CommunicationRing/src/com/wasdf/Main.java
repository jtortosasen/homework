package com.wasdf;

import java.io.IOException;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2) {
            new ServerUDP(args[0],args[1]);
        } else {
            System.err.println("Err args: [type:int length] [type:int position]");
        }
    }
}

