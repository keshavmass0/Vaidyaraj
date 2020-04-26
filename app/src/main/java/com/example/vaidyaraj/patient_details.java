package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class patient_details extends AppCompatActivity {

    EditText mTextName;
    EditText mTextAge;
    RadioButton mMale, mFemale;
    Button mButtonSubmit;
    String selectedGender;
    String name1, gender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        mTextName = (EditText)findViewById(R.id.editText_patient_name);
        mTextAge = (EditText)findViewById(R.id.editText_patient_age);
        mMale = (RadioButton)findViewById(R.id.male);
        mFemale = (RadioButton)findViewById(R.id.female);
        mButtonSubmit = (Button)findViewById(R.id.button_register_reg);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mMale.isChecked()) {
                    selectedGender = mMale.getText().toString();
                } else if (mFemale.isChecked()) {
                    selectedGender = mFemale.getText().toString();
                }
                Toast.makeText(getApplicationContext(), selectedGender, Toast.LENGTH_LONG).show(); // print the value of selected super star
            //    Intent LoginIntent = new Intent(patient_details.this, registerActivity.class);
            //    startActivity(LoginIntent);
            }
        });

        name1 = getIntent().getStringExtra("name");
        gender1 = getIntent().getStringExtra("gender");

/*
        TextView name, gender;
        String name1, gender1;
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);

        name1 = getIntent().getStringExtra("name");
        gender1 = getIntent().getStringExtra("gender");

        name.setText(name1);
        gender.setText(gender1);
*/
    }
}
