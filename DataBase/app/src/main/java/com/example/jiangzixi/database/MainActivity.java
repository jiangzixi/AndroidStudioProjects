package com.example.jiangzixi.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Dictionary;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button creatTable = (Button)findViewById(R.id.btn_createTable);
        creatTable.setOnClickListener(this);
        Button add = (Button)findViewById(R.id.btn_add);
        add.setOnClickListener(this);
        Button delete = (Button)findViewById(R.id.btn_delete);
        delete.setOnClickListener(this);
        Button update = (Button)findViewById(R.id.btn_update);
        update.setOnClickListener(this);
        Button check = (Button)findViewById(R.id.btn_check);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(getApplicationContext());
        //打开或者创建数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //打开或者创建数据库 磁盘满了返回一个只读数据库
        SQLiteDatabase readableDb = helper.getReadableDatabase();
        switch (view.getId()){
            case R.id.btn_createTable:{
                //自动建表,吊的不行
                break;
            }case R.id.btn_add:{
                //增
//                db.execSQL("insert into info(name,phone) values(?,?)",new Object[]{"张三","1388888"});
                //谷歌官方语句
                //key 列名 value 值
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","张三");
                contentValues.put("phone","1377777777");
                //返回值代表行ID
                long result = db.insert("info",null, contentValues);
                break;
            }case R.id.btn_delete:{
                //删
//                db.execSQL("delete from info where name=?",new Object[]{"张三"});
                //谷歌官方语句
                //返回值代表影响的行数
                int result = db.delete("info","name=",new String[]{"张三"});
                break;
            }case R.id.btn_update:{
                //改
//                db.execSQL("update info set phone=? where name=?",new Object[]{"1377777","张三"});
                //谷歌官方语句
                //返回值代表更新了多少行
                ContentValues contentValues = new ContentValues();
                contentValues.put("phone","110");
                int result = db.update("info",contentValues,"name=?",new String[]{"王五"});
                break;
            }case R.id.btn_check:{
                //查
                Cursor cursor = db.rawQuery("select * from info",null);
                //取出数据
                for (int i = 0; i < cursor.getCount(); i++) {
                    String name = cursor.getString(1);
                    cursor.moveToNext();
                }
                cursor.close();
                //谷歌官方语句
                //1表名,2null代表所有,
                //select phone from info where name=王五
                db.query("info",new String[]{"phone"},"name=?",new String[]{"王五"},null,null,null);
                break;
            }
        }
        db.close();
    }
}
