package com.example.aplikasisewafilm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_film";
    private static final int DATABASE_VERSION = 1;

    private Context context;
    private static final String TABLE_NAME = "tb_film";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_JUDUL = "judul";
    private static final String COLUMN_LAMAPEMINJAMAN = "lamapeminjaman";
    private static final String COLUMN_GENDRE = "gendre";
    private static final String COLUMN_RATING = "rating";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

//create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAMA + " TEXT, " +
                        COLUMN_JUDUL + " TEXT," +
                        COLUMN_LAMAPEMINJAMAN + " TEXT, " +
                        COLUMN_GENDRE+ " TEXT, " +
                        COLUMN_RATING + " INTEGER);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_film");
    }


    //fungsi untuk lihat semua data
    Cursor readData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //fungsi untuk tambah data
    void tambahpeminjaman(String nama, String judul, String lamapeminjaman, String gendre, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_LAMAPEMINJAMAN, lamapeminjaman);
        cv.put(COLUMN_GENDRE, gendre);
        cv.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "failed "+ rating + gendre, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
        }
    }

    //fungsi load data sesuai ketentuan
    Cursor loadData(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =?";

        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});
        return cursor;
    }

    //fungsi update data
    void updateData(String rowid, String nama, String judul, String lamapeminjaman, String gendre, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_LAMAPEMINJAMAN, lamapeminjaman);
        cv.put(COLUMN_GENDRE, gendre);
        cv.put(COLUMN_RATING, rating);

        long result = db.update(TABLE_NAME,cv,"id=?", new String[]{rowid});
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update Success " + nama, Toast.LENGTH_SHORT).show();
        }

    }

    //fungsi untuk delete data yang dipilih
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete Success ", Toast.LENGTH_SHORT).show();
        }
    }


}
