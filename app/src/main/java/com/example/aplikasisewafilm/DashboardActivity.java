package com.example.aplikasisewafilm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    //deklarasi variabel

    EditText edNama, edJudul;
    RadioButton rbPmjm, rbPmjm2, rbPmjm3;
    CheckBox cbHoror, cbAnimasi, cbRomantis;
    SeekBar sbSeekbar;
    String nama, judul, peminjaman, genre, rate;
    public TextView Judul, Rate;
    private Button btnTambah;
    Dialog mDialog;
    Button btn_input;
    DBHelper dbHelper;
    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dbHelper = new DBHelper(this);
//menghubungkan tampilan ke variabe
            edNama = (EditText) findViewById(R.id.ed_nama);
            edJudul = (EditText) findViewById(R.id.ed_judul);

            rbPmjm = (RadioButton) findViewById(R.id.rb_pmjm);
            rbPmjm2 = (RadioButton) findViewById(R.id.rb_pmjm2);
            rbPmjm3 = (RadioButton) findViewById(R.id.rb_pmjm3);

            cbHoror = (CheckBox) findViewById(R.id.cb_horor);
            cbAnimasi = (CheckBox) findViewById(R.id.cb_animasi);
            cbRomantis = (CheckBox) findViewById(R.id.cb_romantis);


        Judul = findViewById(R.id.txt_judul);
        final TextView textRate = findViewById(R.id.txt_ptrate);
        SeekBar seekBar = findViewById(R.id.seekbar);


        //seekbar
        textRate.setText(seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textRate.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                rate = rate + (progressValue - progress);
                progress = progressValue;
                textRate.setText(rate + "/100");
                Rate = textRate;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });
        btn_input = findViewById(R.id.btn_input);
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edNama.getText().toString().length()==0) {
                    edNama.setError("Nama diperlukan!");
                    Toast.makeText(DashboardActivity.this, "Masih Kosong", Toast.LENGTH_LONG).show();
                }else if(edJudul.getText().toString().length()<=11){
                    edJudul.setError("diperlukan!");
                    Toast.makeText(DashboardActivity.this, "Masih Kosong", Toast.LENGTH_LONG).show();
                } else if(rbPmjm.getText().toString().length()==0 || rbPmjm2.getText().toString().length()==0 || rbPmjm3.getText().toString().length()==0){
                    rbPmjm.setError("diperlukan!");
                    Toast.makeText(DashboardActivity.this, "Masih Kosong", Toast.LENGTH_LONG).show();

                }else {
                    inputData();
                }



            }
        });
    }

    //proses setelah menekan tombol input
    public void inputData() {
        nama = edNama.getText().toString();
        judul = edJudul.getText().toString();
        peminjaman = "";
        genre = "";
        rate = Rate.getText().toString();


        //radio button
        if (rbPmjm.isChecked()) {
            peminjaman += "1 Minggu";
        }
        if (rbPmjm2.isChecked()) {
            peminjaman += "2 Minggu";
        }
        if (rbPmjm3.isChecked()) {
            peminjaman += "1 Bulan";
        }

        //checkbox
        if (cbHoror.isChecked()) {
            genre += "Horor,";
        }
        if (cbAnimasi.isChecked()) {
            genre += "Animasi,";
        }
        if (cbRomantis.isChecked()) {
            genre += "Romantis";
        }

        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Berhasil Menambahkan Data");
        String finalPeminjaman = peminjaman;
        String finalGenre = genre;
        builder.setMessage("Nama\t\t\t\t\t\t: " + String.valueOf(nama) + "\n" +
                "Judul\t\t\t\t\t\t: " + String.valueOf(judul) + "\n" +
                "Peminjaman\t: " + String.valueOf(peminjaman) + "\n" +
                "Genre\t\t\t\t\t\t: " + String.valueOf(genre) + "\n" +
                "Rating\t\t\t\t\t: " + String.valueOf(rate) + "\n").setPositiveButton("Selesai",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //insert data ke database
                        DBHelper mydh = new DBHelper(DashboardActivity.this);
                        mydh.tambahpeminjaman(nama.trim(), judul.trim(),
                                peminjaman.trim(), genre.trim(), rate.trim());

                        //intent mod 2
                        Intent intent = new Intent(DashboardActivity.this, HasilActivity.class);
                        intent.putExtra("KEY_nama", nama);
                        intent.putExtra("KEY_judul", judul);
                        intent.putExtra("KEY_peminjaman", peminjaman);
                        intent.putExtra("KEY_gendre", genre);
                        intent.putExtra("KEY_rate", rate);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

}