package com.hp.nytimes.interactors;

import com.hp.nytimes.templates.Section;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import static com.hp.nytimes.templates.Urls.url_1;
import static com.hp.nytimes.templates.Urls.url_all_sections;
import static com.hp.nytimes.templates.Urls.url_mostemailed;
import static com.hp.nytimes.templates.Urls.url_mostshared;
import static com.hp.nytimes.templates.Urls.url_mostviewed;
import static com.hp.nytimes.templates.Urls.url_site;

/**
 * Created by Hp on 11.02.2018.
 */

public interface Api {

    @GET(url_site + "{section}" + url_all_sections + url_1)
    Call<Section> getSection(@Path("section") String sect, @QueryMap Map<String, String> key);

    @GET(url_site + url_mostemailed + url_all_sections + url_1)
    Call<Section> getMostEmailed(@QueryMap Map<String, String> key);

    @GET(url_site + url_mostshared + url_all_sections + url_1)
    Call<Section> getMostShared(@QueryMap Map<String, String> key);

    @GET(url_site + url_mostviewed + url_all_sections + url_1)
    Call<Section> getMostViewed(@QueryMap Map<String, String> key);


}
