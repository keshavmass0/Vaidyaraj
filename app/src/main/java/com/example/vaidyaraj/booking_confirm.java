package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class booking_confirm extends AppCompatActivity {

    TextView name, visit_time;
    String name1;
    String book_number, Visit_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        name = findViewById(R.id.name);
        visit_time= findViewById(R.id.visit_time);

        name1 = getIntent().getStringExtra("name");
        book_number = getIntent().getStringExtra("book_number");
        Visit_time = getIntent().getStringExtra("Visit_time");

        name.setText("Your appointment has been confirmed with name " + name1 + " and appointment number " + book_number
                );
        visit_time.setText("\nPlease make sure to Reach hospital before " + Visit_time );
     /*   findViewById(R.id.signOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     FirebaseAuth.getInstance().signOut();
        //        Intent intents = new Intent(booking_confirm.this, user_home1.class);
        //        startActivity(intents);
            // m.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
        //    findViewById(R.id.book_appointment).setVisibility(View.GONE);
           // findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);


            }
        }); */
    }




    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(booking_confirm.this, user_home1.class);
        startActivity(setIntent);
    }
}