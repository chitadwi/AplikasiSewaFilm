package com.example.aplikasisewafilm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter untuk recycler view
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_sewa, nama_sewa, judul_filem;
    private Object v;
    Activity activity;


    CustomAdapter(Activity activity, Context context, ArrayList id_sewa, ArrayList nama_sewa, ArrayList judul_filem){
        this.activity = activity;
        this.context = context;
        this.id_sewa = id_sewa;
        this.nama_sewa = nama_sewa;
        this.judul_filem = judul_filem;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_list, parent, false);
        return new MyViewHolder(view);

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id_sewa.get(position)));
        holder.nama_txt.setText(String.valueOf(nama_sewa.get(position)));
        holder.judul_txt.setText(String.valueOf(judul_filem.get(position)));


        holder.mainLayout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent (context, UpdateActivity.class);
                intent.putExtra ("id",String.valueOf(id_sewa.get(position)));
                intent.putExtra ("nama",String.valueOf(nama_sewa.get(position)));
                intent.putExtra ("judul",String.valueOf(judul_filem.get(position)));

                activity.startActivityForResult (intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_sewa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, nama_txt, judul_txt;
        View mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            nama_txt = itemView.findViewById(R.id.nama_txt);
            judul_txt = itemView.findViewById(R.id.judul_txt);
            mainLayout = itemView.findViewById (R.id.mainLayout);

        }
    }
}
