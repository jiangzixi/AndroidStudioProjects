package com.example.jiangzixi.handlerapi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    TimerTask task;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("helloWorld");
                System.out.println("延时执行5s");
            }
        },5000);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                //子线程,更新UI会挂掉
//                textView.setText("goodByeWorld");
                System.out.println("定时器3s,后1s执行");
            }
        };
        timer.schedule(task,3000,1000);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        task.cancel();
        super.onDestroy();
    }
}
