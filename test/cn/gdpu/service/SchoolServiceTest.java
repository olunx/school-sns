package cn.gdpu.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.*;



public class SchoolServiceTest{
	private static SchoolService<School, Integer> schoolService;
	private static InstituteService<Institute, Integer> instituteService;
	private static ClassesService<Classes, Integer> classesService;
	private static VisitorService<Visitor, Integer> visitorService;
	private static PeopleService<People, Integer> peopleService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			schoolService = (SchoolService<School, Integer>) ctx.getBean("schoolService");
			instituteService = (InstituteService<Institute, Integer>) ctx.getBean("instituteService");
			classesService = (ClassesService<Classes, Integer>) ctx.getBean("classesService");
			peopleService =  (PeopleService<People, Integer>) ctx.getBean("peopleService");
			visitorService = (VisitorService<Visitor, Integer>) ctx.getBean("visitorService");
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
	
	@Test
	public void visi(){
		Visitor visitor1 = new Visitor();
		People peo1 = peopleService.getAllEntity(People.class).get(0);
		System.out.println("people.name = " + peo1.getName());
		visitor1.setPeople(peo1);
		visitor1.setTime(new Date());
		visitorService.addEntity(visitor1);
	}
	
	@Test
	public void visitor(){
		
		People peo1 = new People();
		peo1.setName("001");
		peopleService.addEntity(peo1);
		People peo2 = new People();
		peo2.setName("002");
		peopleService.addEntity(peo2);
		People peo3 = new People();
		peo3.setName("003");
		peopleService.addEntity(peo3);
		
		Visitor visitor1 = new Visitor();
		visitor1.setPeople(peo1);
		visitor1.setTime(new Date());
		visitorService.addEntity(visitor1);
		Visitor visitor2 = new Visitor();
		visitor2.setPeople(peo2);
		visitor2.setTime(new Date());
		visitorService.addEntity(visitor2);
		Visitor visitor3 = new Visitor();
		visitor3.setPeople(peo3);
		visitor3.setTime(new Date());
		visitorService.addEntity(visitor3);
		LinkedList<Visitor> visitors = new LinkedList<Visitor>();
		visitors.addFirst(visitor1);
		visitors.addFirst(visitor2);
		visitors.addFirst(visitor3);
		
		for(Visitor v : visitors){
			System.out.print("--" + v.getPeople().getName());
		}
		
		School sch = new School();
		sch.setName("广东药学院");
		sch.setVisitor(visitors);
		schoolService.addEntity(sch);
		
		for(Visitor v : visitors){
			System.out.print("--" + v.getPeople().getName());
		}
	}
	
	@Test
	public void view(){
		Student user = new Student();
		user.setName("test");
		School school = schoolService.getEntity(School.class, 1);
		List<Visitor> visitors = school.getVisitor();
		Visitor visitor = new Visitor();
		visitor.setPeople(user);
		visitor.setTime(new Date());
		boolean ishas =false;
		for(int i=0; i<visitors.size();i++){
			if(visitors.get(i).getPeople() == user){
				visitors.set(i, visitor);
				ishas=true;
			}
		}
		if(ishas != true){
			if(visitors.size()>=10){
				visitors.remove(0);
			}
			visitors.add(visitor);
		}
		school.setVisitor(visitors);
	}
	@Test
	public void list(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.remove(0);
		list.add(6);
		for(int i=list.size()-1; i>=0;i--){
			System.out.print("---" + list.get(i));
		}
	}
	
}
