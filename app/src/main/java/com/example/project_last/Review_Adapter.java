package com.example.project_last;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.ReviewViewHolder> {
    Context c;
    private ArrayList<item_getset4> itemList;
    View view;
    int pos;
    private Review_Adapter.OnItemClickListener_R listener_r = null;

    public interface OnItemClickListener_R {
        void onItemClick_R(View v, int position, String title_MR, String imgurl, String title_R, String content_R, String with_p, String date, String rating);
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(Review_Adapter.OnItemClickListener_R listener) {
        this.listener_r = listener;
    }

    Review_Adapter(Context c, ArrayList<item_getset4> list) {
        this.c = c;
        itemList = list;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title_RM, title_R, content_M, date, with_p, rating;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgurl);
            title_RM = itemView.findViewById(R.id.title_MR);
            title_R = itemView.findViewById(R.id.title_R);
            content_M = itemView.findViewById(R.id.content_R);
            date = itemView.findViewById(R.id.date_R);
            with_p = itemView.findViewById(R.id.with_p);
            rating = itemView.findViewById(R.id.rating_R);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        if (listener_r != null)
                            listener_r.onItemClick_R(v, pos, itemList.get(pos).getTitle_R(), itemList.get(pos).getImgurl(), itemList.get(pos).getTitle_R(), itemList.get(pos).getContent_R(), itemList.get(pos).getWith_p(), itemList.get(pos).getDate(), itemList.get(pos).getRating());
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
    public void onBindViewHolder(@NonNull Review_Adapter.ReviewViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.title_RM.setText(itemList.get(position).getTitle_MR());
        holder.title_R.setText("평론 제목 : " + itemList.get(position).getTitle_R());
        holder.content_M.setText("평론 내용 : " + itemList.get(position).getContent_R());
        holder.date.setText("감상일 : " + itemList.get(position).getDate());
        holder.with_p.setText("함께 본 사람 : " + itemList.get(position).getWith_p());
        holder.rating.setText("별점 : "+itemList.get(position).getRating());
        String imgurl = itemList.get(position).getImgurl();

        Glide.with(c).load(imgurl).into(holder.img);



    }

    @Override
    public Review_Adapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_review, parent, false);
        return new Review_Adapter.ReviewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}