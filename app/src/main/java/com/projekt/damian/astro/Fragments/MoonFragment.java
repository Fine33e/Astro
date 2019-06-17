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

public class MoonFragment extends Fragment {
    private TextView time2;
    private TextView latitude, longitude, moonrise,moonset,age,ilumination, nextFullMoon, nextNewMoon;
    private AstroCalc astroCalc;
    private int refreshTime;
    volatile boolean activityStopped = false;
    Handler someHandler;
    private boolean isPaused =false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.moon, container, false);
        time2 = (TextView)view.findViewById(R.id.time2);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        moonrise = view.findViewById(R.id.moonrise);
        moonset = view.findViewById(R.id.moonset);
        nextFullMoon = view.findViewById(R.id.nextFullMoon);
        nextNewMoon= view.findViewById(R.id.nextNewMoon);
        ilumination = view.findViewById(R.id.ilumination);
        age = view.findViewById(R.id.age);

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
                    Toast.makeText(getActivity(), "Update Data ", Toast.LENGTH_SHORT).show();
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
        isPaused=true;
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
        moonrise.setText(astroCalc.getMoonrise().toString());
        moonset.setText(astroCalc.getMoonset().toString());
        nextFullMoon.setText(astroCalc.getNextFullMoon().toString());
        nextNewMoon.setText(astroCalc.getNextNewMoon().toString());
        ilumination.setText(Double.toString(astroCalc.getIlumination()));
        age.setText(Double.toString(astroCalc.getAge()));
    }

}
