package com.example.jiangzixi.sourceviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setText("");
        //这里有bug 子线程无法刷新UI
        new Thread() {
            @Override
            public void run() {
                //获取源码属性
                EditText et = (EditText) findViewById(R.id.et);
                TextView tv = (TextView) findViewById(R.id.tv);
                String urlStr = et.getText().toString();
                //把网络访问的代码放在这里
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//GET POST
                    connection.setConnectTimeout(10);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        //获取服务器数据 服务器以流形式返回
                        InputStream stream = connection.getInputStream();
                        //把流转化为字符串
                        String str = StreamTools.readStream(stream);
                        tv.setText(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
