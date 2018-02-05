package com.example.wasdf.appfarmacias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wasdf on 10/01/18.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Pharmacy";
    private static final int DATABASE_VERSION = 1;

    private String sqlCreateTownTable = "CREATE TABLE 'Towns' (" +
            "'id' INTEGER NOT NULL," +
            " 'name' TEXT NOT NULL," +
            " 'latitude' REAL NOT NULL," +
            " 'longitude' REAL NOT NULL," +
            " 'tag' TEXT NOT NULL," +
            " PRIMARY KEY('id')" +
            ");";

    private String sqlCreatePharmaciesTable = "CREATE TABLE 'Pharmacies' (" +
            " 'id' INTEGER NOT NULL," +
            " 'name' TEXT NOT NULL," +
            " 'latitude' REAL NOT NULL," +
            " 'longitude' REAL NOT NULL," +
            " 'town' INTEGER NOT NULL," +
            " 'address' TEXT NOT NULL," +
            " 'phone' TEXT NOT NULL," +
            " 'tag' TEXT NOT NULL," +
            " 'imgurl' TEXT NOT NULL" +
            ");";

    private String [] sqlInsertTowns = {
            "INSERT INTO Towns (id, name, latitude, longitude, tag) VALUES (0, 'Oliva', 38.919759, -0.118786, 'town');",
            "INSERT INTO Towns (id, name, latitude, longitude, tag) VALUES (1, 'Gandia', 38.968032, -0.1844671000000062, 'town');",
            "INSERT INTO Towns (id, name, latitude, longitude, tag) VALUES (2, 'Pego', 38.8429394, -0.11564390000000913, 'town');",
    };

    private String [] sqlInsertPharmacies = {
            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (0, 'Martí F.', 38.92311, -0.12309000000004744, 0, 'Carrer de lAlcalde Francesc Llorca, 14', '962.853.036','site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (1, 'Escrivà A.', 38.92143190000001, -0.11777230000006966, 0, 'Passeig de Lluís Vives, 11', '962.850.583', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (2, 'Catalá M.V.', 38.9208002, -0.11239339999997355, 0, 'Pl. Ajuntament, 3', '962.850.105', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (3, 'Farmacia Frasquet', 38.9727134, -0.18442849999996724, 1, 'Avenida República Argentina, 89', '962.844.055', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (4, 'Farmacia Azcon Malonda', 38.9664435, -0.18085480000002008, 1, 'Calle Major, 45', '962.534.543', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (5, 'Farmacia Manila', 39.0115498, -0.16870270000003984, 1, 'Paseo Maritim Neptu, 90', '962.323.322', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (6, 'Farmacia Llorca Llorca C.B.', 38.8430061, -0.11754450000000816, 2, 'Calle Ecce Homo, 12', '935.353.242', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (7, 'Ignacio Pascual Rovira', 38.8408216, -0.11761400000000322, 2, 'Calle Ramon y Cajal, 20', '962.243.555', 'site', 'url');",

            "INSERT INTO Pharmacies (id, name, latitude, longitude, town, address, phone, tag, imgurl)" +
                    "VALUES (8, 'Encarnacion Garrido Azpiroz', 38.8410441, -0.11652989999993224, 2, 'Avenida Jaume I, 4', '944.332.112', 'site', 'url');",

    };

    public MySQLiteHelper(Context context){
        super (context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreateTownTable);
        sqLiteDatabase.execSQL(sqlCreatePharmaciesTable);

        for (String sqlInsertTown : sqlInsertTowns)
            sqLiteDatabase.execSQL(sqlInsertTown);
        for (String sqlInsertPharmacy : sqlInsertPharmacies)
            sqLiteDatabase.execSQL(sqlInsertPharmacy);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
