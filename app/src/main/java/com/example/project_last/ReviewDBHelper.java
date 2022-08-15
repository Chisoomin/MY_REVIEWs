package com.example.project_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class ReviewDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public ReviewDBHelper(@Nullable Context context) {
        super(context, "ReviewDB", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ReviewSQL = "create table ReviewTB(" +
                "_id integer primary key autoincrement," +
                "title_MR text," +
                "imgurl_R text," +
                "title_R text," +
                "content_R CHAR(1000)," +
                "date DATETIME," +
                "with_p text," +
                "rating text)";
        db.execSQL(ReviewSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table ReviewTB");
            onCreate(db);
        }
    }
}
