package com.example.agriculture_expert_app;

import com.example.agriculture_expert_app.bean.Expert;
import com.example.agriculture_expert_app.bean.Infomation;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.likeUtils;
import android.annotation.SuppressLint;
import com.example.agriculture_expert_app.utils.likeUtilstwo;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class InfoDetailActivity extends Activity {

	private ImageView info_back;
	private TextView info_detail_title;//标题
	private ImageView info_detail_photo;//中间图片
	private TextView info_detail_title2;//二级标题
	private TextView info_detail_expert;//专家
	private TextView info_detail_content;//内容
	private TextView info_detail_provenice;
	private TextView info_detail_city;
	private TextView info_detail_district;
	private TextView info_detail_area;
	private TextView info_detail_date;//时间\
	private ImageView img_collect;//收藏图标
	private TextView collect;//收藏文本
	private ImageView img_like;//设置点赞图标
	private TextView dz;//设置点赞文本
	private boolean dianzan_status = false;// false代表未点赞，   true代表点赞
	private boolean collect_status = false;// false代表未收藏，   true代表收藏
	private SharedPreferences sharedPreferences;//数据持久化
	private static  int id;
	private Bitmap bitmap;

	//收藏
	private Handler hand  = new Handler(Looper.getMainLooper()){

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message message) {
			if(message.what == 1)
			{
				likeUtils ll = (likeUtils) message.obj;
				collect = (TextView) findViewById(R.id.collect);
				collect.setText(ll.getCollectNum());
				collect_status = ((likeUtils) message.obj).isCollect_status();
				Log.d("collect_status的状态",collect_status+"");
			}
		}
	};

	//图片处理handle
	private Handler hand_image  = new Handler(Looper.getMainLooper()){

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message message) {
			if(message.what == 1)
			{
				Bitmap bitmap1 = (Bitmap) message.obj;
				info_detail_photo.setImageBitmap(bitmap1);
			}
		}
	};



	//点赞
	private Handler hand_like  = new Handler(Looper.getMainLooper()){

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message message) {
			if(message.what == 1)
			{
				likeUtilstwo ll = (likeUtilstwo) message.obj;
				dz = (TextView) findViewById(R.id.dz);
				dz.setText(ll.getLikeNum());
				dianzan_status = ((likeUtilstwo) message.obj).isDianzan_status();

			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_detail);
		initView();
		Intent intent = getIntent();
		Infomation info = (Infomation)intent.getSerializableExtra("information");
		Log.d("查询id",""+info.getId());
		info_detail_title.setText(info.getTitle());
		info_detail_title2.setText(info.getTitle());
		info_detail_expert.setText(info.getEno());
		info_detail_content.setText(info.getContent());
		info_detail_provenice.setText(info.getProvince());
		info_detail_city.setText(info.getCity());
		info_detail_district.setText(info.getDistrict());
		info_detail_area.setText(info.getArea());
		info_detail_date.setText(info.getDate());
		//图片处理
		Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				String path = Appconfig.BASE_URL+Appconfig.IMAGEADDPATH+info.getWentiphoto();
				bitmap = ImageUtils.getImage(path);

				Message message = new Message();
				message.obj = bitmap;
				message.what=1;
				hand_image.sendMessage(message);
			}
		});

		id = (Integer)info.getId();
		Log.d("详情activity的id",""+id);
		//若点赞数不为0，且已经点赞
		if(dianzan_status){
			dz.setText(info.getLikeNum().toString());
			dz.setTextColor(Color.parseColor("#E21918"));
			img_like.setImageResource(R.mipmap.dianzan_select);
		}else{//未点赞
			dz.setText(info.getLikeNum().toString());
			dz.setTextColor(Color.parseColor("#161616"));
			img_like.setImageResource(R.mipmap.dianzan);
		}



