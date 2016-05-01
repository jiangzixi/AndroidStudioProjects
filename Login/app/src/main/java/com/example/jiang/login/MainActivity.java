package com.example.jiang.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userName = (EditText)findViewById(R.id.et_userName);
        final EditText passWord = (EditText)findViewById(R.id.et_password);
        final CheckBox checkBox = (CheckBox)findViewById(R.id.cb_remember);

        //设置用户保存数据
        Map<String ,String > maps = UserInfoUtils.readInfo(MainActivity.this);
        if (maps!=null){
            userName.setText(maps.get("name"));
            passWord.setText(maps.get("pwd"));
            checkBox.setChecked(true);
        }
        Button login = (Button)findViewById(R.id.btn);
        //登陆按钮操作
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userName.getText().toString().trim();
                String pwStr = passWord.getText().toString().trim();
                //判断账号密码是否为空
                if (TextUtils.isEmpty(userNameStr) || TextUtils.isEmpty(pwStr)){
                    Toast.makeText(MainActivity.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                }else {
                    if (checkBox.isChecked()){
                        //记住密码并提示
                        if (UserInfoUtils.saveInfo(MainActivity.this,userNameStr,pwStr)){
                            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //开始登陆
                }
            }
        });

    }
}
