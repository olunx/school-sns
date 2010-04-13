package cn.gdpu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.IssueType;



public class IssueTypeServiceTest{
	private static IssueTypeService<IssueType, Integer> issueTypeService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			issueTypeService = (IssueTypeService<IssueType, Integer>) ctx.getBean("issueTypeService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		IssueType it1 = new IssueType();
		it1.setName("文学");
		IssueType it2 = new IssueType();
		it2.setName("语文");
		it2.setIsleaf(true);
		IssueType it3 = new IssueType();
		it3.setName("数学");
		it3.setIsleaf(true);
		Set<IssueType> its1 =new HashSet<IssueType>();
		its1.add(it2);
		its1.add(it3);
		it1.setChildType(its1);
		IssueType it4 = new IssueType();
		it4.setName("体育");
		IssueType it5 = new IssueType();
		it5.setName("足球");
		it5.setIsleaf(true);
		IssueType it6 = new IssueType();
		it6.setName("篮球");
		it6.setIsleaf(true);
		Set<IssueType> its2 =new HashSet<IssueType>();
		its2.add(it5);
		its2.add(it6);
		it4.setChildType(its2);
		issueTypeService.addEntity(it1);
		issueTypeService.addEntity(it4);
	}
	@Test
	public void list() {                 
		String hql = "from IssueType it where it.isleaf = '0'";
		List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
		for(IssueType it : its){
			String test = "---"+it.getName();
			System.out.println(test);
			checkCT(it.getChildType(),test);
		}
	}
	public void checkCT(Set<IssueType> set,String test){    //递归拿全子类型
		for(IssueType it : set){
			String str =test;
			str += "---" + it.getName();
			System.out.println(str);
			checkCT(it.getChildType(),str);
		}
	}
	@Test
	public void jsonstr(){//自已写的String类的json格式
		String hql = "from IssueType it where it.isleaf = '0'";
		List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
		String str = "{";
		for(IssueType it : its ){
			str += "\"" + it.getName() + "\"" + ":" + "{";
			str += "\"key\":" + it.getId() + ", ";
			str += "\"defaultvalue\":" + it.getChildType().iterator().next().getId() + ",";
			str += "\"values\":{";
			for(IssueType itc : it.getChildType()){
				str += "\"" + itc.getName() + "\"" + ":" + itc.getId() + ",";
			}
			str = str.substring(0,str.length()-1);
			str += "}},";
		}
		str = str.substring(0,str.length()-1);
		str += "}";
		System.out.println("str = " + str);
	}
	
	@Test
	public void json(){    //用JSONLIB的二级联动
		String hql = "from IssueType it where it.isleaf = '0'";
		List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
		Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
		for(IssueType it : its){
			Map<String, Object> itmap = new LinkedHashMap<String, Object>();
			Map<String, Integer> itcmap = new LinkedHashMap<String, Integer>();
			itmap.put("key", it.getId());
			itmap.put("defaultvalue", it.getChildType().iterator().next().getId());
			for(IssueType itc : it.getChildType()){
				itcmap.put(itc.getName(), itc.getId());
			}
			itmap.put("values", itcmap);
			map.put(it.getName(), itmap);
		}
        JSONObject jo = JSONObject.fromObject(map);
        System.out.println(jo);

	}
	@Test 
	public void parent(){     //从子类型拿父类型
		String hql = "from IssueType it where it.isleaf = '1'";
		List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
		System.out.println("its.size  = " + its.size());
		IssueType it = its.get(0);
		System.out.println("it parent = " + it.getParent().getName());
	}
	@Test
	public void searchjson(){           //搜索时要用到的增加全部选项的二级联动
		String hql = "from IssueType it where it.isleaf = '0'";
		List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
		Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
		Map<String, Object> itallmap = new LinkedHashMap<String, Object>();
		Map<String, Integer> itcallmap = new LinkedHashMap<String, Integer>();
		for(IssueType it : its){
			itallmap.put("key", -1);
			itallmap.put("defaultvalue", -1);
			itcallmap.put("全部", -1);
			for(IssueType itc : it.getChildType()){
				itcallmap.put(itc.getName(), itc.getId());
			}
			itallmap.put("values", itcallmap);
			
		}
		map.put("全部", itallmap);
		for(IssueType it : its){
			Map<String, Object> itmap = new LinkedHashMap<String, Object>();
			Map<String, Integer> itcmap = new LinkedHashMap<String, Integer>();
			itmap.put("key", it.getId());
			itmap.put("defaultvalue", it.getChildType().iterator().next().getId());
			for(IssueType itc : it.getChildType()){
				itcmap.put(itc.getName(), itc.getId());
			}
			itmap.put("values", itcmap);
			map.put(it.getName(), itmap);
		}
        JSONObject jo = JSONObject.fromObject(map);
        System.out.println(jo);
	}
}
