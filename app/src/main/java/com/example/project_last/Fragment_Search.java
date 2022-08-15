package com.example.project_last;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
 * Use the {@link Fragment_Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Search newInstance(String param1, String param2) {
        Fragment_Search fragment = new Fragment_Search();
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

    EditText title;
    Button button;
    RecyclerView recyclerView;
    String mresult, savetitle, bong;

    ArrayList<item_getset> list = new ArrayList<item_getset>();
    JSONObject jsonObject = null;
    JSONArray jsonArray = null;
    Movie_Adapter adapter;
    Boolean Blike = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__search, container, false);
        title = view.findViewById(R.id.title_Edit_Text);
        button = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclerView);
        LikeSaveDBHelper dbHelper = new LikeSaveDBHelper(view.getContext());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleM = title.getText().toString();
                String text;
                try {
                    text = URLEncoder.encode(titleM, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("검색어 인코딩 실패", e);
                }
                if (text != titleM) {
                    recyclerView.removeAllViewsInLayout();
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
                        adapter = new Movie_Adapter(view.getContext(), list);

                        adapter.setOnItemClickListener(new Movie_Adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position, String title, String gebong, String direc, String act, String star, String imgurl) {
                                View dialogView = getLayoutInflater().inflate(R.layout.dialog_like, null);
                                LikeSaveDBHelper dbHelper = new LikeSaveDBHelper(view.getContext());
                                SQLiteDatabase dbR = dbHelper.getReadableDatabase();
                                Blike = false;
                                Cursor cursor = dbR.rawQuery("select title, hae, flag from LikeSave;", null);
                                while (cursor.moveToNext()) {
                                    savetitle = cursor.getString(0);
                                    bong = cursor.getString(1);
                                    if (savetitle.equals(title) && bong.equals(gebong)) {
                                        Blike = true;
                                        break;
                                    }
                                }

                                ImageView dia_poster = dialogView.findViewById(R.id.dia_poster);
                                ImageButton like = dialogView.findViewById(R.id.like);
                                TextView settitle = dialogView.findViewById(R.id.titletext);

                                settitle.setText(title);


                                Glide.with(view.getContext()).load(imgurl.trim()).error(R.drawable.gray).into(dia_poster);
                                if (Blike) {
                                    like.setImageResource(R.drawable.like);
                                } else {
                                    like.setImageResource(R.drawable.unlike);
                                }
                                like.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //like.setImageResource(R.drawable.like_c);
                                        if(Blike==false){
                                            Blike=true;
                                        }else if(Blike==true){
                                            Blike=false;
                                        }
                                        if (Blike) {
                                            like.setImageResource(R.drawable.like);
                                            Toast.makeText(view.getContext(), "좋아요~", Toast.LENGTH_SHORT).show();
                                        } else {
                                            like.setImageResource(R.drawable.unlike);
                                        }
                                    }
                                });

                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setView(dialogView);
                                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int pos) {
                                        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
                                        Cursor cursor = dbR.rawQuery("select title, hae,flag from LikeSave;", null);
                                        //Log.e("Drawable 값", );
                                        if (Blike == true) {
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            String query = "Insert into LikeSave(title, hae, direc, act, star, imgURL, flag) values ('" + title + "', '" + gebong + "', '" + direc + "', '" + act + "', '" + star + "', '" + imgurl + "', 0)";
                                            db.execSQL(query);
                                            db.close();
                                            v.setBackgroundResource(R.drawable.roundedge_pick);
                                            v.findViewById(R.id.imageView2).setBackgroundResource(R.drawable.like_star);
                                        }else if (Blike == false) {
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            db.delete("LikeSave", "title = ? and hae = ? and direc = ? and act = ? and star = ?", new String[]{savetitle, bong, direc, act, star});
                                            db.close();
                                            v.findViewById(R.id.imageView2).setBackgroundResource(R.drawable.unlike_star);
                                            v.setBackgroundResource(R.drawable.roundedge);
                                        }
                                       /* while (cursor.moveToNext()) {
                                            savetitle = cursor.getString(0);
                                            bong = cursor.getString(1);
                                            int flag = cursor.getInt(2);
                                            //Drawable drawable = v.getContext().getDrawable(R.drawable.roundedge);
                                            //Log.e("Drawable 값", String.valueOf(drawable));
                                            if (Blike[0] == true && v.getBackground()==drawable) {
                                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                                String query = "Insert into LikeSave(title, hae, direc, act, star, imgURL, flag) values ('" + title + "', '" + gebong + "', '" + direc + "', '" + act + "', '" + star + "', '" + imgurl + "', 0)";
                                                db.execSQL(query);
                                                db.close();
                                                v.setBackgroundResource(R.drawable.roundedge_pick);
                                                break;
                                            } else if (Blike[0] == false && title.equals(savetitle) && bong.equals(gebong)) {
                                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                                db.delete("LikeSave", "title = ? and hae = ? and direc = ? and act = ? and star = ?", new String[]{savetitle, bong, direc, act, star});
                                                db.close();
                                                v.setBackgroundResource(R.drawable.roundedge);
                                                break;
                                            }
                                        }*/

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

                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
                list.clear();
                asyncTask.execute();
            }
        });
        return view;
    }
}