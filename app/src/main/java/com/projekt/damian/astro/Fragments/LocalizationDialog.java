package com.projekt.damian.astro.Fragments;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.projekt.damian.astro.Localization;
import com.projekt.damian.astro.R;

public class LocalizationDialog extends AppCompatDialogFragment {
    private EditText latitude;
    private EditText longitude;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);

        builder.setView(view).setTitle("Localization")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String latitudeValue = latitude.getText().toString();
                        String longitudeValue = longitude.getText().toString();

                        if (!latitudeValue.matches("") && !longitudeValue.matches("")) {
                            if(Double.parseDouble(latitudeValue) > -90 && Double.parseDouble(latitudeValue) < 90 && Double.parseDouble(longitudeValue) > -180 && Double.parseDouble(longitudeValue) < 180   ) {
                                Localization.settLatitude(Double.parseDouble(latitudeValue));
                                Localization.setLongitude(Double.parseDouble(longitudeValue));
                            }else Toast.makeText(getActivity(), "latitude must be > -90 and < 90, Longitude must be > -180 and < 180", Toast.LENGTH_SHORT).show();
                        }

                        if (latitudeValue.matches("")) {
                            Toast.makeText(getActivity(), "You did not enter a lattitude value", Toast.LENGTH_SHORT).show();

                        } else if (longitudeValue.matches("")) {
                            Toast.makeText(getActivity(), "You did not enter a longitude value", Toast.LENGTH_SHORT).show();

                        }



                    }
                });
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        return builder.create();
    }

}
