package com.example.jiangzixi.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrAdapterVC extends AppCompatActivity {

    String objects[] = {"老张","老方","老黎","老毕","老刘","老韩"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arr_adapter_vc);
        ListView lv = (ListView)findViewById(R.id.lv_arr);
        //单显示文字
        lv.setAdapter(new ArrayAdapter<String>(this,R.layout.layoutarr,R.id.textView,objects));
    }


}
