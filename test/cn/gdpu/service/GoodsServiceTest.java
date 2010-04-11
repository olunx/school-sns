package cn.gdpu.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.Goods;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Topic;



public class GoodsServiceTest{
	private static GoodsService<Goods, Integer> goodsService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			goodsService = (GoodsService<Goods, Integer>) ctx.getBean("goodsService");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		Goods goods = new Goods();
		goods.setContent("this is a test goods");
		goods.setName("mp3");
		goods.setState(0);
		goods.setImage("image");
		Student owner = new Student();
		goods.setOwner(owner);
		goods.setQuantity(10);
		goods.setRecord("创建货品");
		Topic reply1 = new Topic();
		Topic reply2 = new Topic();
		List<Topic> replys = new ArrayList<Topic>();
		replys.add(reply1);
		replys.add(reply2);
		goods.setReply(replys);
		goods.setValue(300);
		GoodsType goodsType = new GoodsType();
		goodsType.setName("数码产品");
		goods.setGoodsType(goodsType);
		goodsService.addEntity(goods);
	}
	
	@Test
	public void delete() {
		goodsService.deleteEntity(Goods.class, 1);
	}
}
