package com.example.agriculture_expert_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.agriculture_expert_app.adapter.InfoListAdapter;
import com.example.agriculture_expert_app.adapter.SearchAdapter;
import com.example.agriculture_expert_app.bean.Infomation;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.StringUtils;
import com.example.agriculture_expert_app.utils.WebConnectionConfig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//用户登录主页页面控制Activity
public class InfoListActivity extends Activity  {

	private ImageView info_list_flush;
	private Button info_list_search;
	private EditText  info_etn_search = null;//输入搜索框
	private ListView  infolist;
	Infomation information;
	private List<Infomation> informations = new ArrayList<Infomation>();
	//查询
	private List<Infomation> informationlist = new ArrayList<Infomation>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_list);
		//info_etn_search = (EditText)findViewById(R.id.info_etn_search);
		new Thread() {
			InputStream input = null;
			String data = "";
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(informations != null) {
					informations.clear();
				}
				try {
				
				HttpURLConnection conn = (HttpURLConnection)new URL(Appconfig.BASE_URL+"/androidaction/androidqueryall.action").openConnection();
				//System.out.println("数据库拿到的信息"+infoJSON.toString());
				conn.setRequestMethod("POST");
				conn.setReadTimeout(3000);//设置读取超时的毫秒数
				conn.setConnectTimeout(3000);//设置连接超时的毫秒数

				
				//获取输入流
				input = conn.getInputStream();
				InputStreamReader inputReader = new InputStreamReader(input);
				//为输出创建bufferedReader
				BufferedReader bufferedReader = new BufferedReader(inputReader);
			
				String inputline = "";
				while((inputline = bufferedReader.readLine()) != null) {
					data  = data + inputline+"\n";
				}
				
				
				//字符串转换
					JSONArray jsoninfoArray = new JSONArray(data);
					
					for(int i = 0;i<jsoninfoArray.length();i++) {
						JSONObject jsonObject = (JSONObject)jsoninfoArray.get(i);
						Infomation information = new Infomation();
						Log.d("infolistactivity中查询到的id",""+jsonObject.getInt("id"));
						information.setId(jsonObject.getInt("id"));

						information.setArea(jsonObject.getString("area"));
						information.setDate(jsonObject.getString("date"));
						information.setTitle(jsonObject.getString("title"));
						information.setCity(jsonObject.getString("city"));
						information.setContent(jsonObject.getString("content"));
						information.setDistrict(jsonObject.getString("district"));
						information.setEno(jsonObject.getString("eno"));
						information.setProvince(jsonObject.getString("province"));
						information.setWentiphoto(jsonObject.getString("wentiphoto"));
						information.setLikeNum(jsonObject.getInt("likeNum"));
						information.setCollectNum(jsonObject.getInt("collectNum"));
						informations.add(information);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(input != null) {
						try {
							input.close();
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
					}
				}	
			}
		}.start();

		infolist = (ListView)findViewById(R.id.infolist);

		infolist.setAdapter(new MyAdapter(informations,this));
	
		info_etn_search = (EditText)findViewById(R.id.info_etn_search);
		
	
		//设置单向监听ListView功能
		setListViewListrener();
	
	}
	
