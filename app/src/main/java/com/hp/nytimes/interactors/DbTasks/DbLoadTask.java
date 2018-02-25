package com.hp.nytimes.interactors.DbTasks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.Section;

import java.util.List;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbLoadTask extends AsyncTask<Section.Result,Void,List<Section.Result>> {

    SQLiteDatabase db;
    Section.Result r;
    OnPostLoad onPostLoad;

    public DbLoadTask(SQLiteDatabase db, Section.Result r, OnPostLoad onPostLoad) {
        this.db = db;
        this.r = r;
        this.onPostLoad = onPostLoad;
    }

    @Override
    protected List<Section.Result> doInBackground(Section.Result... results) {
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                NewsReaderContract.NewsEntry.COLUMN_TITLE,
                NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS,
                NewsReaderContract.NewsEntry.COLUMN_DATE,
                NewsReaderContract.NewsEntry.COLUMN_URL,
//                NewsReaderContract.NewsEntry.COLUMN_SECTION,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                NewsReaderContract.NewsEntry.COLUMN_DATE;

        Cursor c = db.query(
                NewsReaderContract.NewsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,                                    
                null,
                sortOrder
        );

        return null;
    }

    @Override
    protected void onPostExecute(List<Section.Result> results) {
        onPostLoad.postLoad();
        super.onPostExecute(results);
    }

    public interface OnPostLoad{
        void postLoad();
    }

}
