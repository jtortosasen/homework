package com.wasdf.examensurfaceview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Option2Activity extends MainActivity {

    SurfaceView2 surfaceView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView2 = new SurfaceView2(this);
        setContentView(surfaceView2);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
