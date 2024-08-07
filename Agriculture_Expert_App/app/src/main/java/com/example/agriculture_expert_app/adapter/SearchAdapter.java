package com.example.agriculture_expert_app.adapter;

import java.util.List;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Infomation;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
//搜素按钮Adapter适配器
public class SearchAdapter extends BaseAdapter {

	private List<Infomation> informations ;

	private Context context;
	//配置适配器的构造函数
	public SearchAdapter(List<Infomation> informations, Context context) {
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
		TextView item_info_title = (TextView)convertView.findViewById(R.id.item_info_title);
		 item_info_title.setText(informations.get(position).getTitle());
		//得到领域
		TextView item_info_area = (TextView)convertView.findViewById(R.id.item_info_area);
		item_info_area.setText(informations.get(position).getArea());
		//得到日期
		TextView item_info_date = (TextView)convertView.findViewById(R.id.item_info_date);
		item_info_date.setText(informations.get(position).getDate());
		 //Infomation infor= informations.get(position);
		

        return convertView;
	}

}
