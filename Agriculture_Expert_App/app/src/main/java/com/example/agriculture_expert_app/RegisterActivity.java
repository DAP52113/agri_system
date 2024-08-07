package com.example.agriculture_expert_app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.agriculture_expert_app.activity.BaseActivity;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.StringUtils;
import com.example.agriculture_expert_app.utils.WebConnectionConfig;

import android.R.bool;
/*
 * 用户注册界面安卓前端控制器
 * */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {

	private Button btn_expert_reg;//得到跳转专家注册页面按钮
	private EditText et_account;
	private EditText et_pwd;
	private Button btn_register;//得到用户注册按钮 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		et_account = (EditText)findViewById(R.id.et_account);//得到注册账号
		et_pwd = (EditText)findViewById(R.id.et_pwd);//得到密码
	    //实现到专家页面的跳转
/*		btn_expert_reg = (Button)findViewById(R.id.btn_expert_reg);
		btn_expert_reg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in_expert = new Intent(RegisterActivity.this,ExpertRegActivity.class);
				startActivity(in_expert);	
			}
		});*/
		
		//实现用户注册，查询服务器
		btn_register = (Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String unname = et_account.getText().toString().trim();
				String password = et_pwd.getText().toString().trim();
				register(unname,password);
			}
		});
		
	}
	
	//注册实现函数
	private void register(final String unname,final String password) {
		// TODO Auto-generated method stub
		
		//检测用户名是否已经存在
		if(StringUtils.isEmpty(unname)) {
			showToast("请输入用户名");
			return;
		}
		if(StringUtils.isEmpty(password)) {
			showToast("请输入密码");
			return;
		}
		//用户注册
		
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					// TODO Auto-generated method stub
					String path = Appconfig.BASE_URL+"/androidaction/androidcheckusername.action?unname="+unname;
					String result = WebConnectionConfig.getConnection(path);//得到路径
					if (result.equals("exist")) {//用户名已经存在
						 Looper.prepare();//使消息循环初始化

                         Toast.makeText(RegisterActivity.this,"用户名已经存在，请重新输入",Toast.LENGTH_SHORT).show();

                         Looper.loop();
						
					}else if(result.equals("no exist")){

						//可以注册
						new Thread(new Runnable() {

							@Override
							public void run() {
								
								// TODO Auto-generated method stub
				                String path=Appconfig.BASE_URL+"/androidaction/androidlregister.action?unname="+unname+"&password="+password;
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

				                        if (result.equals("register successfully!")){
				                        	//Looper作为线程之间的通信
				                        	//并且调用Looper.loop()使消息循环一直处于运行状态

				                            Looper.prepare();//使消息循环初始化

				                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

											Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
											startActivity(intent);
				                            Looper.loop();

				                        }else {

				                              Looper.prepare();

				                              Toast.makeText(RegisterActivity.this,"输入错误",Toast.LENGTH_SHORT).show();

				                              Looper.loop();//关闭线程
				                        }
				                    }catch (MalformedURLException e){}
				                } catch (IOException e) {
				                    e.printStackTrace();
				                }
								
							}}).start();
					}
				}}).start();
	}
}
