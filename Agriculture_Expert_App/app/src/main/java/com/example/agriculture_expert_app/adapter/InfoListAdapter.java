package com.example.agriculture_expert_app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Infomation;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoListAdapter extends BaseAdapter {

	 
	private  Context context;
	List<Infomation> informations = new ArrayList<Infomation>();
	private Bitmap bitmap;

	public InfoListAdapter(Context context, List<Infomation> informations) {
		super();
		this.context = context;
		this.informations = informations;
	}


	//展示listview行数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return informations.size();
	}

	//返回指定位置的数据
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return informations.get(position);
	}

	//得到指定位置的id
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//得到视图文件
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(R.layout.iteminfo_list, null);
		//得到信息列表的图片id
		ImageView item_info_id = (ImageView)convertView.findViewById(R.id.item_info_id);
		//得到信息列表的标题
		TextView item_info_title = (TextView)convertView.findViewById(R.id.item_info_title);
		//得到领域
		TextView item_info_area = (TextView)convertView.findViewById(R.id.item_info_area);
		//得到日期
		TextView item_info_date = (TextView)convertView.findViewById(R.id.item_info_date);

		item_info_title.setText(informations.get(position).getTitle());
		item_info_area.setText(informations.get(position).getArea());
		item_info_date.setText(informations.get(position).getDate());

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

	

	private Bitmap stringToBitmap( byte[] bytes) {
		// TODO Auto-generated method stub
		return  BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}


}
