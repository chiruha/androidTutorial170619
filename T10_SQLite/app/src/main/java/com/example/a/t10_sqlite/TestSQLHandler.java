package com.example.a.t10_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a on 2017-06-21.
 */

public class TestSQLHandler {
    TestSQLOpenHelper helper ;

    public TestSQLHandler(Context context){
        helper = new TestSQLOpenHelper(context, "people", null, 1);
        // people 은 DB name
    }

    public void insert(String name, int age, String address){
        SQLiteDatabase db = helper.getWritableDatabase();
        // ContentValues - key , value 로 값 저장
        ContentValues values = new ContentValues();
        // values.put("필드(컬럼)이름", 값);
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);
        //db.insert("테일블 이름",null,values);
        db.insert("student",null,values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        // delete(String table, String whereClause, String[] whereArgs
        // String[] whereArgs - 스트링 배열로 만들어서 넣어야 한다
        db.delete("student","name = ?", new String[]{name});
    }

    public void update(String name, int newAge){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age",newAge);
        db.update("student",values, "name = ?", new String[]{name});
    }

    public String selectAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String str = "";

        // 방법1 (조건이 거의 없다면)
        /*String sql = "SELECT * FROM student";
        Cursor c1 = db.rawQuery(sql,null);*/

        // 방법2
        Cursor c = db.query("student",null,null,null,null,null,null,null);
        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));

            str +="id:"+id+" name:"+name+" age: "+age+" address:"+address+"\n";
        }
        return str;
    }
}
