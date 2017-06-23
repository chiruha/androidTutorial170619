package com.example.a.t12_xml;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    class MyPullParser extends AsyncTask<String, Void, String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String str= "";
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = factory.newPullParser();
                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(), "utf-8");

                int eventType = xpp.getEventType();
                boolean bRead = false;
                while (eventType != XmlPullParser.END_DOCUMENT);
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            String tag = xpp.getName();
                            if(tag.equals("hour") || tag.equals("day")
                                    || tag.equals("temp") || tag.equals("wfKor")){
                                bRead = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(bRead){
                                str += xpp.getText()+" ";
                                bRead = false;
                            }
                            break;
                    }
                    eventType = xpp.next();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void onBtnClick(View view){
        MyPullParser parser = new MyPullParser();
        parser.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153052000");
    }
}
