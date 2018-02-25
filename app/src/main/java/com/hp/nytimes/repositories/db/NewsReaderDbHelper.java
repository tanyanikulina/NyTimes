package com.hp.nytimes.repositories.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hp on 24.02.2018.
 */

public class NewsReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NewsReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsReaderContract.NewsEntry.TABLE_NAME + " (" +
                    NewsReaderContract.NewsEntry._ID + " INTEGER PRIMARY KEY," +
                    NewsReaderContract.NewsEntry.COLUMN_SECTION + " TEXT, " +
                    NewsReaderContract.NewsEntry.COLUMN_URL + " TEXT, " +
                    NewsReaderContract.NewsEntry.COLUMN_TITLE + " TEXT, " +
                    NewsReaderContract.NewsEntry.COLUMN_DATE + " TEXT, " +
                    NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsReaderContract.NewsEntry.TABLE_NAME;

    public NewsReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public NewsReaderDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

}
