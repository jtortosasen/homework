package com.example.wasdf.touchexampleview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TouchExampleView touchExampleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        touchExampleView = new TouchExampleView(this);
        //setContentView(R.layout.activity_main);
        touchExampleView.setBackgroundColor(Color.WHITE);
        setContentView(touchExampleView);
    }
}
