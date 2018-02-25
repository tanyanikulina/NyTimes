package com.hp.nytimes.interactors.DbTasks;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.Section;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbPutTask extends AsyncTask<Section.Result,Void,String> {

    SQLiteDatabase db;
    Section.Result r;
    OnPostPut onPostPut;

    public DbPutTask(SQLiteDatabase db, Section.Result r, OnPostPut onPostPut) {
        this.db = db;
        this.r = r;
        this.onPostPut = onPostPut;
    }

    @Override
    protected String doInBackground(Section.Result... params) {

        ContentValues values = new ContentValues();
        values.put(NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS,r.getAbstract_());
        values.put(NewsReaderContract.NewsEntry.COLUMN_DATE,r.getPublished_date());
        values.put(NewsReaderContract.NewsEntry.COLUMN_URL,r.getUrl());
        values.put(NewsReaderContract.NewsEntry.COLUMN_TITLE,r.getTitle());
        long newRowId;
        newRowId = db.insert(NewsReaderContract.NewsEntry.TABLE_NAME,null, values);


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        onPostPut.postPut();
        super.onPostExecute(s);
    }

    public interface OnPostPut {
        void postPut();
    }

}
