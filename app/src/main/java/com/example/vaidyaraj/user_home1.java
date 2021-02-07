package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;

public class user_home1 extends AppCompatActivity {
    private static final String TAG = "Hi";
    FirebaseAuth mAuth;
    Button mBookAppointment, mMyBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home1);
        mAuth = FirebaseAuth.getInstance();

        mBookAppointment = (Button)findViewById(R.id.book_appointment);
        mMyBooking = (Button)findViewById(R.id.my_booking);

        mBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(user_home1.this, doctor_List.class);
                startActivity(registerIntent);
            }
        });
        mMyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                Log.d(TAG, "**********************************:" + currentUser);

                Intent registerIntent = new Intent(user_home1.this, booking_confirm.class);
                startActivity(registerIntent);
            }
        });


    }




}