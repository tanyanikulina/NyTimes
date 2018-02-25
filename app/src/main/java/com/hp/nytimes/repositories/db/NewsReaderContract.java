package com.hp.nytimes.repositories.db;

import android.provider.BaseColumns;

/**
 * Created by Hp on 24.02.2018.
 */

public class NewsReaderContract {

    public NewsReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_SECTION = "section";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_ABSTRACTS = "abstracts";

    }

//    public static abstract class MediaEntry implements BaseColumns {
//        public static final String TABLE_NAME = "media";
//        public static final String COLUMN_SECTION = "type";
//        public static final String COLUMN_SECTION = "url";
//        public static final String COLUMN_SECTION = "title";
//        public static final String COLUMN_SECTION = "date";
//        public static final String COLUMN_SECTION = "abstracts";
//
//    }




}
