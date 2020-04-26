package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class booking_confirm extends AppCompatActivity {

    TextView name, gender;
    String name1, gender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);

        name1 = getIntent().getStringExtra("name");
        gender1 = getIntent().getStringExtra("gender");

        name.setText(name1);
        gender.setText(gender1);

    }
}
