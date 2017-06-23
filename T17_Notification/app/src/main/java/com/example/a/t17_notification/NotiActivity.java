package com.example.a.t17_notification;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NotiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 클릭시 사라지게 만들기
        manager.cancel(1234);
    }



}
