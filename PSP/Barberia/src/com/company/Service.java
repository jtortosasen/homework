package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Service {

    CUT, SHAVE, TINT;

    private static final List<Service> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Service randomService()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static int getTimeService(Service s) {
        int time = 0;
        switch (s) {
            case CUT:
                time = RANDOM.nextInt(200) + 301;
                break;
            case SHAVE:
                time = RANDOM.nextInt(50) + 151;
                break;
            case TINT:
                time = RANDOM.nextInt(100) + 601;
        }
        return time;
    }
}
