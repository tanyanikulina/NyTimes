package com.hp.nytimes.interactors;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hp.nytimes.templates.Urls.url_site;

/**
 * Created by Hp on 13.02.2018.
 */

public class ApiManager {

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url_site).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit retrofit() { return retrofit; }

    public static <T> T createService(Class<T> serviceClass){
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

}
