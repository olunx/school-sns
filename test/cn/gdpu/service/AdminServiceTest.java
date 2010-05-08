package cn.gdpu.service;

import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.gdpu.vo.Admin;



public class AdminServiceTest{
	private static AdminService<Admin, Integer> adminService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			adminService =  (AdminService<Admin, Integer>) ctx.getBean("adminService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setName("管理员");
		admin.setRegTime(new Date());
		adminService.addEntity(admin);
	}
	
	@Test
	public void list() {
		List<Admin> admins= adminService.getAllEntity(Admin.class);
		for(Admin admin: admins){
			System.out.print("admin : " + admin.getName());
		}
	}
}
