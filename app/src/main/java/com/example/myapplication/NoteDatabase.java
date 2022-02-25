package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private static final int database_version = 4;
    private static final String database_name = "NoteDBb";
    private static final String database_table = "NoteTableb";

    private static final String key_id = "id";
    private static final String key_title = "title";
    private static final String key_content = "content";


    NoteDatabase(Context context) {
        super(context, database_name, null, database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +
                database_table + " ("+
                key_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                key_title + " TEXT, "+
                key_content + " TEXT"+")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion) {
            return;
        }
        db.execSQL("Drop table if exists "+ database_name);
        onCreate(db);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(key_title,note.getTitle());
        c.put(key_content,note.getContent());
        //c.put(key_id,note.getId());

        long id = db.insert(database_table, null, c);
        Log.d("Inserted", "id-->"+id);
        return id;


    }
    public Note getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(database_table,new String[] {key_id, key_title, key_content}, key_id+"=?",
                new String[] {String.valueOf(id)},null,null,null);
        if(cursor != null)
            cursor.moveToFirst();

        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2));

    }
    public List<Note> getNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();

        String query = "SELECT * FROM "+database_table ;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));

                allNotes.add(note);
            }while(cursor.moveToNext());
        }
        return allNotes;
    }
}
