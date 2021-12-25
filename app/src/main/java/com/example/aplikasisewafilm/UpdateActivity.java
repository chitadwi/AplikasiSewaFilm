package com.example.aplikasisewafilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText nama, judul, lamapeminjaman, genre, rating;
    Button btn_simpan, btn_hapus;
    String id, nama_sewa, judul_sewa, lamapeminjaman_sewa, genre_sewa, rating_sewa;
    String falidnama, falidjudul, falidgenre;
    DBHelper mydh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mydh = new DBHelper(UpdateActivity.this);
        nama = findViewById(R.id.txt_nama_sewa);
        judul = findViewById(R.id.txt_judul);
        lamapeminjaman = findViewById(R.id.txt_lamapeminjaman);
        genre = findViewById(R.id.txt_genre);
        rating = findViewById(R.id.txt_rating);

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_hapus = findViewById(R.id.btn_hapus);
        //ImageButton hapus = findViewById(R.id.hapus);


        getIntendata();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama_sewa = nama.getText().toString();
                judul_sewa = judul.getText().toString();
                lamapeminjaman_sewa = lamapeminjaman.getText().toString();
                genre_sewa= genre.getText().toString();
                rating_sewa = rating.getText().toString();
                ;

                if (nama_sewa.compareTo(falidnama)==0 && judul_sewa.compareTo(falidjudul)==0 && genre_sewa.compareTo(falidgenre)==0 )   {
                    Toast.makeText(UpdateActivity.this, "Tidak ada perubahan data", Toast.LENGTH_LONG).show();
                }else{
                    mydh.updateData(id, nama_sewa, judul_sewa, lamapeminjaman_sewa, genre_sewa, rating_sewa);
                }



            }
        });
        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasi();
            }
        });
    }

    void getIntendata() {
        getIntent().hasExtra("nama");
        //get data from intent
        id = getIntent().getStringExtra("id");
        //namaa = getIntent().getStringExtra("nama");
        getData();
    }



    void getData(){
        Cursor cursor = mydh.loadData(id);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                nama.setText(cursor.getString(1));
                judul.setText(cursor.getString(2));
                lamapeminjaman.setText(cursor.getString(3));
                genre.setText(cursor.getString(4));
                rating.setText(cursor.getString(5));


                falidnama = (cursor.getString(1));
                falidjudul = (cursor.getString(2));
                falidgenre = (cursor.getString(4));
            }
        }
    }

    void konfirmasi(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data " + falidnama +" ?");
        builder.setMessage("Data akan hilang setelah dihapus apakah yakin ingin menghapus data "+falidnama +" ?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper mydh = new DBHelper(UpdateActivity.this);
                mydh.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
