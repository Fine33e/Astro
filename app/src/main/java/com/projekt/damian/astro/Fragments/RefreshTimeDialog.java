package com.projekt.damian.astro.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projekt.damian.astro.Localization;
import com.projekt.damian.astro.R;

public class RefreshTimeDialog extends AppCompatDialogFragment {

    private EditText refreshTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.refresh_dialog,null);

        builder.setView(view).setTitle("Refresh time in minutes")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Localization.setRefreshTime(Integer.parseInt(refreshTime.getText().toString()));
                    }
                });
        refreshTime = view.findViewById(R.id.refreshTime);

        return builder.create();
    }
}
