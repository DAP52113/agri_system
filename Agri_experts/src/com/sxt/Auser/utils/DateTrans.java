package com.sxt.Auser.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 时间格式化类
 * */
public class DateTrans {

	public static String dateTr() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateString = new Date();
		String date = simpleDateFormat.format(dateString); 
		return date;
	}
			
			
	
}
