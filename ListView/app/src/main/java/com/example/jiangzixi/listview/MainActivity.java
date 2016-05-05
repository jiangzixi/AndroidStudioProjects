package com.example.jiangzixi.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnArr = (Button)findViewById(R.id.btn_arrAdapter);
        btnArr.setOnClickListener(this);
        Button btnSimple = (Button)findViewById(R.id.btn_simple);
        btnSimple.setOnClickListener(this);
        Button btnQZ = (Button)findViewById(R.id.btn_qz);
        btnQZ.setOnClickListener(this);
        //tableview
        ListView lv = (ListView)findViewById(R.id.lv);
        //显示数据 和其他普通控件有点区别 数据来源于数据适配器
        lv.setAdapter(new MyListAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_arrAdapter:{
                ArrAdapterVC vc = new ArrAdapterVC();
                Intent intent = new Intent(MainActivity.this,vc.getClass());
                startActivity(intent);
                break;
            }case R.id.btn_simple:{
                simpleVC vc = new simpleVC();
                Intent intent = new Intent(MainActivity.this,vc.getClass());
                startActivity(intent);
                break;
            }case R.id.btn_qz:{
                quanzhongVC qz = new quanzhongVC();
                Intent intent = new Intent(MainActivity.this,qz.getClass());
                startActivity(intent);
                break;
            }
        }
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
                //获取打气筒服务
                //1  简单
//                newView = View.inflate(getApplicationContext(),R.layout.layout,null);
                //2
//                newView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout,null);
                //3  使用频率相对高  谷歌内部使用
                LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                newView = inflater.inflate(R.layout.layout,null);
            }else {
                newView = view;
            }
            return newView;
        }
    }
}
