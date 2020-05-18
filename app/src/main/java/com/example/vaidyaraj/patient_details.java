package com.example.vaidyaraj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class patient_details extends AppCompatActivity {

    EditText mTextName, mTextAge;
    RadioButton mMale, mFemale;
    Button mButtonSubmit;
    String selectedGender, pName, pAge;
    String name1, gender1, documentId;
    String doc_details;
    long documentId1;

    static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        mTextName = (EditText)findViewById(R.id.editText_patient_name);
        mTextAge = (EditText)findViewById(R.id.editText_patient_age);
        mMale = (RadioButton)findViewById(R.id.male);
        mFemale = (RadioButton)findViewById(R.id.female);
        mButtonSubmit = (Button)findViewById(R.id.button_register_reg);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestore firebaseFirestore;


        if (mMale.isChecked()) {
            selectedGender = mMale.getText().toString();
        } else if (mFemale.isChecked()) {
            selectedGender = mFemale.getText().toString();
        }
        name1 = getIntent().getStringExtra("name");
        gender1 = getIntent().getStringExtra("gender");
        documentId = getIntent().getStringExtra("documentId");

        //documentId1 = getIntent().getIntExtra("documentId", 0);
     //   documentId1 = getIntent().getLongExtra("documentID", (long) 0.0);
     //   documentId1 = (int)documentId1;
//        doc_details = db.collection("doctors").document(documentId).get().toString();

        // Create a new user with a first and last name
        final Map<String, Object> user = new HashMap<>();
        //user.put("doctor-name", name1);
        //user.put("doctor-details", gender1);
        DocumentReference docRef = db.collection("doctors").document(documentId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Double value =  document.getDouble("patient_limit");
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData() + "keshav" +  value);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Toast.makeText(getApplicationContext(), selectedGender,  Toast.LENGTH_LONG).show(); // print the value of selected super star
                //    Intent LoginIntent = new Intent(patient_details.this, registerActivity.class);
                                Toast.makeText(getApplicationContext(),  documentId, Toast.LENGTH_LONG).show();

                //    startActivity(LoginIntent);
                pName = mTextName.getText().toString();
                pAge = mTextAge.getText().toString();
                user.put("name", pName);
                user.put("age", pAge);
                user.put("gender", selectedGender);
                user.put("doctor-name", name1);
                user.put("doctor-details", gender1);

                db.collection("patient_details")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });

// Add a new document with a generated ID
   /*     TextView name, gender;
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