//		//若收藏数不为0，且已经收藏
		if(collect_status){
		collect.setText(info.getCollectNum().toString());
		collect.setTextColor(Color.parseColor("#E21918"));
		img_collect.setImageResource(R.mipmap.collect_select);
		}else{//未收藏
			collect.setText(info.getCollectNum().toString());
			collect.setTextColor(Color.parseColor("#161616"));
			img_collect.setImageResource(R.mipmap.collect);
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		info_back = (ImageView) findViewById(R.id.info_back);
		info_detail_title = (TextView) findViewById(R.id.info_detail_title);
		info_detail_photo = (ImageView)findViewById(R.id.info_detail_photo);
		info_detail_title2 = (TextView)findViewById(R.id.info_detail_title2);
		info_detail_expert = (TextView)findViewById(R.id.info_detail_expert);
		info_detail_content = (TextView)findViewById(R.id.info_detail_content);
		info_detail_provenice = (TextView)findViewById(R.id.info_detail_provenice);
		info_detail_city = (TextView)findViewById(R.id.info_detail_city);
		info_detail_district = (TextView)findViewById(R.id.info_detail_district);
		info_detail_area = (TextView)findViewById(R.id.info_detail_area);
		info_detail_date = (TextView)findViewById(R.id.info_detail_date);
		img_collect = (ImageView) findViewById(R.id.img_collect);
		collect = (TextView) findViewById(R.id.collect);

		img_like = (ImageView)findViewById(R.id.img_like);
		dz = (TextView) findViewById(R.id.dz);
		//获取收藏用户
		sharedPreferences = getSharedPreferences("configeperts", MODE_PRIVATE);
		String unname = sharedPreferences.getString("unname","");
		Log.d("unname中存储的是from收藏",unname);
		
		info_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();//销毁当前的activity;
			}
		});
		//设置收藏功能点击事件进行监听
		img_collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//得到收藏量
				int collect_num =  Integer.parseInt(collect.getText().toString());
				if(collect_status == true && collect_num > 0) {//显示已经收藏的操作
					collect.setText(String.valueOf(--collect_num));
					collect.setTextColor(Color.parseColor("#161616"));
					img_collect.setImageResource(R.mipmap.collect);
				}else {//表示数量为0
					collect.setText(String.valueOf(++collect_num));
					collect_status = true;
					collect.setTextColor(Color.parseColor("#E21918"));
					img_collect.setImageResource(R.mipmap.collect_select);
				}

				int collectNum = collect_num;



				//开启线程存储收藏量数据
				Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
					@Override
					public void run() {
						String path= Appconfig.BASE_URL+"/androidaction/informationAddCollectNum.action?id="+id+"&collectNum="+collectNum+"&unname="+unname;

						try {
							try{
								URL url = new URL(path); //新建url并实例化
								//进行与服务器端的连接初始化
								HttpURLConnection connection = (HttpURLConnection) url.openConnection();
								connection.setRequestMethod("GET");//获取服务器数据
								connection.setReadTimeout(5000);//设置读取超时的毫秒数
								connection.setConnectTimeout(5000);//设置连接超时的毫秒数
								InputStream in = connection.getInputStream();//获取输入流
								//字符输入流中读取文本并缓冲字符
								BufferedReader reader = new BufferedReader(new InputStreamReader(in));

								String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据

								if (result.equals("successful")){
									Looper.prepare();//使消息循环初始化
									//Looper作为线程之间的通信
									//并且调用Looper.loop()使消息循环一直处于运行状态
									//存储sp登录数据
									Toast.makeText(InfoDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
									Looper.loop();
								}else {

									Looper.prepare();
									Toast.makeText(InfoDetailActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();
									Looper.loop();//关闭线程
								}
							}catch (MalformedURLException e){}
						} catch (IOException e) {
							e.printStackTrace();
						}

						Message message_collect = new Message();
						message_collect.what = 1;

						likeUtils likeu = new likeUtils();
						likeu.setCollectNum(collectNum);
						likeu.setCollect_status(collect_status);


						message_collect.obj = likeu;
						hand.sendMessage(message_collect);
					}
				});
			}
		});
		
		//设置点赞功能点击监听
		img_like.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int dianzanNum = Integer.parseInt(dz.getText().toString());
				if(dianzanNum > 0  &&  dianzan_status==true) {//已经点赞
					dz.setText(String.valueOf(--dianzanNum));
					dz.setTextColor(Color.parseColor("#161616"));
					img_like.setImageResource(R.mipmap.dianzan);
				}else {//未点赞
					dz.setText(String.valueOf(++dianzanNum));
					dianzan_status = true;
					dz.setTextColor(Color.parseColor("#E21918"));
					img_like.setImageResource(R.mipmap.dianzan_select);
				}

				int like_num = dianzanNum;

				//开启线程进行存储点赞信息
				Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
					@Override
					public void run() {
						String path= Appconfig.BASE_URL+"/androidaction/informationAddLikeNum.action?id="+id+"&like_num="+like_num+"&unname="+unname;

						try {
							try{
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

								if(result.equals("successful")){
									Looper.prepare();//使消息循环初始化
									//Looper作为线程之间的通信
									//并且调用Looper.loop()使消息循环一直处于运行状态
									//存储sp登录数据
									Toast.makeText(InfoDetailActivity.this,"点赞成功",Toast.LENGTH_SHORT).show();
									Looper.loop();
								}else{
									Looper.prepare();//使消息循环初始化
									//Looper作为线程之间的通信
									//并且调用Looper.loop()使消息循环一直处于运行状态
									//存储sp登录数据
									Toast.makeText(InfoDetailActivity.this,"点赞失败",Toast.LENGTH_SHORT).show();
									Looper.loop();
								}

								Message message_like = new Message();
								message_like.what = 1;

								likeUtilstwo likeu = new likeUtilstwo();
								likeu.setLikeNum(like_num);
								likeu.setDianzan_status(dianzan_status);


								message_like.obj = likeu;
								hand_like.sendMessage(message_like);

							}catch (MalformedURLException e){}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}



