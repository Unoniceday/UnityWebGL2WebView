package com.uno.unitytowebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webview = (WebView) findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);// 設定此 WebView 支援 Javascript
        webview.setWebViewClient(new WebViewClient());//不調用系統瀏覽器


        webview.addJavascriptInterface(new JSBridge(), "android"); //第一個參數是javascript對應java的物件，第二個則是js中要呼叫該物件的方法就是通過這個來呼叫，也就是對應一開始的寫的jslib內的android.ShowString(Pointer_stringify(str));的android對象
        webview.loadUrl("https://cheesegames.net/games/2747/index.php?gameDataId=2747");
    }

    public class JSBridge{
        @JavascriptInterface
        //從Unity端可以呼叫到的function，ShowString對應一開始的jslib內的android.ShowString(Pointer_stringify(str));
        public void ShowString(String message) {
            Toast.makeText(getApplicationContext(), "通過Natvie傳遞的Toast:"+message, Toast.LENGTH_LONG).show();
        }
    }
}
