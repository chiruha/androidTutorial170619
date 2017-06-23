package com.example.a.project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    ListView listView;
    SQLHandler dbHandler;
    ArrayList<WeatherData> list = new ArrayList<>();

    MyAdater myAdater;

    class WeatherData {
        int day;
        int hour;
        float temp;
        String wfKor;

        @Override
        public String toString() {
            return " day : "+day+" hour : "+hour+" temp : "+temp+" wfKor : "+wfKor;
        }
    }

    class MyPullParser extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //String[] str = new String[list.size()];
            for(int i=0; i<list.size(); i++){
                //str[i] = list.get(i).toString();
                dbHandler.insert(list.get(i).day,list.get(i).hour,list.get(i).temp,list.get(i).wfKor);

            }

            //textView.setText(str);
            String[] str = dbHandler.selectAll();
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(MainActivity2.this,
                    android.R.layout.simple_list_item_1,str);
            listView.setAdapter(listAdapter);
        }

        @Override
        protected String doInBackground(String... params) {
            String str = "";
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(), "utf-8");

                int eventType = xpp.getEventType();
                boolean bHour= false,bDay= false,bTemp= false,bWfKor = false;
                WeatherData data = null;
                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            if(tag.equals("data")){
                                data = new WeatherData();
                                list.add(data);
                            }else if(tag.equals("hour")){
                                bHour = true;
                            }else if(tag.equals("day")){
                                bDay = true;
                            }else if(tag.equals("temp")){
                                bTemp = true;
                            }else if(tag.equals("wfKor")){
                                bWfKor = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(bHour){
                                data.hour = Integer.parseInt(xpp.getText());
                                bHour = false;
                            }
                            if(bDay){
                                data.day = Integer.parseInt(xpp.getText());
                                bDay = false;
                            }
                            if(bTemp){
                                data.temp = Float.parseFloat(xpp.getText());
                                bTemp = false;
                            }
                            if(bWfKor){
                                data.wfKor = xpp.getText();
                                bWfKor = false;
                            }
                            break;
                    }

                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return str;
        }
    }

    class MyAdater extends BaseAdapter{

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
                convertView = getLayoutInflater().inflate(R.layout.icon_layout, null);
            }
            WeatherData data = list.get(position);
            TextView textView1 = (TextView) convertView.findViewById(R.id.weatherText1);
            TextView textView2 = (TextView) convertView.findViewById(R.id.weatherText2);

            Date date = new Date();
            date.setHours(data.hour);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 HH시");
            textView2.setText(sdf.format(date));
            return convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        dbHandler = new SQLHandler(this);

        myAdater = new MyAdater();
        listView.setAdapter(myAdater);

        //ImageView icon = (ImageView)

    }

    public void onBtnClick(View view){
        MyPullParser parser = new MyPullParser();
        parser.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");
    }
}
