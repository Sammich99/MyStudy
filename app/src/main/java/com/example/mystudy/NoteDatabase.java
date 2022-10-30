package com.example.mystudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {
    public NoteDatabase(Context context) {super(context, "Notedata.db", null , 1);}

    @Override
    public void onCreate(SQLiteDatabase noteDB) {
        noteDB.execSQL("create Table Notedetails(inputNoteTitle TEXT primary key, inputNoteSubTitle TEXT, inputNoteText TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase noteDB, int i, int i1) {
        noteDB.execSQL(("drop Table if exists Notedetails"));
    }

    public Boolean addNote(String inputNoteTitle, String inputNoteSubTitle, String inputNoteText){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inputNoteTitle", inputNoteTitle);
        contentValues.put("inputNoteSubTitle", inputNoteSubTitle);
        contentValues.put("inputNoteText", inputNoteText);
        long result = DB.insert("Notedetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notedetails", null);
        return cursor;
    }
}
