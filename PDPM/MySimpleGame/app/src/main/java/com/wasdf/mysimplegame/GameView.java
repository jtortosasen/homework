package com.wasdf.mysimplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by wasdf on 5/02/18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    boolean run = true;
    AnimationThread animationThread = null;

    Ship principalShip;
    ArrayList<Ship> enemyShip = new ArrayList<>();
    Bullet bullet;
    boolean shot = false;
    boolean startShooted = true;

    long myTime;
    boolean resetTime = true;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        principalShip = new Ship(30,50,0,0, 0,true);
        bullet = new Bullet(0,2,10,30);

        if (animationThread == null) {
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

    public void newDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        drawBackground(canvas, paint);
        createAsteroids(canvas);
        drawAsteroids(canvas);
        drawPrincipalShip(canvas);
        if (shot)
            drawBullet(canvas);
        collision(canvas);

    }

    private void collision(Canvas canvas) {
        int i = 0;
        for(Ship enemy : enemyShip){
            if (bullet.xPosittionBullet > enemy.xPosition && bullet.xPosittionBullet < enemy.yPosition + enemy.width
                    && bullet.yPositionBullet > enemy.yPosition && bullet.yPositionBullet < enemy.yPosition + enemy.height) {
                enemyShip.remove(i);
                shot = false;
                startShooted = true;
                Log.d("collision", "destruido");
            }
            if (enemy.yPosition > principalShip.yPosition) {
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(200);
                canvas.drawText("YOU LOSE", canvas.getWidth()/2, canvas.getHeight()/2, paint);

            }
            i++;
        }
        if(bullet.yPositionBullet < 1){
            shot = false;
            startShooted = true;
            Log.d("collision", "chocado con techo");
            bullet.yPositionBullet = principalShip.yPosition;
        }
    }

    private void drawBackground(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void createAsteroids(Canvas canvas){
        if(resetTime){
            myTime = System.currentTimeMillis();
            myTime = TimeUnit.MILLISECONDS.toSeconds(myTime);
            resetTime = false;
        }
        long estimatedTime = System.currentTimeMillis();
        estimatedTime = TimeUnit.MILLISECONDS.toSeconds(estimatedTime);
        if(estimatedTime - myTime > 1){
            enemyShip.add(new Ship(50,200,(float) (Math.random() * canvas.getWidth() + 1), 0, 5, false));
            resetTime = true;
        }
    }

    private void drawAsteroids(Canvas canvas){
        for(Ship enemy : enemyShip){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawRect(enemy.xPosition, enemy.yPosition, enemy.xPosition + enemy.width, enemy.yPosition + enemy.height, paint);
            enemy.yPosition += enemy.yVelocity;
        }
    }

    void drawBullet(Canvas canvas) {
       bullet.xPosittionBullet  = principalShip.xPosition;
       if(startShooted){
           bullet.yPositionBullet = principalShip.yPosition;
           startShooted = false;
       }else{
           bullet.yPositionBullet -= bullet.velocityBullet;
       }
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(bullet.xPosittionBullet, bullet.yPositionBullet, bullet.radiusBullet, paint);
    }


    private void drawPrincipalShip(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        principalShip.yPosition = canvas.getHeight() - principalShip.height;
        canvas.drawRect(principalShip.xPosition, principalShip.yPosition, principalShip.width + principalShip.xPosition, principalShip.yPosition + principalShip.height, paint);
    }


    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: {
                shot = true;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                principalShip.xPosition = (int) event.getX();
                break;
            }
        }
        return true;
    }

    public void stopAnimation() {
        run = false;
    }

    private class AnimationThread extends Thread {
        private SurfaceHolder holder;

        public AnimationThread(SurfaceHolder surfaceHolder) {
            holder = surfaceHolder;
        }

        public void run() {
            while (run) {
                Canvas canvas = null;
                try {
                    canvas = holder.lockCanvas();
                    synchronized (holder) {
                        newDraw(canvas);
                    }
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

