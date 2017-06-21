package com.example.a.p01_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by a on 2017-06-20.
 */

class MyAdapter extends BaseAdapter{
    Context context;
    int resItemView;
    ArrayList<MyData> list;

    public MyAdapter(Context context, int resItemView, ArrayList<MyData> list) {
        this.context = context;
        this.resItemView = resItemView;
        this.list = list;
    }

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
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resItemView, null);
            //convertView =  getLayoutInflater().inflate(resItemView, null);
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
