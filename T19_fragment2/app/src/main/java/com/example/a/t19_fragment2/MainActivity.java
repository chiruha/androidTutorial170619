package com.example.a.t19_fragment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onBtnClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame);
            switch (view.getId()){
                case R.id.btnAdd:
                    if(fragment == null){
                        fragment = new BlankFragment();
                        FragmentTransaction tr = fm.beginTransaction();
                        tr.add(R.id.frame, fragment, "counter");
                        // 뒤로 가기 버튼 눌렀을 때 종료 대신 뒷 화면 보여주기
                        tr.addToBackStack(null);
                        tr.commit();

                    }

                    break;
                case R.id.btnRemove:
                    if(fragment != null){
                        FragmentTransaction tr = fm.beginTransaction();
                        tr.remove(fragment);
                        tr.commit();
                        // 백스텍에서 빼기
                        fm.popBackStack();
                    }
                    break;
                case R.id.btnReplace:
                    if(fragment != null){
                        FragmentTransaction tr = fm.beginTransaction();
                        //바꾸고 싶은 걸 먼저 쓴다
                        if(fragment.getTag().equals("counter")){
                            BlankFragment2 f2 = new BlankFragment2();
                            tr.replace(R.id.frame, f2, "text");
                        }else if(fragment.getTag().equals("text")){
                            BlankFragment f1 = new BlankFragment();
                            tr.replace(R.id.frame, f1, "counter");
                        }
                        // 방금 동작을 백 스텍에 저장
                        tr.addToBackStack(null)
;                        tr.commit();
                    }
                    break;
                case R.id.btnHide:
                    if(fragment != null){
                        FragmentTransaction tr = fm.beginTransaction();
                        if(fragment.isHidden()){
                            tr.show(fragment);
                        }else{
                            tr.hide(fragment);
                        }
                        tr.commit();

                    }
                    break;
            }
    }
}
