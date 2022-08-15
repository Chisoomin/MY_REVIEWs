package com.example.project_last;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

class Mypage_Adapter extends RecyclerView.Adapter<Mypage_Adapter.MypageViewHolder> {
    Context c;
    private ArrayList<item_getset3> itemList;
    View view;

    Mypage_Adapter(Context c, ArrayList<item_getset3> list) {
        this.c = c;
        itemList = list;
    }

    public class MypageViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView je;
        public MypageViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            je = itemView.findViewById(R.id.je);

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull Mypage_Adapter.MypageViewHolder holder, final int position) {

        holder.je.setText(itemList.get(position).getJe());
        int uri = itemList.get(position).getPoster();
        Glide.with(c).load(uri).into(holder.poster);


    }

    @Override
    public Mypage_Adapter.MypageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recom_recycler, parent, false);
        return new Mypage_Adapter.MypageViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}