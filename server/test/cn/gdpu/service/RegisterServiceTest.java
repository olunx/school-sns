package cn.gdpu.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.Province;
import cn.gdpu.vo.School;



public class RegisterServiceTest{
	private static ProvinceService<Province, Integer> provinceService;
	private static SchoolService<School, Integer> schoolService;
	private static InstituteService<Institute, Integer> instituteService;
	private static ClassesService<Classes, Integer> classesService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			provinceService =  (ProvinceService<Province, Integer>) ctx.getBean("provinceService");
			schoolService = (SchoolService<School, Integer>) ctx.getBean("schoolService");
			instituteService = (InstituteService<Institute, Integer>) ctx.getBean("instituteService");
			classesService = (ClassesService<Classes, Integer>) ctx.getBean("classesService");

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addProvince(){
		Province province1 = new Province();
		province1.setName("广东省");
		provinceService.addEntity(province1);
		
		Province province2 = new Province();
		province2.setName("北京");
		provinceService.addEntity(province2);
	}
	
	@Test
	public void addSchool(){
		Province province1 = provinceService.getEntity(Province.class, 1);
		School school1 = new School();
		school1.setName("广东药学院");
		school1.setAddress("广东省广州大学城广东药学院");
		school1.setProvince(province1);
		schoolService.addEntity(school1);
		
		School school2 = new School();
		school2.setName("中山大学");
		school2.setAddress("广东省广州大学城中山大学");
		school2.setProvince(province1);
		schoolService.addEntity(school2);
		
		Province province2 = provinceService.getEntity(Province.class, 2);
		School school3 = new School();
		school3.setName("北京大学");
		school3.setAddress("北京");
		school3.setProvince(province2);
		schoolService.addEntity(school3);
	}
	@Test 
	public void addInstitute(){
		//第一个学校
		School school1 = schoolService.getAllEntity(School.class).get(0); 
		Institute institute1 = new Institute();
		institute1.setName("医药信息工程学院");
		institute1.setSchool(school1);
		instituteService.addEntity(institute1);
		
		Institute institute2 = new Institute();
		institute2.setName("医药商学院");
		institute2.setSchool(school1);
		instituteService.addEntity(institute2);

		
		Institute institute3 = new Institute();
		institute3.setName("药科学院");
		institute3.setSchool(school1);
		instituteService.addEntity(institute3);
		
		//第二个学校
		School school2 = schoolService.getAllEntity(School.class).get(1);
		Institute institute4 = new Institute();
		institute4.setName("文学院");
		institute4.setSchool(school2);
		instituteService.addEntity(institute4);
		
		Institute institute5 = new Institute();
		institute5.setName("数学学院");
		institute5.setSchool(school2);
		instituteService.addEntity(institute5);

		//第三个学校
		School school3 = schoolService.getAllEntity(School.class).get(2);
		Institute institute6 = new Institute();
		institute6.setName("XX学院");
		institute6.setSchool(school3);
		instituteService.addEntity(institute6);
		
	}
	
	@Test
	public void addClasses() {	
		Institute institute1 = instituteService.getAllEntity(Institute.class).get(0);
		
		Classes classes1 = new Classes();
		classes1.setName("信息管理与信息系统（医药软件工程方向）07");
		classes1.setEntryYear(2007);
		classes1.setInstitute(institute1);
		
		classesService.addEntity(classes1);
		
		
		Classes classes2 = new Classes();
		classes2.setName("信息管理与信息系统（医药信息方向）07");
		classes2.setEntryYear(2007);
		classes2.setInstitute(institute1);
		
		classesService.addEntity(classes2);
		
		Institute institute2 = instituteService.getAllEntity(Institute.class).get(1);

		Classes classes3 = new Classes();
		classes3.setName("医药电子商务");
		classes3.setEntryYear(2007);
		classes3.setInstitute(institute2);
		
		classesService.addEntity(classes3);
		
		Institute institute3 = instituteService.getAllEntity(Institute.class).get(2);

		Classes classes4 = new Classes();
		classes4.setName("制药工程08");
		classes4.setEntryYear(2008);
		classes4.setInstitute(institute3);
		
		classesService.addEntity(classes4);
		
		Institute institute4 = instituteService.getAllEntity(Institute.class).get(3);

		Classes classes5 = new Classes();
		classes5.setName("历史");
		classes5.setEntryYear(2008);
		classes5.setInstitute(institute4);
		
		classesService.addEntity(classes5);
		
		Institute institute5 = instituteService.getAllEntity(Institute.class).get(4);

		Classes classes6 = new Classes();
		classes6.setName("高数班");
		classes6.setEntryYear(2008);
		classes6.setInstitute(institute5);
		
		classesService.addEntity(classes6);
		
		Institute institute6 = instituteService.getAllEntity(Institute.class).get(5);

		Classes classes7 = new Classes();
		classes7.setName("XX班");
		classes7.setEntryYear(2008);
		classes7.setInstitute(institute6);
		
		classesService.addEntity(classes7);
	}
	
	@Test
	public void listClasses(){
		List<School> schools = schoolService.getAllEntity(School.class);
		for(School school: schools){
			System.out.println("学校：" + school.getName());
			for(Institute ins : school.getInstitute()){
				System.out.println("学校--学院：" + ins.getName());
				for(Classes cla : ins.getClasses()){
					System.out.println("学校--学院--班级：" + cla.getName());
				}
			}
		}
	}
	
	@Test
	public void gogo(){         //用这个生成初始的班级
		addProvince();
		addSchool();
		addInstitute();
		addClasses();
		listClasses();
	}
	
	@Test
	public void jsonlistschool(){
		List<Province> provinces = provinceService.getAllEntity(Province.class);
		
		Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
		for(Province province: provinces){
			Map<String, Object> promap = new LinkedHashMap<String, Object>();
			Map<String, Integer> sclmap = new LinkedHashMap<String, Integer>();
			promap.put("key", province.getId());
			promap.put("defaultvalue", province.getSchools().iterator().next().getId());
			for(School school : province.getSchools()){
				sclmap.put(school.getName(), school.getId());
			}
			
			promap.put("values", sclmap);
			map.put(province.getName(), promap);
		}
        JSONObject jo1 = JSONObject.fromObject(map);
        System.out.println("jo1-----------: " + jo1);
	}
	@Test
	public void jsonlistclasses(){
		List<Institute> institutes= instituteService.getAllEntity(Institute.class);

		Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
		for(Institute institute: institutes){
			Map<String, Object> insmap = new LinkedHashMap<String, Object>();
			Map<String, Integer> clamap = new LinkedHashMap<String, Integer>();
			insmap.put("key", institute.getId());
			insmap.put("defaultvalue", institute.getClasses().iterator().next().getId());
			for(Classes classes: institute.getClasses()){
				clamap.put(classes.getName(), classes.getId());
			}
			
			insmap.put("values", clamap);
			map.put(institute.getName(), insmap);
		}
        JSONObject jo1 = JSONObject.fromObject(map);
        System.out.println("jo1-----------: " + jo1);
	}
}
