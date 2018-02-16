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
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    boolean run = true;
    boolean died = false;
    AnimationThread animationThread = null;

    Ship principalShip;
    ArrayList<Ship> enemyShip = new ArrayList<>();
    int maxEnemies = 15;
    ArrayList<Bullet> bullets = new ArrayList<>();

    long myTime;
    boolean resetTime = true;

    int points = 0;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        principalShip = new Ship(30, 50, 0, 0, 0, true);

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
        drawPoints(canvas);
        createEnemies(canvas);
        drawEnemies(canvas);
        drawPrincipalShip(canvas);
        drawBullet(canvas);
        if(died)
            died(canvas);
    }

    private void drawPoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf(points), 0, 40, paint);
    }

    private void died(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(200);
        canvas.drawText("YOU DIED", 0, canvas.getHeight() / 2, paint);
    }

    private void collision() {
        try {
            ArrayList<Bullet> removeBullets = new ArrayList<>();
            ArrayList<Ship> removeShips = new ArrayList<>();
            for (Bullet bullet : bullets) {
                for (Ship enemy : enemyShip) {
                    if (bullet.xPosittionBullet + bullet.radiusBullet > enemy.xPosition && bullet.xPosittionBullet + bullet.radiusBullet < enemy.xPosition + enemy.width
                            && bullet.yPositionBullet + bullet.radiusBullet > enemy.yPosition && bullet.yPositionBullet < enemy.yPosition + enemy.height) {
                        points += 1;
                        removeBullets.add(bullet);
                        removeShips.add(enemy);
                        break;
                    } else if (enemy.yPosition > principalShip.yPosition) {
                        died = true;
                    }
                }
                if (bullet.yPositionBullet < 1) {
                    Log.d("collision", "chocado con techo");
                    removeBullets.add(bullet);
                }
            }
            if(!removeBullets.isEmpty())
                bullets.removeAll(removeBullets);
            if(!removeShips.isEmpty())
                enemyShip.removeAll(removeShips);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
    }

    private void drawBackground(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void createEnemies(Canvas canvas) {
        if (enemyShip.size() < maxEnemies) {
            if (resetTime) {
                myTime = System.currentTimeMillis();
                myTime = TimeUnit.MILLISECONDS.toMillis(myTime);
                resetTime = false;
            }
            long estimatedTime = System.currentTimeMillis();
            estimatedTime = TimeUnit.MILLISECONDS.toMillis(estimatedTime);
            if (estimatedTime - myTime > 100) {
                Random random = new Random();
                enemyShip.add(new Ship(50, 200, random.nextInt(canvas.getWidth()-200) + 1, 0, random.nextInt(10) + 1, false));
                resetTime = true;
            }
        }
    }

    private void drawEnemies(Canvas canvas) {
        for (Ship enemy : enemyShip) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawRect(enemy.xPosition, enemy.yPosition, enemy.xPosition + enemy.width, enemy.yPosition + enemy.height, paint);
            enemy.yPosition += enemy.yVelocity;
        }
    }

    void drawBullet(Canvas canvas) {
        if (!bullets.isEmpty()) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);
            try {
                for (Bullet bullet : bullets) {
                    bullet.yPositionBullet -= bullet.velocityBullet;
                    canvas.drawCircle(bullet.xPosittionBullet, bullet.yPositionBullet, bullet.radiusBullet, paint);
                }
            }catch (ConcurrentModificationException e){
                e.printStackTrace();
            }
        }
    }

    void addBullet(float posX) {
        bullets.add(new Bullet(posX, principalShip.yPosition, 10, 20));
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
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                if(!died){
                    principalShip.xPosition = (int) event.getX();
                    addBullet(event.getX());
                    break;
                }
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
                collision();
            }
        }
    }
}

