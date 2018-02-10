package com.wasdf.examensurfaceview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Option4Activity extends MainActivity {

    SurfaceView4 surfaceView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView4 = new SurfaceView4(this);
        setContentView(surfaceView4);
    }

    @Override
    protected void onPause(){
        super.onPause();
        surfaceView4.stopAnimation();
    }
}
