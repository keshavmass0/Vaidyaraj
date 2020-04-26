package com.example.vaidyaraj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDexApplication;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class doctor_List extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    FirestoreRecyclerAdapter adapter;

 /*   String s1[];
    String s2[];
    int images[] = {R.drawable.pass, R.drawable.pass, R.drawable.pass}; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__list);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.my_recycler_view);

        Query query = firebaseFirestore.collection("doctors");

        FirestoreRecyclerOptions<doctors_Model> options = new FirestoreRecyclerOptions.Builder<doctors_Model>()
                .setQuery(query, doctors_Model.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<doctors_Model, doctorsViewHolder>(options){
            @NonNull
            @Override
            public doctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
                return new doctorsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull doctorsViewHolder holder, int position, @NonNull doctors_Model model) {
                holder.name.setText(model.getName());
                holder.gender.setText(model.getGender());


            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public class doctorsViewHolder extends RecyclerView.ViewHolder{

        TextView name, gender;
            public doctorsViewHolder(@NonNull final View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.textView_proglang);
                gender = itemView.findViewById(R.id.textView2_description);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position =  getAdapterPosition();
                        Intent intent = new Intent(doctor_List.this, booking_confirm.class);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("gender", gender.getText().toString());
                        startActivity(intent);
                        }
                });

            }
    }



    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //    s1 = getResources().getStringArray(R.array.programming_languages);
    //     s2 = getResources().getStringArray(R.array.description);
    //   myadaptor1 myadaptor1 = new myadaptor1(this, s1, s2, images);
    //   recyclerView.setAdapter(myadaptor1);
    //   recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }