package com.example.a.t02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 어디서 오는 intetn 인지 알기 위해 만든 상수
    private static final int myRequest = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
        // activitiy 간 데이터 전달은 intent 를 통해 전달된다
        Intent intent = new Intent(MainActivity.this, MyActivity.class);
        intent.putExtra("myValue", "hello world");
        intent.putExtra("secondValue", 222);

        // 받을 것 없이 호출할 때 사용
        //startActivity(intent);

        // 뭔가를 받을 때 사용
        startActivityForResult(intent, myRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // int resultCode, Intent data은 MyActivity에서 설정한 setResult(RESULT_OK, intent); 가 넘어온다
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == myRequest){
            if(resultCode == RESULT_OK){
                String res = data.getStringExtra("myResult");
                Toast.makeText(this, "res"+res, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
