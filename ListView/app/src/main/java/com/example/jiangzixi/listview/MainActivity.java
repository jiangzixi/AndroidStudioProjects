package com.example.jiangzixi.listview;

import android.database.DataSetObserver;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tableview
        ListView lv = (ListView)findViewById(R.id.lv);
        //显示数据 和其他普通控件有点区别 数据来源于数据适配器
        lv.setAdapter(new MyListAdapter());
    }

    private class MyListAdapter extends BaseAdapter{

        //行数numberOfRowsInSection
        @Override
        public int getCount() {
            return 66;
        }

        //返回指定position位置对应的对象
        @Override
        public Object getItem(int i) {
            return null;
        }

        //返回position位置对应的id
        @Override
        public long getItemId(int i) {
            return 0;
        }

        //获取一个view 用来显示listview的数据 会作为listview的一个条目出现
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //view参数是之前return的view
//            TextView tv;
//            if (view!=null){
//                tv = (TextView)view;
//            }else {
//                tv = new TextView(MainActivity.this);
//            }
//            tv.setText(new String(""+i));
            //把自定义布局转换成一个view对象
            View newView;
            if (view==null){
                newView = View.inflate(getApplicationContext(),R.layout.layout,null);
            }else {
                newView = view;
            }

            return newView;
        }
    }
}
