package com.example.mystudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Coursedata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Coursedetails(coursetitle TEXT primary key, coursecode TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL(("drop Table if exists Coursedetails"));
    }

    public Boolean insertcoursedata(String coursetitle, String coursecode) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("coursetitle", coursetitle);
        contentValues.put("coursecode", coursecode);
        long result = DB.insert("Coursedetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }




    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Coursedetails", null);
        return cursor;
    }


    }
