package com.example.wasdf.appfarmacias;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class ActivityFragments extends AppCompatActivity implements FragmentMaps.CallbackListener{

    FragmentMaps fragmentMaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        fragmentMaps = new FragmentMaps();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, fragmentMaps);
        ft.commit();
    }

    @Override
    public void locationIdInformation(int id){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, InformationLocation.newInstance(id));
        ft.addToBackStack(null);
        ft.commit();
    }
}
