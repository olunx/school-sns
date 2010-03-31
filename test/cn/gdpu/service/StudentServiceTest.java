package cn.gdpu.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.*;



public class StudentServiceTest{
	private static StudentService<Student, Integer> studentService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			studentService = (StudentService<Student, Integer>) ctx.getBean("studentService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {

		Student stu1 = new Student();
		stu1.setName("测试1");
		studentService.addEntity(stu1);
		Student stu2 = new Student();
		stu2.setName("测试2");
		studentService.addEntity(stu2);
		Student stu3 = new Student();
		stu3.setName("测试3");
		Set<Student> set = new HashSet<Student>();
		set.add(stu1);
		set.add(stu2);
		stu3.setFriends(set);
		studentService.addEntity(stu3);
		
		studentService.queryForPage(Student.class, 10, 1);
	}
	
	@Test
	public void getAllStu() {
		studentService.getAllEntity(Student.class);
		studentService.getEntity(Student.class, "from Student");
	}
}
