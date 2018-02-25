package com.hp.nytimes.interactors;

import com.hp.nytimes.templates.Section;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


import static com.hp.nytimes.templates.Urls.api_key;
import static com.hp.nytimes.templates.Urls.api_key_v;

/**
 * Created by Hp on 13.02.2018.
 */

public class NetInteractor {

    retrofit2.Callback<Section> callback;
    String section;

    public NetInteractor(Callback<Section> callback, String section) {
        this.callback = callback;
        this.section = section;
    }

    public void execute(){

        Api api = ApiManager.createService(Api.class);
        Map<String, String> data = new HashMap<>();
        data.put(api_key, api_key_v);
        Call<Section> sectionCall = api.getSection(section, data);
        sectionCall.enqueue(callback);

    }



}
