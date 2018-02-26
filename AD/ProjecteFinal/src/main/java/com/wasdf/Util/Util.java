package com.wasdf.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Date stringToDate(String date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDate = null;
        try {
            dateDate = df.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDate;
    }

    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);

    }
}
