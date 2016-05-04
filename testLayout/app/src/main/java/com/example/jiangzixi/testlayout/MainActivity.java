package com.example.jiangzixi.testlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView left = (ImageView)findViewById(R.id.iv_left);
        left.setImageResource(R.mipmap.and);
        ImageView right = (ImageView)findViewById(R.id.iv_right);
        right.setImageResource(R.mipmap.and);
    }
}
