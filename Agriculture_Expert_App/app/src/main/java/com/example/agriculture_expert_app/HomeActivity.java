package com.example.agriculture_expert_app;



import com.example.agriculture_expert_app.activity.BaseActivity;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.Model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HomeActivity extends BaseActivity{




	private View view;
	private SharedPreferences sharePre;

	
	//由于子类集成了父类，权限不能减小，改为Public
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


	}
	
	
	//按钮点击跳转页面
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.home_index://选择的是主页按钮
			naticatToActivity(InfoListActivity.class);//跳转到首页
			break;
		case R.id.home_askquestion://选择的是在线咨询按钮
			naticatToActivity(QuestionActivity.class);//跳转到专家在线咨询页面
			break;
		case R.id.home_appoint://选择的是专家预约按钮
			naticatToActivity(ExpertnlineWelcome.class);//跳转到专家在线主页
			break;
		case R.id.home_myinfo://选择的是用户个人信息按钮

			Toast.makeText(HomeActivity.this,"加载中.......",Toast.LENGTH_SHORT).show();

			sharePre = getSharedPreferences("configeperts", MODE_PRIVATE);
			String unname = sharePre.getString("unname","");
			Log.d("unname中存储的是fromhome",unname);

			Intent intent = new Intent(HomeActivity.this,MyInfoActivity.class);
			intent.putExtra("unname",unname);

			//开启线程获取用户收藏信息个数
			Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					InputStream input = null;
					String data = "";
					String path= Appconfig.BASE_URL+"/androidaction/selectCollectCountNum.action?unname="+unname;
					try {
						try {
							URL url = new URL(path);//新建url并实例化
							//进行与服务器端的连接初始化

							HttpURLConnection connection = (HttpURLConnection) url.openConnection();

							connection.setRequestMethod("GET");//获取服务器数据
							connection.setReadTimeout(5000);//设置读取超时的毫秒数
							connection.setConnectTimeout(5000);//设置连接超时的毫秒数
							InputStream in = connection.getInputStream();//获取输入流
							//字符输入流中读取文本并缓冲字符
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));

							String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据

							if(!result.equals("")){
								intent.putExtra("collectCountNum",result);

							}else{
								Looper.prepare();//使消息循环初始化
								//Looper作为线程之间的通信
								//并且调用Looper.loop()使消息循环一直处于运行状态
								//存储sp登录数据
								Toast.makeText(HomeActivity.this,"暂未查询到收藏内容",Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
						} catch (MalformedURLException e) {
						} catch (IOException e) {
							e.printStackTrace();
						}
					} finally {
						startActivity(intent);
					}
				}
			});

			//开启线程获取用户点赞个数
			Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					InputStream input = null;
					String data = "";
					String path= Appconfig.BASE_URL+"/androidaction/selectLikeCountNum.action?unname="+unname;
					try {
						try {
							URL url = new URL(path);//新建url并实例化
							//进行与服务器端的连接初始化

							HttpURLConnection connection = (HttpURLConnection) url.openConnection();

							connection.setRequestMethod("GET");//获取服务器数据
							connection.setReadTimeout(5000);//设置读取超时的毫秒数
							connection.setConnectTimeout(5000);//设置连接超时的毫秒数
							InputStream in = connection.getInputStream();//获取输入流
							//字符输入流中读取文本并缓冲字符
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));

							String result_like = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据


							if(!result_like.equals("")){
								intent.putExtra("likeCountNum",result_like);

							}else{
								Looper.prepare();//使消息循环初始化
								//Looper作为线程之间的通信
								//并且调用Looper.loop()使消息循环一直处于运行状态
								//存储sp登录数据
								Toast.makeText(HomeActivity.this,"暂未查询到点赞内容",Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
						} catch (MalformedURLException e) {
						} catch (IOException e) {
							e.printStackTrace();
						}
					} finally {
						startActivity(intent);
					}

				}
			});

			//开启线程获取用户咨询个数
			Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					InputStream input = null;
					String data = "";
					String path= Appconfig.BASE_URL+"/androidaction/selectConsultCountNum.action?unname="+unname;
					try {
						try {
							URL url = new URL(path);//新建url并实例化
							//进行与服务器端的连接初始化

							HttpURLConnection connection = (HttpURLConnection) url.openConnection();

							connection.setRequestMethod("GET");//获取服务器数据
							connection.setReadTimeout(5000);//设置读取超时的毫秒数
							connection.setConnectTimeout(5000);//设置连接超时的毫秒数
							InputStream in = connection.getInputStream();//获取输入流
							//字符输入流中读取文本并缓冲字符
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));

							String result_consult = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据


							if(!result_consult.equals("")){
								intent.putExtra("consultCountNum",result_consult);

							}else{
								Looper.prepare();//使消息循环初始化
								//Looper作为线程之间的通信
								//并且调用Looper.loop()使消息循环一直处于运行状态
								//存储sp登录数据
								Toast.makeText(HomeActivity.this,"暂未查询到咨询内容",Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
						} catch (MalformedURLException e) {
						} catch (IOException e) {
							e.printStackTrace();
						}
					} finally {
						startActivity(intent);

					}
				}
			});

			//开启线程获取专家回复个数
			Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					InputStream input = null;
					String data = "";
					String path= Appconfig.BASE_URL+"/androidaction/selectReplyCountNum.action?unname="+unname;
					try {
						try {
							URL url = new URL(path);//新建url并实例化
							//进行与服务器端的连接初始化
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();

							connection.setRequestMethod("GET");//获取服务器数据
							connection.setReadTimeout(5000);//设置读取超时的毫秒数
							connection.setConnectTimeout(5000);//设置连接超时的毫秒数
							InputStream in = connection.getInputStream();//获取输入流
							//字符输入流中读取文本并缓冲字符
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));

							String result_reply = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据

							if(!result_reply.equals("")){
								intent.putExtra("replyCountNum",result_reply);
							}else{
								Looper.prepare();//使消息循环初始化
								//Looper作为线程之间的通信
								//存储sp登录数据
								Toast.makeText(HomeActivity.this,"暂未查询到回复内容",Toast.LENGTH_SHORT).show();
								Looper.loop();
							}
						} catch (MalformedURLException e) {
						} catch (IOException e) {
							e.printStackTrace();
						}
					} finally {
						startActivity(intent);
					}
				}
			});
			break;
		default:
			break;
		}
	}
}
