package cn.gdpu.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.action.FeedAction;
import cn.gdpu.vo.*;

public class TwitterServiceTest {
	private static TwitterService<Twitter, Integer> twitterService;
	private static StudentService<Student, Integer> studentService;
	private static Log logger = LogFactory.getLog("TwitterServiceTest");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			twitterService = (TwitterService<Twitter, Integer>) ctx.getBean("twitterService");
			studentService = (StudentService<Student, Integer>) ctx.getBean("studentService");
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		
		try {
			List<Twitter> list = getQQ("fatkun");
			addTwitter("0707501112",list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Twitter> getQQ(String username) throws IOException{
		List<Twitter> list = new ArrayList<Twitter>();
		URL url = new URL("http://q.hzlzh.com/"+username);
		InputStream is = url.openStream();
		StringBuilder sb = new StringBuilder();
		int c = is.read();
		while (c!=-1){
			sb.append((char)c);
			c = is.read();
		}
		is.close();
		logger.info(sb);
		
		JSONArray json = JSONObject.fromObject(sb.toString()).getJSONArray("contents");
		for (int i=0; i<json.size();i++){
			JSONObject jitem = (JSONObject)json.get(i);
			String content = jitem.getString("content");
			String time = jitem.getString("time");
			Twitter t = new Twitter();
			t.setContent(content);
			t.setTime(new Date(time));
			t.setIstopic(true);
			list.add(t);
			logger.info(content);
		}
		
		return list;
	}
	
	//username:用户名
	public void addTwitter(String username,List<Twitter> list){
		Student p = studentService.getStudentByUserName(username);
		for (Twitter t : list){
			t.setAuthor(p);
			twitterService.addEntity(t);
			FeedAction.init().add(t, FeedAction.ADD_TWITTER);
		}
	}

}
