package com.example.vaidyaraj;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
@Keep
public class patient_details extends AppCompatActivity {

    EditText mTextName, mTextAge;
    RadioButton mMale, mFemale;
    Button mButtonSubmit;
    String selectedGender, pName, pAge;
    String name1, gender1, documentId; //document ID can be used to identify the doctor
    String Visit_time, doc_date_booking, doc_phone_date_booking, Mobile_Number;
    double fee=0;
    double booking_total = 0, patient_limit = 0 ;
    static final String TAG = "MyActivity";
    CollectionReference pat_ref;
    Boolean nameChanged, ageChanged=false;
    String book_number;
    int limit_check=0;
    FirebaseAuth mAuth;


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
        pat_ref = db.collection("patient_details");
        FirebaseFirestore firebaseFirestore;
        mButtonSubmit.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();


        if (mMale.isChecked()) {
            selectedGender = mMale.getText().toString();
        } else if (mFemale.isChecked()) {
            selectedGender = mFemale.getText().toString();
        }
        name1 = getIntent().getStringExtra("name");
        gender1 = getIntent().getStringExtra("gender");
        documentId = getIntent().getStringExtra("documentId");
        Mobile_Number = getIntent().getStringExtra("phone");
        Visit_time = getIntent().getStringExtra("Visit_time");
        fee = getIntent().getDoubleExtra("fee", fee);

        final String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        doc_date_booking = documentId + currentDate;
        doc_phone_date_booking = Mobile_Number + currentDate;

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
                    patient_limit =  document.getDouble("patient_limit");
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData() + "keshav" +  patient_limit);

/***************************************************************************************************************/
                        db.collection("patient_details")
                                .whereEqualTo("doc_date_booking", doc_date_booking)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d(TAG, document.getId() + " ====> " + document.getData());
                                                booking_total = booking_total + 1;
                                            }
                                            Log.d(TAG, "Value" + booking_total);

                                            Log.d(TAG, "patient_limit" + patient_limit + "booking_total" + booking_total);
                                            if (  patient_limit <= booking_total) {
                                                LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout_patient_details);
                                                for (int i = 0; i < layout.getChildCount(); i++) {
                                                    View child = layout.getChildAt(i);
                                                    child.setEnabled(false);
                                                    Toast.makeText(getApplicationContext(), "Booking Limit over for the Doctor", Toast.LENGTH_SHORT).show();;

                                                }
                                            } else {}

                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                        limit_check=1;
                                    }
                                });        //patient_limit = 2.0;                     booking_total = 10.0;
                        Log.d(TAG, "patient_limit" + patient_limit + "booking_total" + booking_total);

/***************************************************************************************************************/


              } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

            // TO TEST IF THERE IS INPUT AND ENABLE BUTTON
        mTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {              }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { validateFields();   }

            @Override
            public void afterTextChanged(Editable s) {
                nameChanged = true;
            }
        });
if (nameChanged = true) {
    mTextAge.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateFields();
        }

        @Override
        public void afterTextChanged(Editable s) {
            ageChanged = true;
        }
    });

}
            mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (booking_total < patient_limit) {

                    Toast.makeText(getApplicationContext(), documentId, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Your Booking number is " + book_number, Toast.LENGTH_LONG).show();
                    //              String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault()).format(new Date());
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    pat_ref.whereEqualTo("state", "CA");
                    pName = mTextName.getText().toString();
                    pAge = mTextAge.getText().toString();
                    String currentUser1 = currentUser.getPhoneNumber();
                    booking_total = booking_total+1.0;
                    book_number = Double.toString(booking_total);

                    user.put("name", pName);
                    user.put("age", pAge);
                    user.put("gender", selectedGender);
                    user.put("doctor_name", name1);
                    user.put("doctor_details", gender1);
                    user.put("Booking_date", currentDate);
                    user.put("doc_date_booking", doc_date_booking);
                    user.put("Log_in_user", currentUser1);
                    user.put("Booking_number", book_number);
                    user.put("Login_user_date_booking", currentUser1+currentDate);
                    user.put("Doc_Phone_Date_booking", doc_phone_date_booking);
                    user.put("Visit_time", currentDate+" at "+Visit_time);

                    Log.d(TAG, "Doc_Phone_Date_booking " + doc_phone_date_booking);
                    if((fee!=0.0)) {

                        Log.d(TAG, "inside payment  " + fee);


                        db.collection("patient_details")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        Log.d(TAG, "book_number  " + book_number);
                                        Intent intents1 = new Intent(patient_details.this, payment.class);
                                        intents1.putExtra("name", pName);
                                        intents1.putExtra("book_number", book_number);
                                        intents1.putExtra("Visit_time", Visit_time);
                                        intents1.putExtra("doc_id", documentReference.getId());
                                        //startActivity(intents1);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });

                    } else {
                        db.collection("patient_details")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        Log.d(TAG, "book_number  " + book_number);
                                        Intent intents1 = new Intent(patient_details.this, booking_confirm.class);
                                        intents1.putExtra("name", pName);
                                        intents1.putExtra("book_number", book_number);
                                        intents1.putExtra("Visit_time", Visit_time);
                                        intents1.putExtra("doc_id", documentReference.getId());
                                        startActivity(intents1);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });
                    }
                    //book_number = book_number+2.0;

                    /*Intent intent = new Intent(patient_details.this, booking_confirm.class);
                    intent.putExtra("name", pName);
                    intent.putExtra("book_number", book_number);
                    intent.putExtra("Visit_time", Visit_time);
                    startActivity(intent);*/
                } else {
                    /**************************************************************************************************************************/

                }

            }
        });

    }

    public void validateFields()
    {
        String nName = mTextName.getText().toString().trim();
        String nAge = mTextAge.getText().toString().trim();
        if(nName.isEmpty()){

            mTextName.setError("Enter Valid Name");
            mTextName.requestFocus();
            mButtonSubmit.setEnabled(false);
            return;
            }


        if(nAge.isEmpty()){
            mTextAge.setError("Enter Valid Age");
         //   mTextAge.requestFocus();
            mButtonSubmit.setEnabled(false);
            return;
        }
        else{
            if(limit_check==0){
            SystemClock.sleep(2000);
            }

            else
                mButtonSubmit.setEnabled(true);
        }



    }
}