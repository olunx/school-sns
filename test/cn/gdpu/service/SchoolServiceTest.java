package cn.gdpu.service;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.*;



public class SchoolServiceTest{
	private static SchoolService<School, Integer> schoolService;
	private static InstituteService<Institute, Integer> instituteService;
	private static ClassesService<Classes, Integer> classesService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			schoolService = (SchoolService<School, Integer>) ctx.getBean("schoolService");
			instituteService = (InstituteService<Institute, Integer>) ctx.getBean("instituteService");
			classesService = (ClassesService<Classes, Integer>) ctx.getBean("classesService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		School sch = new School();
		sch.setName("广东药学院");
		schoolService.addEntity(sch);
		
		Institute i1 = new Institute();
		i1.setName("医药信息工程学院");
		i1.setSchool(sch);
		Institute i2 = new Institute();
		i2.setName("公共学院");
		i2.setSchool(sch);
		instituteService.addEntity(i1);
		instituteService.addEntity(i2);
		
		Classes c1 = new Classes();
		c1.setName("信息管理与信息系统（医药软件工程方向）");
		c1.setEntryYear(2007);
		c1.setInstitute(i1);
		Classes c2 = new Classes();
		c2.setName("生物医学智能");
		c2.setEntryYear(2007);
		c2.setInstitute(i1);
		classesService.addEntity(c1);
		classesService.addEntity(c2);
		

	}
}
