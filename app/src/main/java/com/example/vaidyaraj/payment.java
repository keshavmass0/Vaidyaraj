package com.example.vaidyaraj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class payment extends AppCompatActivity  implements PaymentResultListener {
    private static final String TAG = payment.class.getSimpleName();
    String name1, fee1;
    String book_number, Visit_time, doc_id;
    double fee=0.0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

       /*          To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.          */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = findViewById(R.id.btn_pay);
        fee =  getIntent().getDoubleExtra("fee", fee);
        TextView amt =  findViewById(R.id.amount);
        fee1= new Double(fee/100).toString();
        amt.setText(fee1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        /*           You need to pass current activity in order to let Razorpay create CheckoutActivity          */
        final Activity
                activity = this;

        final Checkout co = new Checkout();
        co.setKeyID("rzp_live_M2PTdBE9UP0D0M");
         fee =  getIntent().getDoubleExtra("fee",0.0);

         //  Toast.makeText(this, "inside Payment activity : " + fee, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "inside Payment activity :   " + fee);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Vaidyaraj");
            options.put("description", "Booking charges");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", fee);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "keshavbansal1412@gmail.com");
            preFill.put("contact", "9811018970");

            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            String err = (e.getMessage()==null)?"SD Card failed":e.getMessage();
            e.printStackTrace();
            Log.e("sdcard-err2:", err);
        /*    Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        */}
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            name1 = getIntent().getStringExtra("name");
            book_number = getIntent().getStringExtra("book_number");
            Visit_time = getIntent().getStringExtra("Visit_time");
            doc_id = getIntent().getStringExtra("doc_id");
           // getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            final FirebaseFirestore db = FirebaseFirestore.getInstance();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",100);
            int resultCode=2;
            setResult(resultCode, returnIntent);
            finish();
      /*      db.collection("patient_details").document(doc_id).update("Payment", "Success");
            Intent intent = new Intent(payment.this, booking_confirm.class);
            intent.putExtra("name", name1);
            intent.putExtra("book_number", book_number);
            intent.putExtra("Visit_time", Visit_time);
            intent.putExtra("Visit_time", Visit_time);

            startActivity(intent);*/
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();

            // this.finish();
            //finish();
            Intent returnIntent = new Intent(this, patient_details.class);
            returnIntent.putExtra("result",100);
            int resultCode=1;
            setResult(resultCode, returnIntent);
            finish();
            System.exit(0);

        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        finish();

// Finish all activities in stack and app closes
        finishAffinity();

    }
}