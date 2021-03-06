package com.hp.nytimes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hp.nytimes.R;
import com.hp.nytimes.templates.NewsEntity;
import com.hp.nytimes.templates.Section;

import java.util.List;

/**
 * Created by Hp on 15.02.2018.
 */

public class SectionRVAdapter extends RecyclerView.Adapter<SectionRVAdapter.ViewHolder> {

    RequestOptions requestOptions;
    OnClickNewsListener onClickNewsListener;
    List<NewsEntity> nList;



    public SectionRVAdapter(List<NewsEntity> nList) {
        this.nList = nList;
        requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(R.drawable.ic_001_home);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new SectionRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final NewsEntity ne = nList.get(position);
        holder.tv_title.setText(ne.getTitle());
        holder.tv_date.setText(ne.getDate());
        holder.tv_content.setText(ne.getAbstracts());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNewsListener.OnClickNews(ne);
            }
        });
        holder.iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNewsListener.OnClickStar(ne, holder, holder.getAdapterPosition());
            }
        });
        setStar(holder, ne.isFavorite());
//        if(ne.isFavorite()){
//            holder.iv_star.setImageDrawable(holder.itemView.getResources().getDrawable(android.R.drawable.btn_star_big_on));
//        }
//        else {
//            holder.iv_star.setImageDrawable(holder.itemView.getResources().getDrawable(android.R.drawable.btn_star_big_off));
//        }

//        String path = r.getMediaList().get(0).getMetadataList().get(0).getUrl();
//        if (!path.isEmpty())
//            Glide.with(holder.itemView)
//                    .asBitmap()
//                    .load(path)
//                    .apply(requestOptions)
//                    .into(iv);
    }

    public void setStar(ViewHolder vh, boolean isFavorite){
        if(isFavorite){
            vh.iv_star.setImageDrawable(vh.itemView.getResources().getDrawable(android.R.drawable.btn_star_big_on));
        }
        else {
            vh.iv_star.setImageDrawable(vh.itemView.getResources().getDrawable(android.R.drawable.btn_star_big_off));
        }
    }

    public void setProgressBarVisibility(ViewHolder vh, boolean visibility){
        if (visibility){
            vh.pb.setVisibility(View.VISIBLE);
            vh.iv_star.setVisibility(View.INVISIBLE);
        }
        else {
            vh.pb.setVisibility(View.INVISIBLE);
            vh.iv_star.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_date;
        TextView tv_content;
        ImageView iv;
        ImageView iv_star;
        ProgressBar pb;

        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            tv_date = (TextView) v.findViewById(R.id.tv_date);
            tv_content = (TextView) v.findViewById(R.id.tv_content);
            iv = (ImageView) v.findViewById(R.id.iv);
            iv_star = (ImageView) v.findViewById(R.id.iv_star);
            pb = (ProgressBar) v.findViewById(R.id.pb);

        }
    }

    public void setOnClickNewsListener(OnClickNewsListener onClickNewsListener) {
        this.onClickNewsListener = onClickNewsListener;
    }

    public interface OnClickNewsListener {
        void OnClickNews(NewsEntity ne);
        void OnClickStar(NewsEntity ne, ViewHolder vh, int pos);
    }



}
