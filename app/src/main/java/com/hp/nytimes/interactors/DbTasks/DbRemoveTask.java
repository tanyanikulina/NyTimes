package com.hp.nytimes.interactors.DbTasks;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.Section;

/**
 * Created by Hp on 25.02.2018.
 */

public class DbRemoveTask extends AsyncTask<Section.Result,Void,String> {

    SQLiteDatabase db;
    Section.Result r;
    OnPostRemove onPostRemove;

    public DbRemoveTask(SQLiteDatabase db, Section.Result r, OnPostRemove onPostRemove) {
        this.db = db;
        this.r = r;
        this.onPostRemove = onPostRemove;
    }

    @Override
    protected String doInBackground(Section.Result... results) {
        //delete from tablename where id='1' and name ='jack'
        //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
        String whereClause = NewsReaderContract.NewsEntry.COLUMN_DATE + "=? and"+
                NewsReaderContract.NewsEntry.COLUMN_TITLE + "=?";
        String[] whereArgs = new String[] { String.valueOf(r.getPublished_date()),
                String.valueOf(r.getTitle()) };
        int delCount = db.delete(NewsReaderContract.NewsEntry.TABLE_NAME,
                whereClause , whereArgs);

        return "Deleted "+delCount+" news from db";
    }

    @Override
    protected void onPostExecute(String s) {
        onPostRemove.postRemove();
        super.onPostExecute(s);
    }

    public interface OnPostRemove{
        void postRemove();
    }


}
