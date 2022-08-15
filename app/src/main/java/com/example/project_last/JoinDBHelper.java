package com.example.project_last;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class JoinDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public JoinDBHelper(@Nullable Context context) {
        super(context, "JoinDB", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String joinSQL = "create table joinTB(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "likeGenre text," +
                "pwd text," +
                "imgURL_pr text)";

        db.execSQL(joinSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table joinTB");
            onCreate(db);
        }
    }
}
