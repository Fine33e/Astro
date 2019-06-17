package com.projekt.damian.astro;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.io.Serializable;

public class AstroCalc implements Serializable {
    AstroCalculator astroCalculator;

    public AstroCalc(AstroDateTime time, AstroCalculator.Location location) {
        astroCalculator = new AstroCalculator(time, location);
    }

    //--------------------------------------------Sun Info -----------------------------------------
    public AstroDateTime getSunrise(){
        return astroCalculator.getSunInfo().getSunrise();
    }
    public AstroDateTime getSunset(){
        return astroCalculator.getSunInfo().getSunset();
    }
    public double getAzimuthRise(){
        return astroCalculator.getSunInfo().getAzimuthRise();
    }
    public double getAzimuthSet(){
        return astroCalculator.getSunInfo().getAzimuthSet();
    }
    public AstroDateTime getTwilightEvening(){ return astroCalculator.getSunInfo().getTwilightEvening(); }
    public AstroDateTime getTwilightMorning(){ return astroCalculator.getSunInfo().getTwilightMorning(); }

    //--------------------------------------------Moon Info ----------------------------------------
    public AstroDateTime getMoonrise(){
        return astroCalculator.getMoonInfo().getMoonrise();
    }
    public AstroDateTime getMoonset(){
        return astroCalculator.getMoonInfo().getMoonset();
    }
    public double getAge(){
        return astroCalculator.getMoonInfo().getAge();
    }
    public double getIlumination(){
        return astroCalculator.getMoonInfo().getIllumination();
    }
    public AstroDateTime getNextNewMoon(){
        return astroCalculator.getMoonInfo().getNextNewMoon();
    }
    public AstroDateTime getNextFullMoon(){ return astroCalculator.getMoonInfo().getNextFullMoon(); }

    //--------------------------------------------Set Localization----------------------------------
    public void setLocation(AstroCalculator.Location location){
        astroCalculator.setLocation(location);
    }

    public void setDateTime(AstroDateTime astroDateTime){
        astroCalculator.setDateTime(astroDateTime);
    }
}
