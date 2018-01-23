package com.company;

import java.util.Random;

public enum Service {

    CUT, SHAVE, TINT;

    private static Random r = new Random(System.currentTimeMillis());

    public static Service getService() {
        int n = r.nextInt(3);
        Service s = null;
        switch (n) {
            case 0:
                s = CUT;
                break;
            case 1:
                s = SHAVE;
                break;
            case 2:
                s = TINT;
        }
        return s;
    }

    public static int getTimeService(Service s) {
        int time = 0;
        switch (s) {
            case CUT:
                time = r.nextInt(200) + 301;
                break;
            case SHAVE:
                time = r.nextInt(50) + 151;
                break;
            case TINT:
                time = r.nextInt(100) + 601;
        }
        return time;
    }
}
