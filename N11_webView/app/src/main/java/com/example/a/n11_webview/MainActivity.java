package com.example.a.n11_webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView ;
    ProgressDialog dlg;
    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            dlg.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dlg.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlg = new ProgressDialog(this);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://www.naver.com");

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editAddress);
                String address = editText.getText().toString();
                webView.loadUrl(address);
                WebSettings ws = webView.getSettings();  // web 설정 가져오기
                ws.setJavaScriptEnabled(true);  // 기본적으로 false

                // 기본적으로 back 버튼은 앱 종료 기능을 가진다
                // back 버튼으로 이전 페이지를 가기 위해서는 오버라이딩으로 기능 추가해야 함
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            // 종료의 기능을 가지고 있으므로 마지막으로 보내기
            super.onBackPressed();
        }

    }
}
