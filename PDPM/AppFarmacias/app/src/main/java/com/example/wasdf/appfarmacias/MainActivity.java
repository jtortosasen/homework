package com.example.wasdf.appfarmacias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelper connection = new MySQLiteHelper(this);
        AccessData.setConnecction(connection);
    }

    public void start(View v){
        Intent intent = new Intent(MainActivity.this,ActivityFragments.class);
        this.startActivity(intent);
    }
}
