package com.wasdf.examensurfaceview;

/**
 * Created by Wasdf on 2/7/2018.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by wasdf on 5/02/18.
 */

public class SurfaceView4 extends SurfaceView implements SurfaceHolder.Callback {
    boolean run = true;
    AnimationThread animationThread = null;
    boolean shot =false;
    boolean drawLine;
    boolean firstStep = true;

    private float bulletXPos = 0;
    private float bulletYPos = 0;
    private float bulletRadius = 15;
    private float bulletVelocity = 20;

    private float fingerXPos = 0;
    private float fingerYPos = 0;


    public SurfaceView4(Context context) {
        super(context);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (animationThread == null){
            animationThread = new AnimationThread(holder);
            animationThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void newDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        drawBackground(canvas, paint);
        drawBall(canvas);
        if(drawLine)
            drawLineToShoot(canvas);
        if(shot){
            addVelocityToBullet();
            shot = false;
        }
    }

    private void drawBackground(Canvas canvas, Paint paint){
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), paint);

    }

    private void drawLineToShoot(Canvas canvas){
        bulletVelocity = 0;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawLine(fingerXPos,fingerYPos,bulletXPos,bulletYPos,paint);
    }

    private void drawBall(Canvas canvas){
        if(firstStep){
            bulletXPos = canvas.getWidth()/2;
            bulletYPos = canvas.getHeight()/2;
        }else{
            bulletXPos += bulletVelocity;
            bulletYPos += bulletVelocity;
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(bulletXPos, bulletYPos, bulletRadius, paint);
    }

    private void addVelocityToBullet(){
        if(firstStep)
            firstStep = false;

        float distance = (float) Math.sqrt((float)(Math.pow(bulletXPos,2) - Math.pow(fingerXPos,2) + Math.pow(bulletYPos,2) - Math.pow(fingerYPos,2)));
        bulletVelocity = distance/2;

    }


    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:{
                shot = true;
                drawLine = false;
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                if(!drawLine)
                    drawLine = true;
                fingerXPos = (int) event.getX();
                fingerYPos = (int) event.getY();
                break;
            }
        }
        return true;
    }

    public void stopAnimation(){
        run = false;
    }

    private class AnimationThread extends Thread{
        private  SurfaceHolder holder;

        public AnimationThread(SurfaceHolder surfaceHolder){
            holder = surfaceHolder;
        }

        public void run(){
            Log.d("tag","run");
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
