package com.nightrider.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stefan on 20/03/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    /*constantes pour le nom de la db et version */
    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;

    /* constantes pour identifier les tables et les champs */
    public static final String TABLE_NOTES = "notes";
    public static final String NOTE_ID = "id";
    public static final String NOTE_TEXT = "note_txt";
    public static final String NOTE_DATE= "note_date";
    public static final String[] ALL_COLUMNS = {NOTE_ID , NOTE_TEXT , NOTE_DATE };

    /* sql pour creer la table */
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NOTES + " ( " +
            NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            NOTE_TEXT + " TEXT ," +
            NOTE_DATE + " TEXT default CURRENT_TIMESTAMP "+
            " ) ";

    public DBOpenHelper(Context context) {
        super(context , DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
