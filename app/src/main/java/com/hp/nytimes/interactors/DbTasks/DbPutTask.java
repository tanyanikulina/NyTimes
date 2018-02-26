package com.hp.nytimes.interactors.DbTasks;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.NewsEntity;
import com.hp.nytimes.templates.Section;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbPutTask extends AsyncTask<Section.Result,Void,String> {

    SQLiteDatabase db;
    NewsEntity ne;
    OnPostPut onPostPut;

    public DbPutTask(SQLiteDatabase db, NewsEntity ne, OnPostPut onPostPut) {
        this.db = db;
        this.ne = ne;
        this.onPostPut = onPostPut;
    }

    @Override
    protected String doInBackground(Section.Result... params) {

        ContentValues values = new ContentValues();
        values.put(NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS,ne.getAbstracts());
        values.put(NewsReaderContract.NewsEntry.COLUMN_DATE,ne.getDate());
        values.put(NewsReaderContract.NewsEntry.COLUMN_URL,ne.getUrl());
        values.put(NewsReaderContract.NewsEntry.COLUMN_TITLE,ne.getTitle());
        long newRowId;
        newRowId = db.insert(NewsReaderContract.NewsEntry.TABLE_NAME,null, values);


        return "Putted "+ newRowId +" news to db";
    }

    @Override
    protected void onPostExecute(String s) {
        onPostPut.postPut(s);
        super.onPostExecute(s);
    }

    public interface OnPostPut {
        void postPut(String s);
    }

}