//	//设置刷新按钮监听功能
//	private void setFlushViewListener() {
//		// TODO Auto-generated method stub
//		info_list_flush.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(),"正在刷新页面",Toast.LENGTH_LONG).show();
//			}
//		});
//		
//	}


	//设置单向监听ListView
	private void setListViewListrener() {
		// TODO Auto-generated method stub
		infolist.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//得到information对象
				Infomation information = informations.get(position);
				Intent intent = new Intent(InfoListActivity.this,InfoDetailActivity.class);//进行跳转页面
				intent.putExtra("information", information);//数据存放
				startActivity(intent);
				
			}
		});
		
		
	}
	//ListView的配置适配器，用来为listview渲染数据
	 public class  MyAdapter extends BaseAdapter{
		private Bitmap bitmap;
		private List<Infomation> informations ;

		private Context context;
		
		public MyAdapter(List<Infomation> informations, Context context) {
			super();
			this.informations = informations;
			this.context = context;
		} 
		 
		 
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return informations.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.iteminfo_list, parent, false);
			}
			

	           //找到控件
			ImageView item_info_id = (ImageView)convertView.findViewById(R.id.item_info_id);
			//得到信息列表的标题
			TextView item_info_title = (TextView)convertView.findViewById(R.id.item_info_title);
			 item_info_title.setText(informations.get(position).getTitle());
			//得到领域
			TextView item_info_area = (TextView)convertView.findViewById(R.id.item_info_area);
			item_info_area.setText(informations.get(position).getArea());
			//得到日期
			TextView item_info_date = (TextView)convertView.findViewById(R.id.item_info_date);
			item_info_date.setText(informations.get(position).getDate());
			 //Infomation infor= informations.get(position);

			Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					//获取相关的网络路径
					String path = Appconfig.BASE_URL+Appconfig.IMAGEADDPATH+informations.get(position).getWentiphoto();
					bitmap = ImageUtils.getImage(path);
				}
			});
			item_info_id.setImageBitmap(bitmap);
	         return convertView;

		}

	
	 }
	 
	 Handler hand=new Handler(){
	        @Override
	        public void handleMessage(Message msg) {

	            if(msg.what == 1)
	            {
	                ListView infolist = (ListView) findViewById(R.id.infolist);

	                SearchAdapter adapter = new SearchAdapter(informationlist, InfoListActivity.this);
	                infolist.setAdapter(adapter);
	                //startActivity(new Intent( getApplicationContext(),  StuInfoActivity.class ) );
	                setListViewListrenerOnQuery();
	                
	            }
	            else
	            {
	                Toast.makeText(getApplicationContext(),"查询错误",Toast.LENGTH_LONG).show();
	            }
	        }

			private void setListViewListrenerOnQuery() {
				// TODO Auto-generated method stub
				infolist.setOnItemClickListener(new OnItemClickListener() {
					
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						//得到information对象
						Infomation information = informationlist.get(position);
						Intent intent = new Intent(InfoListActivity.this,InfoDetailActivity.class);//进行跳转页面
						intent.putExtra("information", information);//数据存放
						startActivity(intent);
						
					}
				});
				
				
			}

	    };

	 
	 
	 
	//查询信息事件
	public void findinformations(View view){

		final String data = info_etn_search.getText().toString();

		
		new Thread() {
			InputStream input = null;//设置输入流
			String result = "";
			@Override
public void run() {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection)new URL(Appconfig.BASE_URL+"/androidaction/androidquerydetail.action?title="+data).openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(3000);//设置读取超时的毫秒数
			conn.setConnectTimeout(3000);//设置连接超时的毫秒数
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			input = conn.getInputStream();//得到连接输入流
			InputStreamReader inputStreamReader = new InputStreamReader(input);
			//为得到JSON数据创建BufferedReader
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String inputline = "";
			while((inputline = bufferedReader.readLine()) != null) {
				result = result + inputline+"\n";
			}
			
			//JSON转换为字符串
			JSONArray jsoninfoArray = new JSONArray(result);
			for(int i= 0;i<jsoninfoArray.length();i++) {
				JSONObject jsonObject = (JSONObject) jsoninfoArray.get(i);
				Infomation information = new Infomation();
				information.setArea(jsonObject.getString("area"));
				information.setDate(jsonObject.getString("date"));
				information.setTitle(jsonObject.getString("title"));
				information.setCity(jsonObject.getString("city"));
				information.setContent(jsonObject.getString("content"));
				information.setDistrict(jsonObject.getString("district"));
				information.setEno(jsonObject.getString("eno"));
				information.setProvince(jsonObject.getString("province"));
				information.setId(jsonObject.getInt("id"));
				information.setLikeNum(jsonObject.getInt("like_num"));
				information.setCollectNum(jsonObject.getInt("collect_num"));
				information.setWentiphoto(jsonObject.getString("wentiphoto"));

		
				informationlist.add(information);//加入集合
			}
	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hand.sendEmptyMessage(1);
		
		//System.out.println("数据库拿到的信息"+infoJSON.toString());
	}
}.start();
	
	}



}
