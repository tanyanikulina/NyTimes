package com.hp.nytimes.repositories;

import com.hp.nytimes.repositories.db.NewsReaderContract;
import com.hp.nytimes.templates.NewsEntity;
import com.hp.nytimes.templates.Section;
import com.hp.nytimes.templates.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 15.02.2018.
 */

public class SectionsRepository {

    //todo пока что это синглтон

    private static SectionsRepository instanse;
    private Section emailedSection;
    private Section sharedSection;
    private Section viewedSection;

    private List<NewsEntity> emailedNews;
    private List<NewsEntity> sharedNews;
    private List<NewsEntity> viewedNews;

    private SectionsRepository(){

    }

    public static SectionsRepository getInstanse(){
        if (instanse==null)
            instanse = new SectionsRepository();
        return instanse;
    }

    public Section getEmailedSection() {
        return emailedSection;
    }

    public void setEmailedSection(Section emailedSection) {
        this.emailedSection = emailedSection;
        NewsEntity ne;
        Section.Result r;
        emailedNews = new ArrayList<>();
        for (int i = 0; i < emailedSection.getResults().size() ; i++) {
            ne = new NewsEntity();
            r =emailedSection.getResults().get(i);
            ne.setAbstracts(r.getAbstract_());
            ne.setDate(r.getPublished_date());
            ne.setTitle(r.getTitle());
            ne.setUrl(r.getUrl());
            ne.setSection_url(Urls.url_mostemailed);
            ne.setFavorite(false);
            emailedNews.add(ne);
        }
    }

    public Section getSharedSection() {
        return sharedSection;
    }

    public void setSharedSection(Section sharedSection) {
        this.sharedSection = sharedSection;
        NewsEntity ne;
        Section.Result r;
        sharedNews = new ArrayList<>();
        for (int i = 0; i < sharedSection.getResults().size() ; i++) {
            ne = new NewsEntity();
            r =sharedSection.getResults().get(i);
            ne.setAbstracts(r.getAbstract_());
            ne.setDate(r.getPublished_date());
            ne.setTitle(r.getTitle());
            ne.setUrl(r.getUrl());
            ne.setSection_url(Urls.url_mostshared);
            ne.setFavorite(false);
            sharedNews.add(ne);
        }
    }

    public Section getViewedSection() {
        return viewedSection;
    }

    public void setViewedSection(Section viewedSection) {
        this.viewedSection = viewedSection;
        NewsEntity ne;
        Section.Result r;
        viewedNews = new ArrayList<>();
        for (int i = 0; i < viewedSection.getResults().size() ; i++) {
            ne = new NewsEntity();
            r =viewedSection.getResults().get(i);
            ne.setAbstracts(r.getAbstract_());
            ne.setDate(r.getPublished_date());
            ne.setTitle(r.getTitle());
            ne.setUrl(r.getUrl());
            ne.setSection_url(Urls.url_mostviewed);
            ne.setFavorite(false);
            viewedNews.add(ne);
        }
    }

    public List<NewsEntity> getEmailedNews() {
        return emailedNews;
    }

    public void setEmailedNews(List<NewsEntity> emailedNews) {
        this.emailedNews = emailedNews;
    }

    public List<NewsEntity> getSharedNews() {
        return sharedNews;
    }

    public void setSharedNews(List<NewsEntity> sharedNews) {
        this.sharedNews = sharedNews;
    }

    public List<NewsEntity> getViewedNews() {
        return viewedNews;
    }

    public void setViewedNews(List<NewsEntity> viewedNews) {
        this.viewedNews = viewedNews;
    }
}
