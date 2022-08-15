package com.example.project_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LikeSaveDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public LikeSaveDBHelper(@Nullable Context context) {
        super(context, "LikeSaveDB", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SaveSQL = "create table LikeSave(" +
                "_id integer primary key autoincrement," +
                "title text," +
                "hae text," +
                "direc text," +
                "act text," +
                "star text," +
                "imgURL text," +
                "flag integer)";

        db.execSQL(SaveSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table LikeSave");
            onCreate(db);
        }
    }
}
