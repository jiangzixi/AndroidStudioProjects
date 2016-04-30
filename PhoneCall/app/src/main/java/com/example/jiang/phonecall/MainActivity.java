package com.example.jiang.phonecall;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.button);
        //常用这种方法
        btn.setOnClickListener(this);
        //匿名函数实现
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callPhone(tf);
//            }
//        });
    }
        @Override
        public void onClick(View view){
        callPhone();
    }
        private void callPhone() {
            EditText tf = (EditText)findViewById(R.id.editText);
          if (tf.getText().length()==0){
                Toast.makeText(MainActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
          }else{
              Intent intent = new Intent();
              intent.setData(Uri.parse("tel:"+tf.getText().toString()));
              intent.setAction(Intent.ACTION_CALL);
              startActivity(intent);
        }
    }
}
