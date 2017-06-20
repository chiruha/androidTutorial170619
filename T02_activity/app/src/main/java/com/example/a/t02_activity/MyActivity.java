package com.example.a.t02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String value = intent.getStringExtra("myValue");
        Toast.makeText(this, "val : "+value, Toast.LENGTH_SHORT).show();
        intent.getExtras().getInt("secondValue");
        setContentView(R.layout.activity_my);
    }

    public void onBackPressed(){
        // 뒤로가기 버튼 눌렸을 때
        super.onBackPressed();

        Intent intent = new Intent();
        intent.putExtra("myResult", "hello result");
        setResult(RESULT_OK, intent);


    }
}
