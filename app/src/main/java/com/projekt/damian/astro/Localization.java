package com.projekt.damian.astro;
import com.astrocalculator.AstroCalculator;


public class Localization {

    public static AstroCalculator.Location location= new AstroCalculator.Location(51.5,19.5);
    private static int refreshTime=1;

    public static Double getLatitude(){
        return location.getLatitude();
    }

    public static Double getLongitude(){
        return location.getLongitude();
    }

    public static void settLatitude(Double value){
        location.setLatitude(value);
    }

    public static void setLongitude(Double value){
        location.setLongitude(value);
    }

    //-------------------------------------------------------refresh time=======================

    public static void setRefreshTime(int value){
        refreshTime = value;
    }

    public static int getRefreshTime(){
        return refreshTime;
    }
}