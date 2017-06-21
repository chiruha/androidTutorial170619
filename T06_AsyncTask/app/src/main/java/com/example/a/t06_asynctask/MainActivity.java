package com.example.a.t06_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnStart;
    ProgressBar progressBar;

    class MyTask extends AsyncTask<Integer, Float, String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            int b = (int)Math.round(values[0]);
            textView.setText("count : "+b);
            progressBar.setProgress(b);

        }
        @Override
        protected String doInBackground(Integer... params) {
            int value = params[0];  //배열
            for(int i=value; i<100; i++){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                publishProgress((float)i);
            }
            return "done task";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute(20);
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        MyTask task = new MyTask();
        task.execute(20);








    }

}
