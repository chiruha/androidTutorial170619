package com.example.a.t14_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by a on 2017-06-22.
 */

public class MyBindService extends Service {
    public MyBindService() {
    }
    public class MyBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }
    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    Random random = new Random();
    public int getRandomNumber(){
        return random.nextInt();
    }

}
