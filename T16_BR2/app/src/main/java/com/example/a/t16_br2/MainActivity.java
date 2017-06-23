package com.example.a.t16_br2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int level = intent.getIntExtra("level", 0);
                Toast.makeText(context, "battery : " + level, Toast.LENGTH_LONG).show();
            }else if(action.equals(Intent.ACTION_BATTERY_LOW)){
                //
            }else if(action.equals("abcde")){
                Toast.makeText(context, "custom action received", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View view){
        Intent intent = new Intent("abcde");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction("abcde");
        // receiver 에 넣기
        registerReceiver(receiver, filter);

    }


    @Override
    protected void onStop() {
        super.onStop();
        // receiver 에서 빼주기
        unregisterReceiver(receiver);
    }


}
