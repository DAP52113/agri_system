package com.example.agriculture_expert_app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.example.agriculture_expert_app.bean.Infomation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//字符串判断工具类
public class StringUtils {

	public static boolean isEmpty(String str) {
		if(str == null || str.length() <=0) {
			return true;//字符串为空
		}else {
			return false;
		}
	}
	
	
	//手机号验证
	//返回值为trueu或者false
	 public static boolean validateMobilePhone(String phone) {
	        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
	        return pattern.matcher(phone).matches();
	    }

	

	//字符串转换
	public static List<Infomation> JSONTranS(String data) throws JSONException{
		List<Infomation> informations = new ArrayList<Infomation>();
		JSONArray jsonarray = new JSONArray(data);
		for(int i = 0;i<jsonarray.length();i++) {
			JSONObject jsonObject = (JSONObject)jsonarray.get(i);
			Infomation information = new Infomation();
			information.setArea(jsonObject.getString("area"));
			information.setDate(jsonObject.getString("date"));
			information.setTitle(jsonObject.getString("title"));
			informations.add(information);
		}
		
		
		return informations;
	}
	//字符串截取工具类
	public static String StringSubstring(String path){
		if(path.trim().length() > 10){//去掉空格，若长度大于10 超出空间
			return  path.substring(0,10);
		}
		return path;//否则直接输出
	}

}
