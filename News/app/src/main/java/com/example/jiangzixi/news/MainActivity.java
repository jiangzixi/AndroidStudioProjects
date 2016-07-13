package com.example.jiangzixi.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {
    ListView lv = (ListView) findViewById(R.id.lv);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        http://api.51vsun.com/weixun/admin.php/interfaceNew/mall_index

    }
}
