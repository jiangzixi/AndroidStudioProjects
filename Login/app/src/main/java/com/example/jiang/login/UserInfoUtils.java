package com.example.jiang.login;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang on 2016/5/1.
 */
public class UserInfoUtils {

    //保存用户名和密码
    public  static boolean saveInfo(Context context, String username, String pwd){
        try {
//            String path = context.getFilesDir().getPath();
            String info = username+"#####"+pwd;

            //把数据保存到/data/包名下
            //指定文件存储位置
//
            //把数据保存到SD卡
            String path = Environment.getExternalStorageDirectory().getPath();
//            File file = new File("/data/data/com.example.jiang.login/info.txt");
            File file = new File(path,"info.txt");
            //创建文件输出流
//            FileOutputStream fos = new FileOutputStream(file);
            //通过上下文获取输出流
            FileOutputStream fos = context.openFileOutput("info.txt",0);
            fos.write(info.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //读取用户名密码你信息
    public  static Map<String,String> readInfo(Context context){
        //定义Map
        try {
            Map<String,String > maps = new HashMap<String ,String >();
//            File file = new File("/data/data/com.example.jiang.login/info.txt");
            //通过上下文获取路径
//            File file = new File(context.getFilesDir().getPath(),"info.txt");
//            FileInputStream fis = new FileInputStream(file);
            //通过上下文获取输入流
            FileInputStream fis = context.openFileInput("info.txt");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String content = buffer.readLine();//读取数据
            fis.close();
            //切割字符串
            String[] sp = content.split("#####");
            String userName = sp[0];
            String pwd = sp[1];
            //把用户名密码放入Map中
            maps.put("name",userName);
            maps.put("pwd",pwd);
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
