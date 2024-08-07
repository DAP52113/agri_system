package com.example.agriculture_expert_app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.agriculture_expert_app.LoginActivity;
import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Infomation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class WebConnectionConfig {

	//配置网络
	public static String getConnection(String path) {
	       try {
               try{
                   URL url = new URL(path); //新建url并实例化
                   //进行与服务器端的连接初始化
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                   connection.setRequestMethod("GET");//获取服务器数据
                   connection.setReadTimeout(8000);//设置读取超时的毫秒数
                   connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                   InputStream in = connection.getInputStream();//获取输入流
                   //字符输入流中读取文本并缓冲字符
                   BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                   
                   String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                   
                   return result;
         
               }catch (MalformedURLException e){}
           } catch (IOException e) {
               e.printStackTrace();
           }
		return null;
	}
	
	
	public static String PostArr(String url) {
		String msg = "";
		try {
			HttpURLConnection conn = (HttpURLConnection)new URL("http://192.168.8.92:8080/Agri_experts/"+url).openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);//设置读取超时的毫秒数
			conn.setConnectTimeout(5000);//设置连接超时的毫秒数
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			//获取输出流
//			OutputStream out = conn.getOutputStream();
//			out.write(data.getBytes());
//			out.flush();
			if(conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer response = new StringBuffer();
				String line = null;
				while((line = reader.readLine()) != null) {
					response.append(line);
				}
				msg = response.toString();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	//配置网络
	public static String Post(String url,String data) {
		String msg = "";
		try {
			HttpURLConnection conn = (HttpURLConnection)new URL("http://192.168.8.92:8080/Agri_experts/"+url).openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);//设置读取超时的毫秒数
			conn.setConnectTimeout(5000);//设置连接超时的毫秒数
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			//获取输出流
			OutputStream out = conn.getOutputStream();
			out.write(data.getBytes());
			out.flush();
			if(conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer response = new StringBuffer();
				String line = null;
				while((line = reader.readLine()) != null) {
					response.append(line);
				}
				msg = response.toString();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	
}
