package com.example.a.t10_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2017-06-21.
 */

public class TestSQLOpenHelper extends SQLiteOpenHelper{
    public TestSQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 설치시 한 번만 호출됨
        String sql = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, age INTEGER, address TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 업그레이드 시 사용되는 부분
        String sql = "DROP TABLE IF EXIST student;";
        db.execSQL(sql);
        onCreate(db);

    }
}
