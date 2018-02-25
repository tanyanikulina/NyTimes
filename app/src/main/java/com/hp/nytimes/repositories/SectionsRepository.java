package com.hp.nytimes.repositories;

import com.hp.nytimes.templates.Section;

/**
 * Created by Hp on 15.02.2018.
 */

public class SectionsRepository {

    //todo пока что это синглтон

    private static SectionsRepository instanse;
    private Section emailedSection;
    private Section sharedSection;
    private Section viewedSection;

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
    }

    public Section getSharedSection() {
        return sharedSection;
    }

    public void setSharedSection(Section sharedSection) {
        this.sharedSection = sharedSection;
    }

    public Section getViewedSection() {
        return viewedSection;
    }

    public void setViewedSection(Section viewedSection) {
        this.viewedSection = viewedSection;
    }
}
