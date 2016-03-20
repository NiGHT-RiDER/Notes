package com.nightrider.notes;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Stefan on 20/03/2016.
 */
public class NotesProvider extends ContentProvider {

    private static final String AUTHORITHY = "com.nightrider.notes.notesprovider";
    private static final String BASE_PATH = "notes";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITHY + "/" + BASE_PATH);

    private static final int NOTES = 1;
    private static final int NOTE_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITHY , BASE_PATH , NOTES);
        uriMatcher.addURI(AUTHORITHY , BASE_PATH + "/#" , NOTE_ID); // the hashtag searches for number
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        db = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(DBOpenHelper.TABLE_NOTES , DBOpenHelper.ALL_COLUMNS , selection , null , null , null , DBOpenHelper.NOTE_DATE + " DESC ");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DBOpenHelper.TABLE_NOTES , null , values);
        return  Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(DBOpenHelper.TABLE_NOTES , selection , selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(DBOpenHelper.TABLE_NOTES ,values , selection , selectionArgs);
    }
}
