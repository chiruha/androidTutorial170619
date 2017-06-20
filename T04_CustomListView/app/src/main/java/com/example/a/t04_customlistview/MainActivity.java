package com.example.a.t04_customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        String title;
        String desc;
        int imgIcon;

        public MyData(String title, String desc, int imgIcon) {
            this.title = title;
            this.desc = desc;
            this.imgIcon = imgIcon;
        }
    }

    ArrayList<MyData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<30; i++){
            int icon = R.mipmap.ic_launcher;
            if(i%2 ==0){
                icon = R.mipmap.ic_launcher_round;
            }
            MyData data = new MyData("title"+i, "desc"+i,icon);
            list.add(data);
        }

    }
}
