package com.example.a.t08_menu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        // 컨텍스트 메뉴 띄우기
        registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        menu.add(0,100,0,"hello");
        menu.add(0,101,0,"world");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    listFiles();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    textView.setText("READ EXTERNAL STORAGE DENIED!!");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    // 시스템에 요청?
    static final int MY_PERMISSIONS_REQUEST = 100;
    private boolean checkPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            // 이전에 한 번이라도 권한 허용한 경우
            return true;
        }else{
            // 권한 요청하는데 물어 볼 수 없는 경우
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // 권한 요청하는데 물어 볼 수 있는 경우
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false;
        }
    }


    private void listFiles(){
        File root = Environment.getExternalStorageDirectory();
        File[] files = root.listFiles();
        String str  = "";
        for(int i=0; i<files.length; i++){
            str += i+": "+files[i].getName()+"\n";
        }
        Toast.makeText(this, "file size : "+files.length, Toast.LENGTH_SHORT).show();
        textView.setText(str);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_test1) {
            Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_test2){
            if(checkPermission())
               listFiles();
        }
        return true;
    }
    // 컨텍스트 메뉴
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId() == 100){
            Toast.makeText(this, "context1", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "context2", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}

