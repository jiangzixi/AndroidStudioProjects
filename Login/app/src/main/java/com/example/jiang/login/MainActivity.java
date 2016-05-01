package com.example.jiang.login;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button privateBtn,appendBtn,readBtn,writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //使用sp去保存数据，类似userdefault
        SharedPreferences sp = MainActivity.this.getSharedPreferences("config",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("str","String");
        edit.putBoolean("bool",true);
        edit.putInt("int",1);
        edit.putFloat("float", (float) 1.5);
        edit.commit();

        //文件权限
        privateBtn = (Button)findViewById(R.id.btn_private);
        privateBtn.setOnClickListener(this);
        appendBtn = (Button)findViewById(R.id.btn_append);
        appendBtn.setOnClickListener(this);
        readBtn = (Button)findViewById(R.id.btn_read);
        readBtn.setOnClickListener(this);
        writeBtn = (Button)findViewById(R.id.btn_write);
        writeBtn.setOnClickListener(this);

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
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            //SD卡已经挂载
                            //SD卡可用空间
                            File file = Environment.getExternalStorageDirectory();
                            //获取所有空间
                            long totalSpace = file.getTotalSpace();
                            //获取可用空间
                            long freeSpace = file.getFreeSpace();
                            String totalStr = Formatter.formatFileSize(MainActivity.this,totalSpace);
                            String freeStr = Formatter.formatFileSize(MainActivity.this,freeSpace);
                            System.out.println("总空间"+totalStr+"可用空间"+freeStr);
                        }


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_private:{
                try {
                    FileOutputStream fos = openFileOutput("private.txt",MODE_PRIVATE);
                    fos.write("private.txt".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_append:{
                try {
                    FileOutputStream fos = openFileOutput("append.txt",MODE_APPEND);
                    fos.write("append.txt".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_read:{
                try {
                    FileOutputStream fos = openFileOutput("read.txt",MODE_WORLD_READABLE);
                    fos.write("read.txt".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_write:{
                try {
                    FileOutputStream fos = openFileOutput("write.txt",MODE_WORLD_WRITEABLE);
                    fos.write("write.txt".getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
