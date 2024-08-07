package com.example.agriculture_expert_app.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;

public class Appconfig {

	//URL访问连接
	public static final String BASE_URL="http://XXX.XXX.XXX.XXX:8080/Agri_experts";
	public static final String REQUEST_URL_REGISTER="/androidaction/androidregister.action";
	public static final String IMAGEADDPATH="/resources/images/";

	public static byte[] Bitmap2Bytes(Bitmap bm){
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
	    return baos.toByteArray();
	}

	
}
