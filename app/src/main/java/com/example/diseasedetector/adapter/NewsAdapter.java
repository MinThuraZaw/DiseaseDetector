package com.example.diseasedetector.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diseasedetector.R;
import com.example.diseasedetector.model.Articles;
import com.example.diseasedetector.model.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<Articles> newsdata = null;
    private NewsModel newsModel;
    private Context context;
    private String website;
    private String shareurl;

    public NewsAdapter(Context mcontext, NewsModel mnews){
        this.context = mcontext;
        this.newsModel = mnews;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide
                .with(context)
                .load(newsModel.getArticles()[position].getUrlToImage())
                .placeholder(R.drawable.placeholder1)
                .into(holder.img_title);

        //holder.img_title.setImageResource(R.mipmap.heart_beat);

        holder.tv_title.setText(newsModel.getArticles()[position].getTitle());
        holder.tv_desc.setText(newsModel.getArticles()[position].getDescription());
        holder.tv_date.setText(newsModel.getArticles()[position].getPublishedAt());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                website = newsModel.getArticles()[position].getUrl();


                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                v.getContext().startActivity(Intent.createChooser(browserIntent, "Choose browser"));
            }
        });

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
                shareurl = newsModel.getArticles()[position].getUrl();


                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = shareurl;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModel.getArticles().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tv_title, tv_desc, tv_date;
        AppCompatImageView img_title;
        AppCompatImageButton btn_share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_title = itemView.findViewById(R.id.img_title);
            btn_share = itemView.findViewById(R.id.btn_share);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/avenirnextregular.ttf");
            tv_title.setTypeface(font);
        }
    }
}
