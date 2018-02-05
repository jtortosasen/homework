package com.wasdf.examensurfaceview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Option1Activity extends MainActivity {

    SurfaceView1 surfaceView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView1 = new SurfaceView1(this);
        setContentView(surfaceView1);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
