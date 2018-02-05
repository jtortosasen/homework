package com.wasdf.examensurfaceview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Option3Activity extends MainActivity {

    SurfaceView3 surfaceView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView3 = new SurfaceView3(this);
        setContentView(surfaceView3);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
