package com.example.wasdf.touchexampleview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wasdf on 1/9/2018.
 */

public class TouchExampleView extends View {
    private Drawable mIcon;
    private float mPosX, mPosY;
    private float xDirection=30, yDirection=30;

    private float mLastTouchX, mLastTouchY;

    private float width;
    private float height;

    {
        height = (float) Resources.getSystem().getDisplayMetrics().heightPixels;
        width = (float) Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private boolean stop = false;

    public TouchExampleView(Context context){
        super(context);

        mIcon = context.getResources().getDrawable(R.drawable.ic_launcher_background);
        mIcon.setBounds(0,0,mIcon.getIntrinsicWidth(), mIcon.getIntrinsicHeight());
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        if(!stop){
            motion();
        }
        canvas.translate(mPosX,mPosY);
        mIcon.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x, y;
        float dx, dy;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if(!stop)
                    stop = true;
                else if(stop)
                    stop = false;
                x = ev.getX();
                y = ev.getY();
                mLastTouchX = x;
                mLastTouchY = y;
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                x = ev.getX();
                y = ev.getY();
                dx = x - mLastTouchX;
                dy = y - mLastTouchY;
                mPosX += dx;
                mPosY += dy;
                mLastTouchX = x;
                mLastTouchY = y;
                invalidate();
                break;
        }
        return true;
    }

    public void motion(){
        mPosX += xDirection;
        mPosY += yDirection;
        if((mPosX > width) || (mPosX < 0))
            xDirection = -xDirection;
        if((mPosY > height) || (mPosY < 0))
            yDirection = -yDirection;
        invalidate();
    }
}
