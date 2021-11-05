package com.example.vaidyaraj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class user_home1 extends AppCompatActivity {
    private static final String TAG = "Hi";
    FirebaseAuth mAuth;
    Button mBookAppointment, mMyBooking;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    String Doc_name, Patient_name, reporting_time, patient_Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home1);
        mAuth = FirebaseAuth.getInstance();
        mBookAppointment = (Button)findViewById(R.id.book_appointment);
        mMyBooking = (Button)findViewById(R.id.my_booking);
        findViewById(R.id.book_appointment).setVisibility(View.VISIBLE);
        mBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String currentUser_phone = currentUser.getPhoneNumber();
                final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//**************THIS DATA RETRIEVAL FUNCTIONALITY IS ALSO USED IN MY BOOKING ON CLICK LISTENER
                db.collection("patient_details")
                        .whereEqualTo("Login_user_date_booking", currentUser_phone+currentDate) // <-- This line
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        Patient_name = document.getString("name");
                                        Doc_name = document.getString("doctor_details");
                                        reporting_time = document.getString("Booking_date");
                                        Log.d(TAG, Patient_name);
                                        findViewById(R.id.book_appointment).setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Only one booking per day per doctor is allowed", Toast.LENGTH_SHORT).show();;

                                    }
                                    //in case of empty resultset for the day-start, above function will return success with 0 records
                                    // to handle the situation intent is started here

                                    if(task.getResult().isEmpty()){
                                        Intent registerIntent = new Intent(user_home1.this, doctor_List.class);
                                        startActivity(registerIntent);
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                   //findViewById(R.id.book_appointment).setVisibility(View.GONE);
                                }
                            }
                        });

        //        Intent registerIntent = new Intent(user_home1.this, doctor_List.class);
          //      startActivity(registerIntent);
            }
        });

        //check if user has done already a booking if yes then elope the button for booking

        mMyBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                String currentUser_phone = currentUser.getPhoneNumber();
                final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                db.collection("patient_details")
                        .whereEqualTo("Login_user_date_booking", currentUser_phone+currentDate) // <-- This line
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        Patient_name = document.getString("name");
                                        Doc_name = document.getString("doctor_details");
                                        reporting_time = document.getString("Booking_date");
                                        Log.d(TAG, Patient_name);
                                        findViewById(R.id.book_appointment).setVisibility(View.GONE);
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                   // findViewById(R.id.book_appointment).setVisibility(View.GONE);
                                }
                            }
                        });

                patient_Dialog = " Patient Name : " +Patient_name+ "\n Doctor :" +Doc_name+ "\n Time : " + reporting_time;
                openDialog(patient_Dialog);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {

                Intent intents = new Intent(user_home1.this, contact_us.class);
                startActivity(intents);
                   }
        });

/*        Button pmt = findViewById(R.id.payment);
        pmt.setOnClickListener(view -> {

            Intent intents1 = new Intent(user_home1.this, payment.class);
            startActivity(intents1);
        });*/
    }
    public  void openDialog(String msg){
      //  myDialog myDialog1 = new myDialog();
        myDialog myDialog1 = new myDialog().newInstance(msg);

        myDialog1.show(getSupportFragmentManager(), "Showtime");
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


    }

    private void updateUI(@Nullable FirebaseUser account) {
        if (account == null) {
            Intent intents = new Intent(user_home1.this, phone_auth.class);
            startActivity(intents);
/*            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
  */      } else {
/*            mStatusTextView.setText(R.string.signed_out);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
  */      }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}