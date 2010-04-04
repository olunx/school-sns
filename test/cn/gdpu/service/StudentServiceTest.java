package cn.gdpu.service;

import java.util.HashSet;
import java.util.List;
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
		stu1.setSno("1");
		stu1.setUsername("1");
		stu1.setPassword("1");
		stu1.setName("测试1");
		studentService.addEntity(stu1);
		
	}
	
	@Test
	public void getAllStu() {
		List<Student> students = studentService.getAllEntity(Student.class);
		for(Student s:students)
		System.out.println(s.getSno());
		studentService.getEntity(Student.class, "from Student");
	}
}
