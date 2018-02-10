package com.wasdf.mysimplegame;

import android.graphics.Canvas;

/**
 * Created by wasdf on 8/02/18.
 */

public class Ship {

    public float height;
    public float width;
    public float xPosition;
    public float yPosition;
    public float yVelocity;
    public boolean isPrincipalShip;

    public Ship(float height, float width, float xPosition, float yPosition, float yVelocity, boolean isPrincipalShip){
        this.height = height;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isPrincipalShip = isPrincipalShip;
        this.yVelocity = yVelocity;
    }

}
