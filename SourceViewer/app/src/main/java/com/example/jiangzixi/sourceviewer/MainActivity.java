package com.example.jiangzixi.sourceviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //在主线程中定义一个Handler
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    ImageView iv = (ImageView) findViewById(R.id.iv);
                    iv.setImageBitmap(null);
                    TextView tv = (TextView) findViewById(R.id.tv);
                    tv.setText(msg.obj.toString());
                    break;
                }
                case 1: {
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
                }
                case 2: {
                    TextView tv = (TextView) findViewById(R.id.tv);
                    tv.setText(null);
                    Bitmap bitmap = (Bitmap) msg.obj;
                    ImageView iv = (ImageView) findViewById(R.id.iv);
                    iv.setImageBitmap(bitmap);
                    break;
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Button btn_sleep = (Button) findViewById(R.id.btn_sleep);
        btn_sleep.setOnClickListener(this);
        Button btn_iv = (Button) findViewById(R.id.btn_image);
        btn_iv.setOnClickListener(this);

        //打印当前线程名字
        System.out.println("当前线程名字" + Thread.currentThread().getName());
    }

    @Override
    public void onClick(View v) {
//        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setText("");
        switch (v.getId()) {
            case R.id.btn: {
                //这里有bug 子线程无法刷新UI
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //获取源码属性
                        EditText et = (EditText) findViewById(R.id.et);
                        String urlStr = et.getText().toString();
                        //把网络访问的代码放在这里
                        try {
                            URL url = new URL(urlStr);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");//GET POST
                            connection.setConnectTimeout(10000);
                            int responseCode = connection.getResponseCode();
                            if (responseCode == 200) {
                                //获取服务器数据 服务器以流形式返回
                                InputStream stream = connection.getInputStream();
                                //把流转化为字符串
                                final String str = StreamTools.readStream(stream);
                                //更新UI
//                                Message msg = Message.obtain();
//                                msg.what = 0;
//                                msg.obj = str;
//                                handler.sendMessage(msg);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ImageView iv = (ImageView) findViewById(R.id.iv);
                                        iv.setImageBitmap(null);
                                        TextView tv = (TextView) findViewById(R.id.tv);
                                        tv.setText(str);
                                    }
                                });
                            } else {
                                //请求资源不存在
                                Message msg = Message.obtain();
                                msg.what = 1;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            }
            case R.id.btn_sleep: {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_image: {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        EditText et = (EditText) findViewById(R.id.et);
                        String urlStr = et.getText().toString();
                        try {
                            File file = new File(getCacheDir(), Base64.encodeToString(urlStr.getBytes(), Base64.DEFAULT));
                            if (!(file.exists() && file.length() > 0)) {
                                URL url = new URL(urlStr);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");
                                connection.setConnectTimeout(5000);
                                int code = connection.getResponseCode();
                                if (code == 200) {
                                    InputStream in = connection.getInputStream();
                                    //缓存图片 谷歌给了我们一个缓存目录
                                    FileOutputStream out = new FileOutputStream(file);
                                    int len = -1;
                                    byte[] buffer = new byte[1024];//1k
                                    while ((len = in.read(buffer)) != -1) {
                                        out.write(buffer, 0, len);
                                    }
                                    out.close();
                                    in.close();
                                    //位图bitmap
//                                   Bitmap bitmap = BitmapFactory.decodeStream(in);
                                }
                            }
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            Message msg = Message.obtain();
                            msg.what = 2;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            }
        }
    }
}
