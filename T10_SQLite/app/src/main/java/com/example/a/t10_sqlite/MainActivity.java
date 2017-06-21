package com.example.a.t10_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestSQLHandler dbHandler = new TestSQLHandler(this);
        dbHandler.insert("kim",20,"seoul");
        dbHandler.insert("lee",21,"부산");
        dbHandler.update("kim",22);

        String str = dbHandler.selectAll();
        TextView textView = (TextView)  findViewById(R.id.textView);
        textView.setText(str);
    }
}
