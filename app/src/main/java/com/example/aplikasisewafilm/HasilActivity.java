package com.example.aplikasisewafilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class HasilActivity extends AppCompatActivity {

    //deklarasi variabel
    TextView HNama, HJudul, HPeminjaman, HGenre, HRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        //menghubungkan tampilan ke variabel
        HNama = (TextView) findViewById(R.id.hasil_nama);
        HJudul = (TextView) findViewById(R.id.hasil_judul);
        HPeminjaman = (TextView) findViewById(R.id.hasil_pmjm);
        HGenre = (TextView) findViewById(R.id.hasil_genre);
        HRating = (TextView) findViewById(R.id.hasil_rating);

        //intent untuk menerima data yang dikirimkan dari dashboard ke hasil
        Intent i = getIntent();
        HNama.setText(i.getExtras().getString("KEY_nama"));
        HJudul.setText(i.getExtras().getString("KEY_judul"));
        HPeminjaman.setText(i.getExtras().getString("KEY_peminjaman"));
        HGenre.setText(i.getExtras().getString("KEY_gendre"));
        HRating.setText(i.getExtras().getString("KEY_rate"));
    }

    //activity lifecycle
    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(this, "Proses Berhasil", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
//    }

   @Override
    protected void onPause(){
        super.onPause();

       Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();

        Toast.makeText(this, "Selamat Tinggal", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");

        Toast.makeText(this, "Good Byee", Toast.LENGTH_SHORT).show();
    }
}