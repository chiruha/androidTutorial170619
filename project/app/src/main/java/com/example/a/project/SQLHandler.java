package com.example.a.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by a on 2017-06-22.
 */

public class SQLHandler {
    SQLOpenHelper helper;
    public SQLHandler(Context context){
       helper = new SQLOpenHelper(context, "mydb",null,1);
    }
    public void insert( int day,int hour, float temp, String wfKor){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hour", hour);
        values.put("day", day);
        values.put("temp", temp);
        values.put("wfKor", wfKor);
        db.insert("weather", null, values);
    }

    public void deleteAll(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM WEATHER");
    }

   /* public void update(String name, int newAge){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", newAge);
        db.update("weather", values, "name = ?", new String[]{name});
    }*/

    public String[] selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
//        String sql = "select * from student";
//        Cursor c1 = db.rawQuery(sql, null);
        Cursor c = db.query("weather",null, null, null, null, null, null);
        String[] str = new String[c.getCount()];
        while(c.moveToNext()){
            int id = c.getInt( c.getColumnIndex("id"));
            int hour = c.getInt( c.getColumnIndex("hour"));
            int day = c.getInt( c.getColumnIndex("day"));
            Float temp = c.getFloat( c.getColumnIndex("temp"));
            String wfKor = c.getString(c.getColumnIndex("wfKor"));
            str[c.getPosition()] = "id: "+id+" day: "+day+" hour: "+hour+" temp: "+temp+"wfKor : "+wfKor;
            Log.d("커서 위치 : ",Integer.toString(c.getPosition()));
        }

        return str;
    }

}