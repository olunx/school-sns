package cn.gdpu.service;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.A;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.GoodsType;



public class GoodsTypeServiceTest{
	private static GoodsTypeService<GoodsType, Integer> goodsTypeService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			goodsTypeService = (GoodsTypeService<GoodsType, Integer>) ctx.getBean("goodsTypeService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		GoodsType goodsType = new GoodsType();
		goodsType.setName("数码产品");
		goodsTypeService.addEntity(goodsType);
		GoodsType goodsType1 = new GoodsType();
		goodsType1.setName("玩具");
		goodsTypeService.addEntity(goodsType1);
		GoodsType goodsType2 = new GoodsType();
		goodsType2.setName("体育用品");
		goodsTypeService.addEntity(goodsType2);
	}
	@Test
	public void list() {
		List<GoodsType> list = goodsTypeService.getAllEntity(GoodsType.class);
		for(GoodsType gt : list){
			System.out.println("---" + gt.getName());
			if(gt.getGoods().size() > 0 ){
				for(Goods goods : gt.getGoods()){
					System.out.println("----" + goods.getName());
				}
			}
		}
	}
}
