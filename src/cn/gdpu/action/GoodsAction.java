package cn.gdpu.action;

import java.util.Date;

import cn.gdpu.service.GoodsService;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.Student;

public class GoodsAction extends BaseAction {

	private GoodsService<Goods, Integer> goodsService;
	private Goods goods;
	private PageBean pageBean;
	private int page;
	private int id;
	
	
	

	@Override
	public String add() {
		Student owner = (Student) getSession().get("student");
		if(owner == null)
			return ERROR;
		goods.setOwner(owner);
		String record = goods.getRecord();
		record = " " + new Date() + ":创建货品:" + goods.getName();
		goods.setRecord(record);
		goodsService.addEntity(goods);
		return super.add();
	}

	@Override
	public String delete() {
		goodsService.deleteEntity(Goods.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		return super.goAdd();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		this.pageBean = this.goodsService.queryForPage(Goods.class, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return "listall";
	}

	public String listme() {
		Student stu = (Student) getSession().get("student");
		String hql = "from Goods g where g.owner.name ='" + stu.getName() + "'";
		this.pageBean = this.goodsService.queryForPage(hql, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return INDEX;
	}
	
	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

	@Override
	public String view() {
		goods = goodsService.getEntity(Goods.class, id);
		return super.view();
	}

	
	//setter and getter
	public GoodsService<Goods, Integer> getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService<Goods, Integer> goodsService) {
		this.goodsService = goodsService;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
