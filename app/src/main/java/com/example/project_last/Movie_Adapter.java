package com.example.project_last;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.MovieViewHolder> {
    Context c;
    private ArrayList<item_getset> itemList;
    String savetitle, bong;
    View view;
    Boolean like=false;

    private Movie_Adapter.OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position, String title, String gebong, String direc, String act, String star, String url);
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(Movie_Adapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    Movie_Adapter(Context c, ArrayList<item_getset> list) {
        this.c = c;
        itemList = list;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView poster, star;
        TextView re_title, re_date, re_direc, re_act, re_rating;
        ConstraintLayout bg;
        public MovieViewHolder(View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.bg);
            poster = itemView.findViewById(R.id.imageView);
            re_title = itemView.findViewById(R.id.re_title);
            re_date = itemView.findViewById(R.id.re_date);
            re_direc = itemView.findViewById(R.id.re_direc);
            re_act = itemView.findViewById(R.id.re_act);
            re_rating = itemView.findViewById(R.id.re_rating);
            star = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        if (mListener != null) {
                            mListener.onItemClick(v, pos, itemList.get(pos).getJe().replace("제목 : ", ""), itemList.get(pos).getGe().replace("개봉일 : ", ""), itemList.get(pos).getGam().replace("감독 : ", ""), itemList.get(pos).getBe().replace("주연배우 : ", ""), itemList.get(pos).getStar().replace("별점 : ", ""), itemList.get(pos).getUrl());
                        }
                    }
                }
            });


        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        LikeSaveDBHelper dbHelper = new LikeSaveDBHelper(c);
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();

        Cursor cursor = dbR.rawQuery("select title, hae, flag from LikeSave;", null);
        while (cursor.moveToNext()) {
            savetitle = cursor.getString(0);
            bong = cursor.getString(1);
            if (savetitle.equals(itemList.get(position).getJe().replace("제목 : ", "")) && bong.equals(itemList.get(position).getGe().replace("개봉일 : ", ""))) {
                holder.bg.setBackgroundResource(R.drawable.roundedge_pick);
                holder.star.setBackgroundResource(R.drawable.like_star);
            } else {
                //holder.bg.setBackgroundColor(0xFFD5D9F1);
            }
        }

        holder.re_title.setText(itemList.get(position).getJe());
        holder.re_date.setText(itemList.get(position).getGe());
        holder.re_direc.setText(itemList.get(position).getGam());
        holder.re_act.setText(itemList.get(position).getBe());
        holder.re_rating.setText(itemList.get(position).getStar());
        String imgurl = itemList.get(position).getUrl();


        Handler handler = new Handler();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imgurl);
                    InputStream is = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.poster.setImageBitmap(bm);
                        }
                    });
                    //holder.poster.setImageBitmap(bm);
                } catch (Exception e) {
                    //holder.poster.setImageResource(R.drawable.gray);
                }

            }
        });

        t.start();


    }

    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
