package com.example.agriculture_expert_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Consult;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.StringUtils;


import java.text.SimpleDateFormat;
import java.util.List;

//用户发布信息数据适配器
public class SetSubmitInfoAdapter extends BaseAdapter {

    private List<Consult> consults ;
    private Bitmap bitmap;
    private Context context;
    //配置适配器的构造函数
   public SetSubmitInfoAdapter(List<Consult> consults,Context context){
       super();
       this.consults = consults;
       this.context = context;
   }


    @Override
    public int getCount() {
        return consults.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.submit_info_detail_listview,parent,false);

        }
        //获取layout元素
        ImageView item_submit_img = (ImageView) view.findViewById(R.id.item_submit_img);
        //开启线程进行更新
        String path = Appconfig.BASE_URL+Appconfig.IMAGEADDPATH+consults.get(position).getPhoto();
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                bitmap = ImageUtils.getImage(path);
            }
        });
        item_submit_img.setImageBitmap(bitmap);

        TextView item_submit_title = (TextView) view.findViewById(R.id.item_submit_title);
        //进行字符串截取，保证空间的完整
        String pathString = consults.get(position).getTitle();

        item_submit_title.setText(StringUtils.StringSubstring(pathString));


        TextView item_submit_area = (TextView)view.findViewById(R.id.item_submit_area);
        item_submit_area.setText(consults.get(position).getArea());

        TextView item_info_province = (TextView)view.findViewById(R.id.item_info_province);
        item_info_province.setText(consults.get(position).getProvince());

        TextView  item_info_city = (TextView)view.findViewById(R.id.item_info_city);
        item_info_city.setText(consults.get(position).getCity());

        //时间转化
        TextView item_info_date = (TextView) view.findViewById(R.id.item_info_date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        item_info_date.setText(format.format(consults.get(position).getDate()));

        return view;
    }
}
