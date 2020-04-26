package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registerActivity extends AppCompatActivity {
    EditText mTextUser;
    EditText mTextPass;
    Button mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mTextUser = (EditText)findViewById(R.id.editText_username_reg);
        mTextPass = (EditText)findViewById(R.id.editText_password_reg);
        mTextPass = (EditText)findViewById(R.id.editText_confirm_password);
        mButtonRegister = (Button)findViewById(R.id.button_register_reg);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(registerActivity.this, phone_auth.class);
                startActivity(LoginIntent);
            }
        });
    }
}
