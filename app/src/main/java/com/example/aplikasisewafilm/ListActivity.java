package com.example.aplikasisewafilm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasisewafilm.DBHelper;

import java.util.ArrayList;

//utk modul 4
public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DBHelper mydh;
    ArrayList <String> id_sewa, nama_sewa, judul_filem;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);

        mydh = new DBHelper(ListActivity.this);
        id_sewa = new ArrayList<>();
        nama_sewa = new ArrayList<>();
        judul_filem = new ArrayList<>();


        displayData();

        customAdapter = new CustomAdapter(ListActivity.this,this, id_sewa, nama_sewa, judul_filem);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //nampilin data
    void displayData(){
        Cursor cursor = mydh.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                id_sewa.add(cursor.getString(0));
                nama_sewa.add(cursor.getString(1));
                judul_filem.add(cursor.getString(2));

            }
        }
    }


}