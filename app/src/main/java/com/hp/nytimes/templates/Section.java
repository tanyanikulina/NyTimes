package com.hp.nytimes.templates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hp on 13.02.2018.
 */

public class Section {

    public Section(){}

    private static final String STATUS = "status";
    private static final String COPYRIGHT = "copyright";
    private static final String NUM_RESULTS = "num_results";
    private static final String RESULTS = "results";

    @SerializedName(STATUS) @Expose private String status;
    @SerializedName(COPYRIGHT) @Expose private String copyright;
    @SerializedName(NUM_RESULTS) @Expose private String num_results;
    @SerializedName(RESULTS) @Expose private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public class Result{

        public Result(){}

        private static final String URL = "url";
        private static final String COUNT_TYPE = "count_type";
        private static final String COLUMN = "column";
        private static final String SECTION = "section";
        private static final String BYLINE = "byline";
        private static final String TITLE = "title";
        private static final String ABSTRACT = "abstract";
        private static final String PUBLISHED_DATE = "published_date";
        private static final String SOURCE = "source";
        private static final String DES_FACET = "des_facet";
        private static final String ORG_FACET = "org_facet";
        private static final String PER_FACET = "per_facet";
        private static final String GEO_FACET = "geo_facet";
        private static final String MEDIA = "media";

        @SerializedName(URL) @Expose private String url;
        @SerializedName(COUNT_TYPE) @Expose private String count_type;
        @SerializedName(COLUMN) @Expose private String column;
        @SerializedName(SECTION) @Expose private String section;
        @SerializedName(BYLINE) @Expose private String byline;
        @SerializedName(TITLE) @Expose private String title;
        @SerializedName(ABSTRACT) @Expose private String abstract_;
        @SerializedName(PUBLISHED_DATE) @Expose private String published_date;
        @SerializedName(SOURCE) @Expose private String source;
//        @SerializedName(DES_FACET) @Expose private List<String> des_facet;
//        @SerializedName(ORG_FACET) @Expose private List<String> org_facet;
//        @SerializedName(PER_FACET) @Expose private String per_facet;
//        @SerializedName(GEO_FACET) @Expose private List<String> geo_facet;
//        @SerializedName(MEDIA) @Expose private List<Media> mediaList;

        private boolean isFavorite = false;

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }

        public String getTitle() {
            return title;
        }

        public String getAbstract_() {
            return abstract_;
        }

        public String getPublished_date() {
            return published_date;
        }

        public String getUrl() {
            return url;
        }

        public String getSource() {
            return source;
        }

//        public List<Media> getMediaList() {
//            return mediaList;
//        }

        public class Media{

            private static final String TYPE = "type";
            private static final String SUBTYPE = "subtype";
            private static final String CAPTION = "caption";
            private static final String COPYRIGHT = "copyright";
//            private static final String APPROVED_FOR_SYNDICATION = "approved_for_syndication";
            private static final String MEDIA_METADATA = "media-metadata";

            @SerializedName(TYPE) @Expose private String type;
            @SerializedName(SUBTYPE) @Expose private String subtype;
            @SerializedName(CAPTION) @Expose private String caption;
            @SerializedName(COPYRIGHT) @Expose private String copyright;
            @SerializedName(MEDIA_METADATA) @Expose private List<MediaMetadata> metadataList;

            public List<MediaMetadata> getMetadataList() {
                return metadataList;
            }

            public class MediaMetadata{

                private static final String URL = "url";
                private static final String FORMAT = "format";
                private static final String HEIGHT = "height";
                private static final String WIDTH = "width";

                @SerializedName(URL) @Expose private String url;
                @SerializedName(FORMAT) @Expose private String format;
                @SerializedName(HEIGHT) @Expose private String height;
                @SerializedName(WIDTH) @Expose private String width;

                public String getUrl() {
                    return url;
                }
            }


        }


    }

}
