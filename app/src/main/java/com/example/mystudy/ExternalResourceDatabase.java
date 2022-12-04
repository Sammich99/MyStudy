package com.example.mystudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ExternalResourceDatabase extends SQLiteOpenHelper {
    public ExternalResourceDatabase(Context context) {super(context, "Notedata.db", null , 1);}

    @Override
    public void onCreate(SQLiteDatabase noteDB) {
        noteDB.execSQL("create Table Notedetails(inputNoteTitle TEXT primary key, inputNoteSubTitle TEXT, inputNoteText TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase noteDB, int i, int i1) {
        noteDB.execSQL(("drop Table if exists Notedetails"));
    }

    public Boolean addNote(String inputNoteTitle, String inputNoteSubTitle, String inputNoteText){
        SQLiteDatabase noteDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inputNoteTitle", inputNoteTitle);
        contentValues.put("inputNoteSubTitle", inputNoteSubTitle);
        contentValues.put("inputNoteText", inputNoteText);
        long result = noteDB.insert("Notedetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase noteDB = this.getReadableDatabase();
        Cursor cursor = noteDB.rawQuery("Select * from Notedetails", null);
        return cursor;
    }

    public void updateNote(String noteupdated,  String updateNoteTitle, String updateNoteSubTitle, String updateNoteText){
        SQLiteDatabase noteDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("updateNoteTitle", updateNoteTitle);
        contentValues.put("updateNoteSubTitle", updateNoteSubTitle);
        contentValues.put("updateNoteText", updateNoteText);
        noteDB.update("Notedetails", contentValues, "updateNoteTitle=?", new String[]{noteupdated});


    }


}
