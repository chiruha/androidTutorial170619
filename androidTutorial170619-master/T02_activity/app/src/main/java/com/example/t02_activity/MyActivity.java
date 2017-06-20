package com.example.t02_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        String value = intent.getStringExtra("myValue");
        Toast.makeText(this, "val : "+value, Toast.LENGTH_SHORT).show();

        intent.getExtras().getInt("secondValue");
    }
}
