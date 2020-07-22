package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class booking_confirm extends AppCompatActivity {

    TextView name, ages;
    String name1;
    String book_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        name = findViewById(R.id.name);

        name1 = getIntent().getStringExtra("name");
        book_number = getIntent().getStringExtra("book_number");

        name.setText("Your appointment has been confirmed with name " + name1 + " and appointment number " + book_number);


    }
}