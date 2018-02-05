package com.wasdf.examensurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by wasdf on 5/02/18.
 */

public class SurfaceView1 extends SurfaceView implements SurfaceHolder.Callback {
    boolean run = true;
    AnimationThread animationThread;

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

    }

    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:{
//                xPositionPaddle = (int) event.getX();
//                break;
//            }
            case MotionEvent.ACTION_MOVE:{
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
