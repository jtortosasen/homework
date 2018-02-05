package com.example.wasdf.examenandroid;

import java.util.ArrayList;

/**
 * Created by Wasdf on 11/9/2017.
 */

public class Motos {
    // Arrays amb informació sobre les motos
    static ArrayList<String> nomMotos = new ArrayList<>();
    static ArrayList<Double> preuMotos = new ArrayList<>();

    public Motos() {
        nomMotos.add("Honda NC700"); nomMotos.add("BMW");   nomMotos.add( "Yamaha");
        preuMotos.add(7000.00);   preuMotos.add(8500.00);  preuMotos.add(7200.00);
    }

    public String getNomMoto(int i) {
        return nomMotos.get(i);
    }

    public double getPreuMoto(int i) {
        return preuMotos.get(i);
    }

}
