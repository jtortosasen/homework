package com.example.wasdf.surfaceviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MySurfaceView mySurfaceView = new MySurfaceView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mySurfaceView);

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        mySurfaceView.stopAnimation();
//
//    }
}
