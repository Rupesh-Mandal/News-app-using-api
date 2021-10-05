package com.kali_corporation.financual_study_guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kali_corporation.financual_study_guide.R;
import com.kali_corporation.financual_study_guide.WebViewActivity;
import com.kali_corporation.financual_study_guide.modle.MainModel;
import com.kali_corporation.financual_study_guide.modle.NewsModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewsModel> mainModelArrayList;

    public MyAdapter(Context context, ArrayList<NewsModel> mainModelArrayList) {
        this.context = context;
        this.mainModelArrayList = mainModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyAdapter.MyViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebViewActivity.class);
                intent.putExtra("url",mainModelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.mHeading.setText(mainModelArrayList.get(position).getTitle());
        holder.mContent.setText(mainModelArrayList.get(position).getDescription());
        holder.mAuthor.setText("By "+mainModelArrayList.get(position).getAuthor());
        holder.mTime.setText("Published At "+mainModelArrayList.get(position).getPublishedAt());
        Glide.with(context).load(mainModelArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mainModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mHeading,mContent,mAuthor,mTime;
        CardView cardView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeading=itemView.findViewById(R.id.main_heading);
            mContent=itemView.findViewById(R.id.content);
            mAuthor=itemView.findViewById(R.id.author);
            mTime=itemView.findViewById(R.id.time);
            cardView=itemView.findViewById(R.id.card_view);
            imageView=itemView.findViewById(R.id.image_view);

        }
    }
}
