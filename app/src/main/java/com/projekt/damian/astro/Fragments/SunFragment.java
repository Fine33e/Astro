package com.projekt.damian.astro.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projekt.damian.astro.AstroCalc;
import com.projekt.damian.astro.AstroDT;
import com.projekt.damian.astro.Localization;
import com.projekt.damian.astro.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.os.Looper.getMainLooper;

public class SunFragment extends Fragment {

    private TextView time2;
    private TextView latitude, longitude, sunrise,sunset,azimuthRise,azimuthSet, twilightEvening, twilightMorning;
    private AstroCalc astroCalc;
    private int refreshTime;

    private boolean isPaused = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sun, container, false);
        time2 = (TextView)view.findViewById(R.id.time2);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        sunrise = view.findViewById(R.id.sunrise);
        sunset = view.findViewById(R.id.sunset);
        azimuthRise = view.findViewById(R.id.azimuthrise);
        azimuthSet= view.findViewById(R.id.azimuthset);
        twilightEvening = view.findViewById(R.id.twilightEvening);
        twilightMorning = view.findViewById(R.id.twilightMorning);

        refreshTime=5;

        astroCalc = new AstroCalc(AstroDT.astroDateTime, Localization.location);


        latitude.setText(Localization.getLatitude().toString());
        longitude.setText(Localization.getLongitude().toString());

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time2.setText(new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date()));
                someHandler.postDelayed(this, 10);
            }
        }, 10);

        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                someHandler.postDelayed(this, 10);


                if(!latitude.getText().equals(Localization.getLatitude().toString()) && !longitude.getText().equals(Localization.getLongitude().toString())) {
                    latitude.setText(Localization.getLatitude().toString());
                    longitude.setText(Localization.getLongitude().toString());
                    astroCalc.setLocation(Localization.location);
                    setTime();

                    if(isPaused){
                        someHandler.removeCallbacks(this);
                    }

                    astroCalc.setDateTime(AstroDT.astroDateTime);
                    setData();
                }

                refreshTime = Localization.getRefreshTime();
            }
        }, 10);


            someHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    someHandler.postDelayed(this, 60000 * refreshTime);
                    setTime();

                    astroCalc.setDateTime(AstroDT.astroDateTime);
                    setData();

                }
            }, 10);

        if(!isPaused){
            Toast.makeText(getActivity(), "Update Data ", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    @Override
    public void onPause() {
        isPaused = true;
        super.onPause();
    }
    public void setTime(){
        AstroDT.setYear(Integer.parseInt(new SimpleDateFormat("yyyy", Locale.US).format(new Date())));
        AstroDT.setMonth(Integer.parseInt(new SimpleDateFormat("MM", Locale.US).format(new Date())));
        AstroDT.setDay(Integer.parseInt(new SimpleDateFormat("dd", Locale.US).format(new Date())));
        AstroDT.setHour(Integer.parseInt(new SimpleDateFormat("HH", Locale.US).format(new Date())));
        AstroDT.setMinute(Integer.parseInt(new SimpleDateFormat("mm", Locale.US).format(new Date())));
        AstroDT.setSecond(Integer.parseInt(new SimpleDateFormat("ss", Locale.US).format(new Date())));

        System.out.println(new SimpleDateFormat("yyyy", Locale.US).format(new Date()));
        System.out.println(new SimpleDateFormat("MM", Locale.US).format(new Date()));
        System.out.println(new SimpleDateFormat("dd", Locale.US).format(new Date()));
        System.out.println(new SimpleDateFormat("HH", Locale.US).format(new Date()));
        System.out.println(new SimpleDateFormat("mm", Locale.US).format(new Date()));
        System.out.println(new SimpleDateFormat("ss", Locale.US).format(new Date()));
    };

    public void setData(){
        sunrise.setText(astroCalc.getSunrise().toString());
        sunset.setText(astroCalc.getSunset().toString());
        azimuthRise.setText(Double.toString(astroCalc.getAzimuthRise()));
        azimuthSet.setText(Double.toString(astroCalc.getAzimuthSet()));
        twilightEvening.setText(astroCalc.getTwilightEvening().toString());
        twilightMorning.setText(astroCalc.getTwilightMorning().toString());
    }

}