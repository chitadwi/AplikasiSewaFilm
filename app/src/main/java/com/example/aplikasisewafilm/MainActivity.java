package com.example.aplikasisewafilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    private Button btnMulai, btnlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMulai = findViewById(R.id.btn_mulai);
        btnlist = findViewById(R.id.btn_list);
        btnMulai.setOnClickListener(new View.OnClickListener() {

            //untuk memindahkan activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(i);
            }
        });

        btnlist.setOnClickListener(new View.OnClickListener() {

            //untuk memindahkan activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(i);
            }
        });
    }
}