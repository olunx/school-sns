package cn.gdpu.service;

import java.util.Map;


public interface FetionService {
	
	public abstract void sendFetion(String phone,String pwd,String tophone,String content);
	public abstract void sendScore(String phone, String pwd, Map<String, Object> items);
}
