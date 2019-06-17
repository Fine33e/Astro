package com.projekt.damian.astro;

import com.astrocalculator.AstroDateTime;

public class AstroDT {
    public static AstroDateTime astroDateTime = new AstroDateTime(2012,5,20,19,26,10,2,false);

    public static void setYear(int value){
        astroDateTime.setYear(value);
    }

    public static void setMonth(int value){
        astroDateTime.setMonth(value);
    }

    public static void setDay(int value){
        astroDateTime.setDay(value);
    }

    public static void setHour(int value){
        astroDateTime.setHour(value);
    }

    public static void setMinute(int value){
        astroDateTime.setMinute(value);
    }

    public static void setSecond(int value){
        astroDateTime.setSecond(value);
    }

}