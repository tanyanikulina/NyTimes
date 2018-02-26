package com.hp.nytimes.interactors.DbTasks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.NewsEntity;
import com.hp.nytimes.templates.Section;
import com.hp.nytimes.templates.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbLoadTask extends AsyncTask<String,Void,List<NewsEntity>> {

    SQLiteDatabase db;
    NewsEntity ne;
    OnPostLoad onPostLoad;

    public DbLoadTask(SQLiteDatabase db, OnPostLoad onPostLoad) {
        this.db = db;
        this.onPostLoad = onPostLoad;
    }

    @Override
    protected List<NewsEntity> doInBackground(String... results) {

        List<NewsEntity> nList = new ArrayList<>();
        String[] projection = {
                NewsReaderContract.NewsEntry.COLUMN_TITLE,
                NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS,
                NewsReaderContract.NewsEntry.COLUMN_DATE,
                NewsReaderContract.NewsEntry.COLUMN_URL,
//                NewsReaderContract.NewsEntry.COLUMN_SECTION,
        };

        String sortOrder = NewsReaderContract.NewsEntry.COLUMN_DATE;
        Cursor c = db.query(
                NewsReaderContract.NewsEntry.TABLE_NAME, projection,
                null, null, null, null, sortOrder);
        c.moveToFirst();
        ne= new NewsEntity();
        while (c.moveToNext()){
            ne.setAbstracts(c.getString(c.getColumnIndexOrThrow(NewsReaderContract.NewsEntry.COLUMN_ABSTRACTS)));
            ne.setDate(c.getString(c.getColumnIndexOrThrow(NewsReaderContract.NewsEntry.COLUMN_DATE)));
            ne.setTitle(c.getString(c.getColumnIndexOrThrow(NewsReaderContract.NewsEntry.COLUMN_TITLE)));
            ne.setUrl(c.getString(c.getColumnIndexOrThrow(NewsReaderContract.NewsEntry.COLUMN_URL)));
            ne.setFavorite(true);
            nList.add(ne);
        }
        return nList;
    }

    @Override
    protected void onPostExecute(List<NewsEntity> nList) {
        onPostLoad.postLoad(nList);
        super.onPostExecute(nList);
    }

    public interface OnPostLoad{
        void postLoad(List<NewsEntity> nList);
    }

}
