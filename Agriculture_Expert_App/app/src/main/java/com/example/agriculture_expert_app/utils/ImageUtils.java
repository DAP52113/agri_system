package com.example.agriculture_expert_app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//图片处理工具类
public class ImageUtils {
    //图片变为转换为字符串数据
    public static String imageToBase64(Bitmap bitmap){
        //输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        String baseStr = Base64.encodeToString(buffer,Base64.DEFAULT);
        return baseStr;
    }

    //字符串数据转换为图片
    public static Bitmap Base64ToImage(String bitmap64){
        //decode解析函数
        byte[] bytes = Base64.decode(bitmap64,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
        return bitmap;
    }

    //网络图片转换为bitmap
    public static Bitmap getImage(String path){
       Bitmap bitmap = null;
       try{
           URL url = new URL(path);
           URLConnection urlConnection = url.openConnection();//打开链接
           InputStream stream = urlConnection.getInputStream();//获取输入流
           bitmap = BitmapFactory.decodeStream(stream);
       }catch (MalformedURLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return bitmap;
    }
}
