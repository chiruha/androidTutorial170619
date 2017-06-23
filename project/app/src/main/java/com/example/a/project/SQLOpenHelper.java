package com.example.a.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2017-06-22.
 */

public class SQLOpenHelper extends SQLiteOpenHelper {
    public SQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE weather (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "hour INTEGER, day INTEGER, temp INTEGER, wfKor TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST weather;";
        db.execSQL(sql);

        onCreate(db);
    }

}
