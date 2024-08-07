package com.example.agriculture_expert_app.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;

//数据模型类
public class Model {

	
	//线程池
	private ExecutorService executors = Executors.newCachedThreadPool();
	
	
	private Context mContext;
	//对象
	private static Model model = new Model();
	
	//私有化构造
	private Model() {
		
	}
	
	
	//单例对象
	public static Model getInstance() {
		
		return model;
	}
	
	//初始化方法
	public void init(Context context) {
		mContext = context;
	}
	//获取全局线程池
	public ExecutorService getGlobalThreadPool() {
		
		return executors;
	}
	
	
}
