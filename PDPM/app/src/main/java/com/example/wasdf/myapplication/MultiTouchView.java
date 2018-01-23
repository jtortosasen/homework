package com.example.wasdf.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wasdf on 1/23/2018.
 */

public class MultiTouchView extends View {

    private static final int SIZE = 60;
    private SparseArray<PointF> mActivePointers;
    private Paint mPaint;
    private int[] colors = {
            Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN,
            Color.GRAY, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.YELLOW
    };
    private Paint textPaint;

    public MultiTouchView(Context context){
        super(context);
        initView();
    }

    private void initView(){
        mActivePointers = new SparseArray<PointF>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(int size = mActivePointers.size(), i=0; i<size; i++){
            PointF point = mActivePointers.valueAt(i);
            if(point != null){
                mPaint.setColor(colors[i % 9]);
                canvas.drawCircle(point.x, point.y, SIZE, mPaint);
            }
            textPaint.setTextSize(30);
            textPaint.setColor(Color.BLUE);
            canvas.drawText("Hijo d puta toca la pantalla", 10, 30, textPaint);
            canvas.drawText("Total pointers: " + mActivePointers.size(), 10, 60, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        int maskedAction = event.getActionIndex();
        switch (maskedAction){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:{
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId,f);
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                for (int size = event.getPointerCount(), i = 0; i<size; i++){
                    PointF point = mActivePointers.get(event.getPointerId(i));
                    if(point != null){
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:{
                mActivePointers.remove(pointerId);
                break;
            }
        }
        invalidate();
        return true;
    }
}
