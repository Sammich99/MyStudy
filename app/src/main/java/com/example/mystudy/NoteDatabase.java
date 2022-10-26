package com.example.mystudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "NoteData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "note_library";
    private static final String COLUMN_ID = "note_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_SUBTITLE = "note_subtitle";
    private static final String COLUMN_TYPENOTE = "type_note";

    public NoteDatabase(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase  db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_SUBTITLE+ " TEXT, " +
                        COLUMN_TYPENOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(String title, String subtitle, String typenote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();

        contentvalues.put(COLUMN_TITLE, title);
        contentvalues.put(COLUMN_SUBTITLE, subtitle);
        contentvalues.put(COLUMN_TYPENOTE, typenote);
        long result = db.insert(TABLE_NAME, null, contentvalues);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData() {
       String query = "SELECT * FROM " + TABLE_NAME;
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = null;
       if(db != null){
           db.rawQuery(query, null);
       }
       return cursor;
    }

}
