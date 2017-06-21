package com.example.a.t09_sharedpreperences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pr = getSharedPreferences("setting",0);
        pr.getString("name","default");

        Toast.makeText(this,"value"+name, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences pr = getSharedPreferences("setting",0);

        // 바로 SharedPreferences 에 put 하지 않고 Editor 사용
        SharedPreferences.Editor editor = pr.edit();
        editor.putString("name","my test");
        editor.commit();
    }
}
