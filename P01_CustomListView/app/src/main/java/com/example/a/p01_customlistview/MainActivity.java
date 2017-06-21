package com.example.a.p01_customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MyData> list = new ArrayList<>();

        MyData data1 = new MyData("피자1","12,000 원",R.drawable.menu_img_1);
        list.add(data1);
        MyData data2 = new MyData("피자2","13,000 원",R.drawable.menu_img_2);
        list.add(data2);
        MyData data3 = new MyData("피자3","14,000 원",R.drawable.menu_img_3);
        list.add(data3);
        MyData data4 = new MyData("피자4","15,000 원",R.drawable.menu_img_4);
        list.add(data4);
        MyData data5 = new MyData("피자5","16,000 원",R.drawable.menu_img_5);
        list.add(data5);
        MyData data6 = new MyData("피자6","12,000 원",R.drawable.menu_img_6);
        list.add(data6);
        MyData data7 = new MyData("피자7","13,000 원",R.drawable.menu_img_7);
        list.add(data7);
        MyData data8 = new MyData("피자8","14,000 원",R.drawable.menu_img_8);
        list.add(data8);
        MyData data9 = new MyData("피자9","15,000 원",R.drawable.menu_img_9);
        list.add(data9);
        MyData data10 = new MyData("피자10","16,000 원",R.drawable.menu_img_10);
        list.add(data10);
        MyData data11 = new MyData("피자11","12,000 원",R.drawable.menu_img_11);
        list.add(data11);
        MyData data12 = new MyData("피자12","13,000 원",R.drawable.menu_img_12);
        list.add(data12);
        MyData data13 = new MyData("피자13","14,000 원",R.drawable.menu_img_13);
        list.add(data13);
        MyData data14 = new MyData("피자14","15,000 원",R.drawable.menu_img_14);
        list.add(data14);
        MyData data15 = new MyData("피자15","16,000 원",R.drawable.menu_img_15);
        list.add(data15);



        ListView listView = (ListView) findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(MainActivity.this,R.layout.item_row,list);
        listView.setAdapter(adapter);

    }
}
