package com.example.vaidyaraj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myadaptor1 extends RecyclerView.Adapter<myadaptor1.MyViewFolder> {

    String data1[], data2[];
    int images[] ;
    Context context;

    public myadaptor1(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public MyViewFolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);

        return new  MyViewFolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewFolder holder, int position) {

        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);


    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewFolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;

        public MyViewFolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView_proglang);
            myText2 = itemView.findViewById(R.id.textView2_description);
            myImage = itemView.findViewById(R.id.imageView1);

        }
    }
}
