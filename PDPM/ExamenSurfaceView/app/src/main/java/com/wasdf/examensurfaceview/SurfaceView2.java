package com.wasdf.examensurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by wasdf on 5/02/18.
 */

public class SurfaceView2 extends SurfaceView implements SurfaceHolder.Callback{


    private AnimationThread animationThread = null;
    boolean run = true;

    private float heighAsteroid = 30;
    private float widthAsteroid = 100;

    private float xPositionAsteroid = 0;
    private float yPositionAsteroid = 0;

    private float velAsteroid = 15;

    public SurfaceView2(Context context){
        super(context);
        getHolder().addCallback(this);
    }

    public void newDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        addBackground(canvas, paint);
        drawAsteroid(canvas);
    }

    private  void drawAsteroid(Canvas canvas){
        Paint paint= new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(xPositionAsteroid, yPositionAsteroid,xPositionAsteroid+widthAsteroid, yPositionAsteroid+heighAsteroid,paint);
        if(xPositionAsteroid + widthAsteroid >= canvas.getWidth()){
            xPositionAsteroid = 0;
            yPositionAsteroid = yPositionAsteroid + heighAsteroid*2;
        }
        xPositionAsteroid = xPositionAsteroid + velAsteroid;
    }


    private void addBackground(Canvas canvas, Paint paint){
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        if (animationThread == null){
            animationThread = new AnimationThread(holder);
            animationThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }


    public void stopAnimation(){
        run = false;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        stopAnimation();
    }

    private class AnimationThread extends Thread{
        private  SurfaceHolder holder;

        public AnimationThread(SurfaceHolder surfaceHolder){
            holder = surfaceHolder;
        }

        public void run(){
            while(run){
                Canvas canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    synchronized (holder){
                        newDraw(canvas);
                    }
                }finally {
                    if(canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
