package com.example.vaidyaraj;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }
    @SuppressLint("IntentReset")
    public void openGmail(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:keshavmass0@gmail.com"));
        String[] recipients = {"keshavbansal1412@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
        emailIntent.setType("text/plain");
        emailIntent.setPackage("com.google.android.gm");

        try {
            startActivity(emailIntent.createChooser(emailIntent, "send"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(contact_us.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();}
    }


  /*  public void openWhatsapp(View v){
        String number = "+91 9540707132";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }*/
}
