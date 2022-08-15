package com.example.project_last;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ReviewList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ReviewList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_ReviewList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_ReviewList.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_ReviewList newInstance(String param1, String param2) {
        Fragment_ReviewList fragment = new Fragment_ReviewList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment_ReviewList newInstance() {
        return new Fragment_ReviewList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerView;
    ImageButton plus_btn;
    Button button;
    String mresult, title_data, imgurl_data;

    ArrayList<item_getset> list = new ArrayList<item_getset>();
    ArrayList<item_getset4> list2 = new ArrayList<item_getset4>();
    JSONObject jsonObject = null;
    JSONArray jsonArray = null;
    Movie_Adapter2 adapter;
    Review_Adapter review_adapter;
    ImageView renew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__reviewlist, container, false);

        recyclerView = view.findViewById(R.id.rec_review);
        plus_btn = view.findViewById(R.id.plus_btn);
        renew = view.findViewById(R.id.renew);
        Intent intent = new Intent(view.getContext(), ContentsReview.class);

        renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_ReviewList fragment_reviewList = Fragment_ReviewList.newInstance();
                ((MainActivity) getActivity()).replaceFragment2(fragment_reviewList);
            }
        });
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_search, null);

                Button sear_btn = dialogView.findViewById(R.id.button2);
                RecyclerView recyclerView_sear = dialogView.findViewById(R.id.dia_sear);
                EditText title = dialogView.findViewById(R.id.dia_title);
                TextView textView = dialogView.findViewById(R.id.textView9);
                sear_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String titleM = title.getText().toString();
                        if(!titleM.equals("")){
                            textView.setVisibility(View.GONE);
                        }
                        String text;
                        try {
                            text = URLEncoder.encode(titleM, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException("검색어 인코딩 실패", e);
                        }
                        if (text != titleM) {
                            recyclerView_sear.removeAllViewsInLayout();
                        }

                        MyAsyncTask asyncTask = new MyAsyncTask(text, new AsyncCallback() {
                            @Override
                            public void onSuccess(String result) {
                                mresult = result;
                                try {
                                    jsonObject = new JSONObject(String.valueOf(mresult));
                                    jsonArray = jsonObject.getJSONArray("items");
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        JSONObject jsonObj = jsonArray.getJSONObject(j);
                                        item_getset itgs = new item_getset(jsonObj);
                                        list.add(itgs);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter = new Movie_Adapter2(view.getContext(), list);

                                adapter.setOnItemClickListener(new Movie_Adapter2.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View v, int position, String title, String imgurl) {
                                        title_data = title;
                                        imgurl_data = imgurl;
                                        Log.d("확인","어리나어ㅣ나러ㅣ낭러니아"+title+imgurl);
                                        intent.putExtra("title", title_data);
                                        intent.putExtra("imgurl", imgurl_data);
                                    }
                                });

                                recyclerView_sear.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                recyclerView_sear.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });
                        list.clear();
                        asyncTask.execute();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setPositiveButton("평론 작성", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();

            }
        });

        if(list.size()>0){
            list.clear();
        }
        if(list2.size()>0){
            list2.clear();
        }

        ReviewDBHelper dbHelper = new ReviewDBHelper(view.getContext());
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Cursor cursor = dbR.rawQuery("select title_MR, imgurl_R, title_R, content_R, date, with_p, rating from ReviewTB;", null);
        while (cursor.moveToNext()) {
            String title_MR = cursor.getString(0);
            String imgurl_R = cursor.getString(1);
            String title_R = cursor.getString(2);
            String content_R = cursor.getString(3);
            String date = cursor.getString(4);
            String with_p = cursor.getString(5);
            String rating = cursor.getString(6);
            Log.d("어케 뜨는 거지",""+title_MR+imgurl_R+title_R+content_R+date+with_p+rating);
            item_getset4 itgs4 = new item_getset4(title_MR, imgurl_R, title_R, content_R, with_p, date, rating);
            list2.add(itgs4);
        }
        review_adapter = new Review_Adapter(view.getContext(), list2);


        review_adapter.setOnItemClickListener(new Review_Adapter.OnItemClickListener_R() {
            @Override
            public void onItemClick_R(View v, int position, String title_MR, String imgurl, String title_R, String content_R, String with_p, String date, String rating) {
                View dialogView = getLayoutInflater().inflate(R.layout.dia_review, null);

                ImageView poster = dialogView.findViewById(R.id.imgurl_dia);
                TextView title = dialogView.findViewById(R.id.title_MR_dia);
                TextView date_dia = dialogView.findViewById(R.id.date_R_dia);
                TextView with_p_dia = dialogView.findViewById(R.id.with_p_dia);
                TextView title_R_dia = dialogView.findViewById(R.id.title_R_dia);
                TextView content_R_dia = dialogView.findViewById(R.id.content_R_dia);
                TextView rating_R_dia = dialogView.findViewById(R.id.rating_R_dia);

                title.setText(title_MR);
                date_dia.setText("감상일 : "+date);
                with_p_dia.setText("함께 본 사람 : "+with_p);
                title_R_dia.setText("평론 제목 : "+title_R);
                content_R_dia.setText("평론 내용 : "+content_R);
                rating_R_dia.setText("별점 : "+ rating);
                Glide.with(getContext()).load(imgurl).into(poster);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(review_adapter);
        return view;
    }
}