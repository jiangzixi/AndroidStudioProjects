package com.example.jiang.createxml;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Sms> smsList = new ArrayList<Sms>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button)findViewById(R.id.btn_xml1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)findViewById(R.id.btn_xml2);
        btn2.setOnClickListener(this);
        for (int i = 0;i < 10; i++){
            Sms sms = new Sms();
            sms.setAddress("10008"+i);
            sms.setBody("nihao"+i);
            sms.setDate("201"+i);
            smsList.add(sms);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_xml1:{
                //创建sb对象
                StringBuffer sb = new StringBuffer();
                //开始组拼xml文件头
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                //开始组拼xml根节点
                sb.append("<smss>");
                for (Sms sms:smsList){
                    sb.append("<sms>");

                    //开始组拼address节点
                    sb.append("<address>");
                    sb.append(sms.getAddress());
                    sb.append("</address>");
                    //开始组拼body
                    sb.append("<body>");
                    sb.append(sms.getBody());
                    sb.append("</body>");
                    //开始组拼date
                    sb.append("<date>");
                    sb.append(sms.getDate());
                    sb.append("</date>");

                    sb.append("</sms>");
                }
                sb.append("</smss>");
                //把数据保存到sd卡中
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath(),"smsbackup1.xml");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(sb.toString().getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }case R.id.btn_xml2:{
                XmlSerializer xmler = Xml.newSerializer();
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath(),"smsbackup2.xml");
                    FileOutputStream fos = new FileOutputStream(file);
                    xmler.setOutput(fos,"utf-8");
                    //头结点
                    xmler.startDocument("utf8",true);
                    //xml根节点 namespace 命名空间
                    xmler.startTag(null,"smss");
                    for (Sms sms:smsList){
                        xmler.startTag(null,"sms");
                        //节点
                        xmler.startTag(null,"address");
                        xmler.text(sms.getAddress());
                        xmler.endTag(null,"address");
                        xmler.startTag(null,"body");
                        xmler.text(sms.getBody());
                        xmler.endTag(null,"body");
                        xmler.startTag(null,"date");
                        xmler.text(sms.getDate());
                        xmler.endTag(null,"date");
                        xmler.endTag(null,"sms");
                    }
                    //xml尾节点
                    xmler.endTag(null,"smss");
                    //尾节点
                    xmler.endDocument();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

