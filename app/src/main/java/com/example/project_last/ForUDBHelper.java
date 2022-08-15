package com.example.project_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class ForUDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public ForUDBHelper(@Nullable Context context) {
        super(context, "MovieDB", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table MovieTB(" +
                "_id integer primary key autoincrement," +
                "title text," +
                "imgPoster integer)";

        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"크루엘라" + "', '" +  R.drawable.cruela + "')";
        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"미드나잇 인 파리" + "', '" +  R.drawable.paris + "')";
        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"인턴" + "', '" +  R.drawable.intern + "')";
        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"악마는 프라다를 입는다" + "', '" +  R.drawable.prada + "')";
        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"닥터 스트레인지" + "', '" +  R.drawable.doctor + "')";
        db.execSQL(query);
        query = "Insert into MovieTB(title, imgPoster) values ('" +"셜록: 유령신부" + "', '" +  R.drawable.sher + "')";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table MovieTB");
            onCreate(db);
        }
    }
}
