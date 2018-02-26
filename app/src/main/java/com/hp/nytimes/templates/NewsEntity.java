package com.hp.nytimes.templates;

/**
 * Created by Hp on 26.02.2018.
 */

public class NewsEntity {

    private boolean isFavorite = false;
    private String title = "";
    private String date = "";
    private String abstracts = "";
    private String url = "";
    private String imgUrl = "";
    private String section_url = "";

    public String getSection_url() {
        return section_url;
    }

    public void setSection_url(String section_url) {
        this.section_url = section_url;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
