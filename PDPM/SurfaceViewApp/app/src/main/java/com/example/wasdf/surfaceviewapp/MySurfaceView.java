package com.example.wasdf.surfaceviewapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by Wasdf on 1/16/2018.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private AnimationThread animationThread = null;
    boolean run = true;


    private int xBall= 0, yBall = 0;
    private int vectorX = 20, vectorY = 20;
    private int radius = 30;

    private int heightPaddle = 40;
    private int widthPaddle = 200;

    private int xPositionPaddle = 0;
    private int yPositionPaddle = 0;

    public MySurfaceView(Context context){
        super(context);
        getHolder().addCallback(this);
    }

    public void newDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        addBackground(canvas, paint);
        drawPaddle(canvas);
        motionBall(canvas);
    }

    private  void drawPaddle(Canvas canvas){
        yPositionPaddle = canvas.getHeight() - heightPaddle;
        Paint paint= new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(xPositionPaddle, yPositionPaddle,xPositionPaddle+widthPaddle, yPositionPaddle+heightPaddle,paint);
    }



    private void motionBall(Canvas canvas){
        if(xBall < 0 || xBall > canvas.getWidth())
            vectorX *= -1;
        if(yBall < 0 || yBall > canvas.getHeight() || (yBall  + radius >= yPositionPaddle && (xBall > xPositionPaddle && xBall + radius < xPositionPaddle + widthPaddle)))
            vectorY *= -1;
        xBall = (xBall + vectorX);
        yBall = (yBall + vectorY);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xBall,yBall,radius,paint);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                xPositionPaddle = (int) event.getX();
//                break;
//            }
            case MotionEvent.ACTION_MOVE:{
                xPositionPaddle += (int) event.getX() - xPositionPaddle;
                break;
            }
        }
        return true;
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
