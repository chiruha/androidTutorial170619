package com.example.a.t03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] list = { "Hello", "World", "Oracle", "Java",
            "Hello1", "World1", "Oracle1", "Java1",
            "Hello2", "World2", "Oracle2", "Java2",
            "Hello3", "World3", "Oracle3", "Java3"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        // 선언시 데이터 타입을 제너릭으로 선언해야 한다
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = list[position];
                Toast.makeText(MainActivity.this, "str : "+str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
