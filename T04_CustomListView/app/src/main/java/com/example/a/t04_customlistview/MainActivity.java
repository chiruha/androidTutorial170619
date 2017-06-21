package com.example.a.t04_customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView =  getLayoutInflater().inflate(R.layout.item_row, null);
            }
            ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.item_title);
            TextView descTextView = (TextView) convertView.findViewById(R.id.item_desc);

            MyData data = list.get(position);

            icon.setImageResource(data.imgIcon);
            titleTextView.setText(data.title);
            descTextView.setText(data.desc);

            return convertView;
        }
    }

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

        ListView listView = (ListView) findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

}
