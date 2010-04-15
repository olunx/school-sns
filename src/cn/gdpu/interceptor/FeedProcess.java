package cn.gdpu.interceptor;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import cn.gdpu.service.MailService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;

public class FeedProcess implements AfterReturningAdvice, MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] arg1, Object obj) throws Throwable {
//		if (obj != null) {
//			Log.init(getClass()).info("MethodBeforeAdvice调用  obj1 " + obj.getClass());
//		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterReturning(Object obj, Method method, Object[] obj2, Object serviceImpl) throws Throwable {

//		if (obj != null) {
//			Log.init(getClass()).info("AfterReturningAdvice调用  obj1 " + obj.getClass());
//		}
//		if (serviceImpl != null) {
//			Log.init(getClass()).info("AfterReturningAdvice调用  obj3 " + serviceImpl.getClass());
//		}
//
//		if (serviceImpl instanceof TopicService) {
//			if (method.getName().equalsIgnoreCase("addEntity")) {
//				Log.init(getClass()).info("添加了一条微博!");
//			}
//
//		} else if (serviceImpl instanceof MailService) {
//			if (method.getName().equalsIgnoreCase("addEntity")) {
//				Log.init(getClass()).info("添加了一张小纸条!");
//			}
//		}

	}

}
