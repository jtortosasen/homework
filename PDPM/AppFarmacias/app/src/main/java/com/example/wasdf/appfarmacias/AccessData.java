package com.example.wasdf.appfarmacias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Wasdf on 1/11/2018.
 */

class AccessData {

    private static SQLiteDatabase db;

    static void setConnecction(MySQLiteHelper connect){
        db = connect.getReadableDatabase();
    }



    AccessData(){
    }

    int getTowns(){
        Cursor c = db.rawQuery("SELECT id FROM Towns",null);
        return c.getCount();
    }

    LatLng getPosTown(int id){
        Double lat, lon;
        Cursor c = db.rawQuery("SELECT latitude, longitude FROM Towns WHERE id=?", new String[] {String.valueOf(id)});
        c.moveToFirst();
        lat = Double.parseDouble(c.getString(0));
        lon = Double.parseDouble(c.getString(1));
        c.close();
        return new LatLng(lat,lon);

    }

    String getNameTown(int id){
        Cursor c = db.rawQuery("SELECT name FROM Towns WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    String getTagTown(int id){
        Cursor c = db.rawQuery("SELECT tag FROM Towns WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    int getIdFromNameTown(String name){
        Cursor c = db.rawQuery("SELECT id FROM Towns WHERE name=?",new String [] {name});
        c.moveToFirst();
        return Integer.parseInt(c.getString(0));
    }

    int[] getAllIdSites(int id){
        Cursor c = db.rawQuery("select id from Pharmacies where town = ?", new String[] {String.valueOf(id)});
        int[] array = new int [c.getCount()];
        c.moveToFirst();
        for(int i=0; i<array.length; i++){
            array[i] = c.getInt(0);
            c.moveToNext();
        }

        return array;
    }

    LatLng getPosSite(int id){
        Double lat, lon;
        Cursor c = db.rawQuery("SELECT latitude, longitude FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        lat = Double.parseDouble(c.getString(0));
        lon = Double.parseDouble(c.getString(1));
        c.close();
        return new LatLng(lat,lon);
    }

    String getNameSite(int id){
        Cursor c = db.rawQuery("SELECT name FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    String getTagSite(int id){
        Cursor c = db.rawQuery("SELECT tag FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    String getPhoneSite(int id){
        Cursor c = db.rawQuery("SELECT phone FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    String getStreetSite(int id){
        Cursor c = db.rawQuery("SELECT address FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }

    String getImageSite(int id){
        Cursor c = db.rawQuery("SELECT imgurl FROM Pharmacies WHERE id=?",new String [] {String.valueOf(id)});
        c.moveToFirst();
        return c.getString(0);
    }


}
