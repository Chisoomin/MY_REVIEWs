package com.example.project_last;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_MyPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_MyPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_MyPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_MyPage.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_MyPage newInstance(String param1, String param2) {
        Fragment_MyPage fragment = new Fragment_MyPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView pic_pf;
    String name_d, genre, uri;
    TextView interesting, name, cnt, cnt2;
    ImageView btn_arr, btn_arr2;
    RecyclerView recommendView;
    ArrayList<item_getset3> list = new ArrayList<item_getset3>();
    int i =0, j=0;
    //Mypage_Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__mypage, container, false);
        pic_pf = view.findViewById(R.id.pic_pf);
        interesting = view.findViewById(R.id.interesting);
        name = view.findViewById(R.id.name);
        cnt = view.findViewById(R.id.cnt);
        cnt2 = view.findViewById(R.id.cnt2);
        btn_arr = view.findViewById(R.id.btn_arr);
        btn_arr2 = view.findViewById(R.id.btn_arr2);
        recommendView = view.findViewById(R.id.recommendView);

        recommendView.removeAllViewsInLayout();
        /*ForUDBHelper dbHelper2 = new ForUDBHelper(getContext());
        SQLiteDatabase dbR2 = dbHelper2.getReadableDatabase();
        Cursor cursor2 = dbR2.rawQuery("select title, imgPoster from MovieTB;", null);
        while (cursor2.moveToNext()){
            list.add(new item_getset3(cursor2.getInt(1),cursor2.getString(0)));
        }*/
        if(list.size()>0){
            list.clear();
        }
        item_getset3 itgs = new item_getset3(R.drawable.cruela,"크루엘라");
        item_getset3 itgs1 = new item_getset3(R.drawable.paris,"미드나잇 인 파리");
        item_getset3 itgs2 = new item_getset3(R.drawable.prada,"악마는 프라다를 입는다");
        item_getset3 itgs3 = new item_getset3(R.drawable.intern,"인턴");
        item_getset3 itgs4 = new item_getset3(R.drawable.sher,"셜록: 유령신부");
        item_getset3 itgs5 = new item_getset3(R.drawable.doctor,"닥터 스트레인지");
        list.add(itgs);
        list.add(itgs1);
        list.add(itgs2);
        list.add(itgs3);
        list.add(itgs4);
        list.add(itgs5);


        Mypage_Adapter adapter = new Mypage_Adapter(view.getContext(), list);
        adapter.notifyDataSetChanged();
        recommendView.setAdapter(adapter);
        recommendView.setLayoutManager( new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false ) );


        JoinDBHelper dbHelper = new JoinDBHelper(getContext());
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Cursor cursor = dbR.rawQuery("select name, likeGenre, imgURL_pr from joinTB;", null);
        while (cursor.moveToNext()){
            name_d = cursor.getString(0);
            genre = cursor.getString(1);
            uri = cursor.getString(2);
        }
        Glide.with(this).load(uri).override(100,100).circleCrop().into(pic_pf);
        interesting.setText(genre+" 장르를 선호하시는");
        name.setText(name_d+"님");

        LikeSaveDBHelper likeSaveDBHelper = new LikeSaveDBHelper(view.getContext());
        SQLiteDatabase dbR2 = likeSaveDBHelper.getReadableDatabase();
        Cursor cursor2 = dbR2.rawQuery("select _id from LikeSave;", null);
        while (cursor2.moveToNext()) {
            i = cursor2.getInt(0);
        }
        cnt.setText("총 "+i+"개의 즐겨찾기");

        btn_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Bookmark fragment_bookmark = Fragment_Bookmark.newInstance();
                ((MainActivity) getActivity()).replaceFragment1(fragment_bookmark);
            }
        });

        ReviewDBHelper reviewDBHelper = new ReviewDBHelper(view.getContext());
        SQLiteDatabase dbR3 = reviewDBHelper.getReadableDatabase();
        Cursor cursor3 = dbR3.rawQuery("select _id from ReviewTB;", null);
        while (cursor3.moveToNext()) {
            j = cursor3.getInt(0);
        }
        cnt2.setText("총 "+j+"개의 평론");

        btn_arr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ReviewList fragment_reviewList = Fragment_ReviewList.newInstance();
                ((MainActivity) getActivity()).replaceFragment2(fragment_reviewList);
            }
        });

        return view;
    }
}