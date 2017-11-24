package com.sharma.deepak.newsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharma.deepak.newsapp.R;
import com.sharma.deepak.newsapp.model.Article;

import java.util.ArrayList;

/**
 * Created by deepak on 08-07-2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolderPattern> {
    private ArrayList<Article> articleListDetail;
    private Context context;
    OnArticleClickListener onArticleClickListener;

    public interface OnArticleClickListener {
        void OnArticleClick(String url);
    }

    public ArticleAdapter(Context context, OnArticleClickListener onArticleClickListener, ArrayList<Article> articleListDetail) {
        this.context = context;
        this.onArticleClickListener = onArticleClickListener;
        this.articleListDetail = articleListDetail;
    }

    @Override
    public ArticleHolderPattern onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_list, parent, false);
        return new ArticleHolderPattern(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleHolderPattern holder, int position) {
        Article article = articleListDetail.get(position);
        holder.tvTitle.setText(article.getWebTitle());
        String []dateAndTime=article.getDate().split("T");
        String date=dateAndTime[0];
        holder.tvDate.setText(date);
        holder.tvSection.setText(article.getSectionName());
    }

    @Override
    public int getItemCount() {
        return articleListDetail.size();
    }

    class ArticleHolderPattern extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvDate, tvSection;

        public ArticleHolderPattern(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_article_name);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvSection = (TextView) itemView.findViewById(R.id.tv_section_name);
        }

        @Override
        public void onClick(View v) {
            onArticleClickListener.OnArticleClick(articleListDetail.get(getAdapterPosition()).getWebUrl());
        }
    }

}

