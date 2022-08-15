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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Bookmark#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Bookmark extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Bookmark() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Bookmark.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Bookmark newInstance(String param1, String param2) {
        Fragment_Bookmark fragment = new Fragment_Bookmark();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment_Bookmark newInstance() {
        return new Fragment_Bookmark();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<item_getset2> list = new ArrayList<item_getset2>();
    RecyclerView bookmark;
    BookMark_Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment__bookmark, container, false);
        bookmark = view.findViewById(R.id.recycler_bookmark);
        bookmark.removeAllViewsInLayout();

        if(list.size()>0){
            list.clear();
        }

        LikeSaveDBHelper dbHelper = new LikeSaveDBHelper(view.getContext());
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Cursor cursor = dbR.rawQuery("select title, hae, direc, act, star, imgURL from LikeSave;", null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String hae = cursor.getString(1);
            String direc = cursor.getString(2);
            String act = cursor.getString(3);
            String star = cursor.getString(4);
            String imgURL = cursor.getString(5);
            item_getset2 itgs2 = new item_getset2(title, hae, direc, act, star, imgURL);
            Log.d("뭐가 들어가냐",title+hae+direc+act+star+imgURL+"");
            list.add(itgs2);
        }
        adapter = new BookMark_Adapter(view.getContext(), list);
        bookmark.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bookmark.setAdapter(adapter);

        return  view;
    }
}