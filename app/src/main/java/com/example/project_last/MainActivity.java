package com.example.project_last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.total_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        fragment_search = new Fragment_Search();
        fragment_bookmark = new Fragment_Bookmark();
        fragment_reviewList = new Fragment_ReviewList();
        fragment_mypage = new Fragment_MyPage();
        fragment_explain = new Fragment_Explain();

        fragmentManager = getSupportFragmentManager();
        if (item.getItemId() == R.id.menu1){
            search.setImageResource(R.drawable.search);
            bookmark.setImageResource(R.drawable.bookmark);
            review.setImageResource(R.drawable.review);
            mypage.setImageResource(R.drawable.mypage);
            setTitle(name+"'s MOVIE LIFE");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.container, fragment_explain);
            ft.commit();
        }else if (item.getItemId() == R.id.menu3){
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.sub1){
            search.setImageResource(R.drawable.search_open);
            bookmark.setImageResource(R.drawable.bookmark);
            review.setImageResource(R.drawable.review);
            mypage.setImageResource(R.drawable.mypage);
            setTitle("MOVIE SEARCH");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.container, fragment_search);
            ft.commit();
        }else if (item.getItemId() == R.id.sub2){
            search.setImageResource(R.drawable.search);
            bookmark.setImageResource(R.drawable.bookmark_open);
            review.setImageResource(R.drawable.review);
            mypage.setImageResource(R.drawable.mypage);
            setTitle(name+"'s BOOKMARKs");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.container, fragment_bookmark);
            ft.commit();
        }else if (item.getItemId() == R.id.sub3){
            search.setImageResource(R.drawable.search);
            bookmark.setImageResource(R.drawable.bookmark);
            review.setImageResource(R.drawable.review_open);
            mypage.setImageResource(R.drawable.mypage);
            setTitle(name+"'s REVIEWs");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.container, fragment_reviewList);
            ft.commit();
        }else if (item.getItemId() == R.id.sub4){
            search.setImageResource(R.drawable.search);
            bookmark.setImageResource(R.drawable.bookmark);
            review.setImageResource(R.drawable.review);
            mypage.setImageResource(R.drawable.mypage_open);
            setTitle(name+"'s PAGE");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.container, fragment_mypage);
            ft.commit();
        } else if (item.getItemId() == R.id.menu4){
            Intent intent = new Intent(getApplicationContext(), Infomation.class);
            startActivity(intent);
        }
        return false;
    }
    ConstraintLayout container;
    Fragment_Search fragment_search;
    Fragment_Bookmark fragment_bookmark;
    Fragment_ReviewList fragment_reviewList;
    Fragment_MyPage fragment_mypage;
    Fragment_Explain fragment_explain;
    ImageButton search, review, bookmark, mypage;
    FragmentManager fragmentManager;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JoinDBHelper dbHelper = new JoinDBHelper(getApplicationContext());
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Cursor cursor = dbR.rawQuery("select name from joinTB;", null);
        while (cursor.moveToNext()){
            name = cursor.getString(0);
        }

        setTitle(name+"'s MOVIE LIFE");

        container = findViewById(R.id.container);
        search = findViewById(R.id.search);
        bookmark = findViewById(R.id.bookmark);
        review = findViewById(R.id.review);
        mypage = findViewById(R.id.mypage);

        fragment_search = new Fragment_Search();
        fragment_bookmark = new Fragment_Bookmark();
        fragment_reviewList = new Fragment_ReviewList();
        fragment_mypage = new Fragment_MyPage();
        fragment_explain = new Fragment_Explain();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.container, fragment_explain);
        ft.commit();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search_open);
                bookmark.setImageResource(R.drawable.bookmark);
                review.setImageResource(R.drawable.review);
                mypage.setImageResource(R.drawable.mypage);
                setTitle("MOVIE SEARCH");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragment_search);
                ft.commit();
            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                bookmark.setImageResource(R.drawable.bookmark_open);
                review.setImageResource(R.drawable.review);
                mypage.setImageResource(R.drawable.mypage);
                setTitle(name+"'s BOOKMARKs");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragment_bookmark);
                ft.commit();
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                bookmark.setImageResource(R.drawable.bookmark);
                review.setImageResource(R.drawable.review_open);
                mypage.setImageResource(R.drawable.mypage);
                setTitle(name+"'s REVIEWs");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragment_reviewList);
                ft.commit();
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setImageResource(R.drawable.search);
                bookmark.setImageResource(R.drawable.bookmark);
                review.setImageResource(R.drawable.review);
                mypage.setImageResource(R.drawable.mypage_open);
                setTitle(name+"'s PAGE");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragment_mypage);
                ft.commit();
            }
        });
    }

    public void replaceFragment1(Fragment fragment) {
        mypage.setImageResource(R.drawable.mypage);
        bookmark.setImageResource(R.drawable.bookmark_open);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
    public void replaceFragment2(Fragment fragment) {
        mypage.setImageResource(R.drawable.mypage);
        review.setImageResource(R.drawable.review_open);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
}