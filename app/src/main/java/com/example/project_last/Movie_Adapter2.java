package com.example.project_last;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

class Movie_Adapter2 extends RecyclerView.Adapter<Movie_Adapter2.MovieViewHolder2> {
    Context c;
    private ArrayList<item_getset> itemList;
    View view;
    private int oldPosition = -1000;
    private int selectedPosition = -1000;
    int pos;
    private Movie_Adapter2.OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position, String title, String url);
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(Movie_Adapter2.OnItemClickListener listener) {
        this.mListener = listener;
    }

    Movie_Adapter2(Context c, ArrayList<item_getset> list) {
        this.c = c;
        itemList = list;
    }

    public class MovieViewHolder2 extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView re_title, re_date, re_direc, re_act, re_rating;
        ConstraintLayout bg;

        public MovieViewHolder2(View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.bg_dia);
            poster = itemView.findViewById(R.id.imageView_dia);
            re_title = itemView.findViewById(R.id.re_title_dia);
            re_date = itemView.findViewById(R.id.re_date_dia);
            re_direc = itemView.findViewById(R.id.re_direc_dia);
            re_act = itemView.findViewById(R.id.re_act_dia);
            re_rating = itemView.findViewById(R.id.re_rating_dia);
            bg.setBackgroundColor(Color.TRANSPARENT);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pos = getAdapterPosition();
                    oldPosition = selectedPosition;
                    selectedPosition = pos;

                    //notifyDataSetChanged();
                    notifyItemChanged(oldPosition);
                    notifyItemChanged(selectedPosition);


                    if (pos != RecyclerView.NO_POSITION) {

                        if (mListener != null) {
                            mListener.onItemClick(v, pos, itemList.get(pos).getJe().replace("제목 : ", ""), itemList.get(pos).getUrl());
                        }
                    }
                }
            });
            if (selectedPosition == pos)
                bg.setBackgroundColor(view.getResources().getColor(R.color.purple_500));
            else
                bg.setBackgroundColor(Color.TRANSPARENT);

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull Movie_Adapter2.MovieViewHolder2 holder, @SuppressLint("RecyclerView") final int position) {

        holder.re_title.setText(itemList.get(position).getJe());
        holder.re_date.setText(itemList.get(position).getGe());
        holder.re_direc.setText(itemList.get(position).getGam());
        holder.re_act.setText(itemList.get(position).getBe());
        holder.re_rating.setText(itemList.get(position).getStar());
        String imgurl = itemList.get(position).getUrl();

        /*if (selectedPosition == position)
            holder.bg.setBackgroundColor(view.getResources().getColor(R.color.purple_500));
        else
            holder.bg.setBackgroundColor(Color.TRANSPARENT);

        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPosition = selectedPosition;
                selectedPosition = position;

                //notifyDataSetChanged();
                notifyItemChanged(oldPosition);
                notifyItemChanged(selectedPosition);
            }
        });*/


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
    public Movie_Adapter2.MovieViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_reviewsearch, parent, false);
        return new Movie_Adapter2.MovieViewHolder2(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
