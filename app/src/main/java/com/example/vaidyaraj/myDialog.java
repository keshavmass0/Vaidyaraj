package com.example.vaidyaraj;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class myDialog extends AppCompatDialogFragment {

    public static myDialog newInstance(String msg) {
        myDialog fragment = new myDialog();

        Bundle bundle = new Bundle();
        bundle.putString("msg", msg); // set msg here
        fragment.setArguments(bundle);

        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Today's Booking Details ")
            .setMessage(getArguments().getString("msg"))
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        return builder.create();
    }
}
