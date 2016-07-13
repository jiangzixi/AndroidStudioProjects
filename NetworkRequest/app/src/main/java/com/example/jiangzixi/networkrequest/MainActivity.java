package com.example.jiangzixi.networkrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String name = "name";
    String pwd = "pwd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRequest();
        postRequest();
    }


    protected void getRequest(){
        String urlPath = "http://www.baidu.com?name="+name+"&pwd="+pwd;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode() == 200) {
                Log.d("jiang","天真!瞎写都能GET成功!");
                InputStream stream = connection.getInputStream();
            }
        } catch (Exception ignored) {

        }
    }
    protected void postRequest(){
        String urlPath = "http://www.baidu.com/jiang";
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            //请求数据
            String data = "name="+name+"&pwd="+pwd;
            //请求头
            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-length", String.valueOf(data.length()));
            //把数据提交给服务器, 以流的形式
            connection.setDoOutput(true);
            connection.getOutputStream().write(data.getBytes());
            if (connection.getResponseCode() == 200) {
                Log.d("jiang","天真!瞎写都能POST成功!");
                InputStream stream = connection.getInputStream();
            }
        } catch (Exception ignored) {

        }
    }

}
