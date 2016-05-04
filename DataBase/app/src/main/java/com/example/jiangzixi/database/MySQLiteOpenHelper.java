package com.example.jiangzixi.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;

/**
 * Created by jiangzixi on 16/5/3.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public MySQLiteOpenHelper(Context context) {
        //最后一个为数据库版本号
        super(context, "jiang.db", null, 2);
    }
    //当第一次创建数据库时调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table info(_id integer primary key autoincrement,name varchar(20))");
    }
    //当数据库升级的时候调用,适合做表结构的更新
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("alter table info add phone varchar(20)");
    }
}
