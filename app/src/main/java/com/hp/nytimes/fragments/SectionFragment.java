package com.hp.nytimes.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hp.nytimes.NewsActivity;
import com.hp.nytimes.R;
import com.hp.nytimes.adapters.SectionRVAdapter;
import com.hp.nytimes.interactors.DbTasks.DbPutTask;
import com.hp.nytimes.interactors.DbTasks.DbRemoveTask;
import com.hp.nytimes.interactors.NetInteractor;
import com.hp.nytimes.repositories.SectionsRepository;
import com.hp.nytimes.repositories.db.NewsReaderDbHelper;
import com.hp.nytimes.templates.Section;
import com.hp.nytimes.templates.Urls;
import com.hp.nytimes.tools.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SectionFragment extends Fragment implements SectionRVAdapter.OnClickNewsListener {
    public static final String ARG_SECTION_URL = "section_url";

    private String sectionUrl;

    SwipeRefreshLayout l_swipe;
    RecyclerView rv;
    SectionsRepository repo = SectionsRepository.getInstanse();
    NetInteractor sInteractor;
    SectionRVAdapter sectionAdapter;

    SQLiteDatabase db;

    public SectionFragment() {
        // Required empty public constructor
    }

    public static SectionFragment newInstance(String sectionUrl) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_URL, sectionUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sectionUrl = getArguments().getString(ARG_SECTION_URL);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_section, container, false);
        l_swipe = (SwipeRefreshLayout) v.findViewById(R.id.l_swipe);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        l_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataFromInet();
            }
        });
        db = (new NewsReaderDbHelper(getContext())).getWritableDatabase();
//        sectionAdapter = new SectionRVAdapter();
//        rv.setAdapter(sectionAdapter);
        boolean isDataExist = false;
        switch (sectionUrl){
            case Urls.url_mostemailed:
                isDataExist=(repo.getEmailedSection()!=null);
                break;
            case Urls.url_mostshared:
                isDataExist=(repo.getSharedSection()!=null);
                break;
            case Urls.url_mostviewed:
                isDataExist=(repo.getViewedSection()!=null);
                break;
            case Urls.url_favorite:
                isDataExist=true;
                break;
        }
        if(isDataExist)
            loadDataFromRepository();
        else
            loadDataFromInet();
        return v;
    }

    private void loadDataFromInet() {
        sInteractor = new NetInteractor(new Callback<Section>() {
            @Override
            public void onResponse(Call<Section> call, Response<Section> response) {
                Section s = response.body();
                switch (sectionUrl){
                    case Urls.url_mostemailed:
                        repo.setEmailedSection(s);
                        break;
                    case Urls.url_mostshared:
                        repo.setSharedSection(s);
                        break;
                    case Urls.url_mostviewed:
                        repo.setViewedSection(s);
                        break;
                }
                loadDataFromRepository();

//                if (sectionAdapter==null){
//                    sectionAdapter = new SectionRVAdapter(response.body().getResults());
//                    sectionAdapter.setOnClickNewsListener((SectionRVAdapter.OnClickNewsListener) getContext());
//                    rv.setAdapter(sectionAdapter);
//                } else
//                    sectionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Section> call, Throwable t) {
                Toast.makeText(getContext(), "Oops! Some error has occurred =(", Toast.LENGTH_SHORT).show();
            }
        }, sectionUrl);
        sInteractor.execute();

    }


    private void loadDataFromRepository(){
        List<Section.Result> results = null;
        switch (sectionUrl){
            case Urls.url_mostemailed:
                results = repo.getEmailedSection().getResults();
                break;
            case Urls.url_mostshared:
                results = repo.getSharedSection().getResults();
                break;
            case Urls.url_mostviewed:
                results = repo.getViewedSection().getResults();
                break;
            case Urls.url_favorite:

                break;
        }
        sectionAdapter = new SectionRVAdapter(results);
        sectionAdapter.setOnClickNewsListener((SectionRVAdapter.OnClickNewsListener) getContext());
        rv.setAdapter(sectionAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void OnClickNews(Section.Result r) {
        //открытие ссылки в приложении
//        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(r.getUrl()));
//        getContext().startActivity(i);

        Intent intent = new Intent(getContext(), NewsActivity.class);
        intent.putExtra(Tools.INTENT_EXTRA_URL, r.getUrl());
        startActivity(intent);
    }

    @Override
    public void OnClickStar(Section.Result r, final SectionRVAdapter.ViewHolder vh, int pos) {
        //todo if (star not clicked) save news to database
        switch (sectionUrl){
            case Urls.url_mostemailed:
                repo.getEmailedSection().getResults().get(pos).setFavorite(!r.isFavorite());
                break;
            case Urls.url_mostshared:
                repo.getSharedSection().getResults().get(pos).setFavorite(!r.isFavorite());
                break;
            case Urls.url_mostviewed:
                repo.getViewedSection().getResults().get(pos).setFavorite(!r.isFavorite());
                break;
        }
        sectionAdapter.setStar(vh,!r.isFavorite());
        if(!r.isFavorite()){
            sectionAdapter.setProgressBarVisibility(vh,View.VISIBLE);
            // save to db
            DbPutTask putTask = new DbPutTask(db, r, new DbPutTask.OnPostPut() {
                @Override
                public void postPut() {
                    sectionAdapter.setProgressBarVisibility(vh,View.INVISIBLE);
                }
            });
            putTask.execute();


        }
        else {
            //todo remove from db
            sectionAdapter.setProgressBarVisibility(vh,View.VISIBLE);
            DbRemoveTask removeTask = new DbRemoveTask(db, r, new DbRemoveTask.OnPostRemove() {
                @Override
                public void postRemove() {
                    sectionAdapter.setProgressBarVisibility(vh,View.INVISIBLE);
                }
            });
            removeTask.execute();
        }

    }



//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
