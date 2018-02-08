package com.wasdf.examensurfaceview;

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

public class SurfaceView1 extends SurfaceView implements SurfaceHolder.Callback {
    boolean run = true;
    AnimationThread animationThread = null;
    boolean shot =false;
    boolean startBullet = true;

    private float paddleWidth=20;
    private float paddleHeight=70;
    private float xPositionPaddle = 0;
    private float yPositionPaddle = 0;
    private float xPositionBullet = xPositionPaddle;
    private float yPositionBullet = yPositionPaddle;
    private float radiusBullet = 15;
    private float velocityBullet = 20;

    public SurfaceView1(Context context) {
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
        drawPaddle(canvas);
        if(shot)
            drawBullet(canvas);

    }

    private void drawBackground(Canvas canvas, Paint paint){
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), paint);

    }

    void drawBullet(Canvas canvas){
        if(startBullet){
            xPositionBullet = xPositionPaddle;
            yPositionBullet = yPositionPaddle;
            startBullet = false;
        }else{
            yPositionBullet = yPositionBullet - velocityBullet;
        }
        if(yPositionBullet < 1){
            shot = false;
            startBullet = true;
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xPositionBullet, yPositionBullet, radiusBullet,paint);
    }


    private void drawPaddle(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        yPositionPaddle = canvas.getHeight() - paddleHeight;
        canvas.drawRect(xPositionPaddle, yPositionPaddle,paddleWidth + xPositionPaddle, yPositionPaddle + paddleHeight,paint);
    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:{
                shot = true;
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                xPositionPaddle = (int) event.getX();
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
