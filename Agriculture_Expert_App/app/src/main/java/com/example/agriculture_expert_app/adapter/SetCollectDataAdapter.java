package com.example.agriculture_expert_app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Infomation;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

//设置收藏信息listview
public class SetCollectDataAdapter extends BaseAdapter {

    private List<Infomation> informations ;
    private Bitmap bitmap;
    private Context context;
    //配置适配器的构造函数
    public SetCollectDataAdapter(List<Infomation> informations, Context context) {
        super();
        this.informations = informations;
        this.context = context;
    }


    @Override
    public int getCount() {
        return informations.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.collect_detail_from_listview,parent,false);

        }
        //获取layout元素
        ImageView item_collect_img = (ImageView) view.findViewById(R.id.item_collect_img);
        String path = Appconfig.BASE_URL+Appconfig.IMAGEADDPATH+informations.get(position).getWentiphoto();//2021-01-26/202101262226459923302.jpg_temp
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
             bitmap  = ImageUtils.getImage(path);
            }
        });
        //设置图片
        item_collect_img.setImageBitmap(bitmap);

        TextView item_collect_title = (TextView)view.findViewById(R.id.item_collect_title);
        //设置元素
        item_collect_title.setText(informations.get(position).getTitle());

        TextView item_collect_area = (TextView) view.findViewById(R.id.item_collect_area);
        item_collect_area.setText(informations.get(position).getArea());

        TextView item_info_date = (TextView)view.findViewById(R.id.item_info_date);
        item_info_date.setText(informations.get(position).getDate());

        TextView item_info_eno = (TextView) view.findViewById(R.id.item_info_eno);
        item_info_eno.setText(informations.get(position).getEno());

        return view;
    }
}
