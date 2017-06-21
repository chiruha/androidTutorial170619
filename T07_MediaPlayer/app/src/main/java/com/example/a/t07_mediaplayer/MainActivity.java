package com.example.a.t07_mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);

                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //익명 클래스
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(100); //0.1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(mp != null){
                        seekBar.setProgress(mp.getCurrentPosition());

                    }
                }
            }
        }).start();

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 마쉬멜로 이후는 애플리케이션 관리자에서 런타임 권한 허용을 해야 실행된다
                // 아니면 코드상에서 미리 설정한다 manifest 에
                //<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
                //https://developer.android.com/training/permissions/requesting.html
                String path = Environment.getExternalStorageDirectory()
                        +"/Download/testMusic.mp3";
                mp = new MediaPlayer();
                try{
                    Log.d("경로",path);
                    mp.setDataSource(path);
                    mp.prepare();
                    mp.start();
                    seekBar.setMax(mp.getDuration());
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null) {
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            }
        });

    }
}
