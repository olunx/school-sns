package cn.gdpu.service;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.A;
import cn.gdpu.vo.ClassFee;



public class ClassFeeServiceTest{
	private static ClassFeeService<ClassFee, Integer> classfeeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			classfeeService = (ClassFeeService<ClassFee, Integer>) ctx.getBean("classfeeService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		System.out.println("ctx: sdfsdf" );
		ClassFee classfee = new ClassFee();
		classfee.setFee(200);
		classfee.setRemarks("测试班费");
		classfeeService.addEntity(classfee);
	}
	
	@Test
	public void list() {
		System.out.println("ctx: sdfsdf" );
		
		List<ClassFee> classfees= classfeeService.getAllEntity(ClassFee.class);
		for(ClassFee classfee: classfees){
			System.out.print("test:classfee : " + classfee.getId());
		}
	}
}
