package com.example.jiangzixi.sourceviewer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangzixi on 16/5/4.
 */
public class StreamTools {
    //把InputStream转化为String
    public static String readStream(InputStream stream) throws IOException {
        //定义一个内存输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];//1k
        while ((len=stream.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        stream.close();
        String str = new String(baos.toByteArray());
        //toString都可以
        return str;
    }

}
