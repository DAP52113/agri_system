package com.sxt.sys.utils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//redis配置类
public class RedisUtils {

	@Autowired
	private RedisTemplate redisTemplate;
	
	
	//存入缓存
	public boolean set(String key,String value){
		try {
			redisTemplate.opsForValue().set(key, value);//放入缓存
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			//释放连接
			RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
		}
	}
	
	//检查redis中是否存在key
	public boolean haskey(String key){
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//设置过期时间
	 public boolean expire(String key, long time) {
	        try {
	            if (time > 0) {
	                redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * 根据key 获取过期时间
	     *
	     * @param key 键 不能为null
	     * @return 时间(秒) 返回0代表为永久有效
	     */
	    public long getExpire(String key) {
	        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	    }

	//获取缓存数值
	    public String get(String key) {
	        return key == null ? null : redisTemplate.opsForValue().get(key).toString();
	    }
	
	//存入缓存并指定存入时间
	    public boolean set(String key, String value, long time){
	    	try {
				if(time > 0){
					redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
				}else{
					set(key, value);
				}
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
	    }
	 
	    

	
}
