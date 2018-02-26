package com.hp.nytimes.interactors.DbTasks;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.NewsEntity;
import com.hp.nytimes.templates.Section;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbRemoveTask extends AsyncTask<Section.Result,Void,String> {

    SQLiteDatabase db;
    NewsEntity ne;
    OnPostRemove onPostRemove;

    public DbRemoveTask(SQLiteDatabase db, NewsEntity ne, OnPostRemove onPostRemove) {
        this.db = db;
        this.ne = ne;
        this.onPostRemove = onPostRemove;
    }

    @Override
    protected String doInBackground(Section.Result... results) {
        //delete from tablename where id='1' and name ='jack'
        //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
        String whereClause = NewsReaderContract.NewsEntry.COLUMN_DATE + "=? and "+
                NewsReaderContract.NewsEntry.COLUMN_TITLE + "=?";
        String[] whereArgs = new String[] { String.valueOf(ne.getDate()),
                String.valueOf(ne.getTitle()) };
        int delCount = db.delete(NewsReaderContract.NewsEntry.TABLE_NAME,
                whereClause , whereArgs);

        return "Deleted "+delCount+" news from db";
    }

    @Override
    protected void onPostExecute(String s) {
        onPostRemove.postRemove(s);
        super.onPostExecute(s);
    }

    public interface OnPostRemove{
        void postRemove(String s);
    }


}
