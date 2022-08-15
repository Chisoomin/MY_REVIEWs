package com.example.project_last;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

class BookMark_Adapter  extends RecyclerView.Adapter<BookMark_Adapter.BookMarkViewHolder> {
    Context c;
    private ArrayList<item_getset2> itemList;
    String savetitle, bong;
    View view;
    Boolean like=false;

   /* private BookMark_Adapter.OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position, String title, String gebong, String direc, String act, String star, String url);
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(BookMark_Adapter.OnItemClickListener listener) {
        this.mListener = listener;
    }*/

    BookMark_Adapter(Context c, ArrayList<item_getset2> list) {
        this.c = c;
        itemList = list;
    }

    public class BookMarkViewHolder extends RecyclerView.ViewHolder {
        ImageView poster, bookmark;
        TextView re_title, re_date, re_direc, re_act, re_rating;
        public BookMarkViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imageView_bk);
            re_title = itemView.findViewById(R.id.re_title_bk);
            re_date = itemView.findViewById(R.id.re_date_bk);
            re_direc = itemView.findViewById(R.id.re_direc_bk);
            re_act = itemView.findViewById(R.id.re_act_bk);
            re_rating = itemView.findViewById(R.id.re_rating_bk);
            bookmark = itemView.findViewById(R.id.imageView2_bk);


        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull BookMark_Adapter.BookMarkViewHolder holder, final int position) {

        holder.re_title.setText("제목 : "+itemList.get(position).getJe());
        holder.re_date.setText("개봉일 : "+itemList.get(position).getGe());
        holder.re_direc.setText("감독 : "+itemList.get(position).getGam());
        holder.re_act.setText("주연배우 : "+itemList.get(position).getBe());
        holder.re_rating.setText("별점 : "+itemList.get(position).getStar());
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
    public BookMark_Adapter.BookMarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_bookmark, parent, false);
        return new BookMark_Adapter.BookMarkViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}