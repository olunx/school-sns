package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gdpu.service.GoodsService;
import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Topic;

public class GoodsAction extends BaseAction {

	private GoodsService<Goods, Integer> goodsService;
	private TopicService<Topic, Integer> topicService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private Goods goods;
	private GoodsType goodsType;
	private Topic reply;
	private PageBean pageBean;
	private int page;
	private int id;
	private int rid;
	private int gtid;
	private String gsType[];

	@Override
	public String add() {
		Object student = this.getSession().get("user");
		if (student != null) {
			if (student instanceof Student) {
				Student stu = (Student) student;
				goods.setOwner(stu);
			}
			String record = goods.getRecord();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			goods.setAirTime(date);
			String str = sdf.format(date);
			record = " " + str + ":创建货品:" + goods.getName();
			goods.setRecord(record);
			goodsType = goodsTypeService.getEntity(GoodsType.class, gtid);
			goods.setGoodsType(goodsType);
			if(goods.getState() == 1){
				String goodsType = "";
				for(int i=0 ;i<gsType.length; i++ ){
					goodsType +=gsType[i];
					if(i != gsType.length-1)
						goodsType +=",";
				}
				goods.setExchange(goodsType);
			}
			goodsService.addEntity(goods);
		}
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
		List<GoodsType> list = goodsTypeService.getAllEntity(GoodsType.class);
		getRequest().put("goodsType", list);
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
		Object student = this.getSession().get("user");
		if (student != null) {
			if (student instanceof Student) {
				Student stu = (Student) student;
				String hql = "from Goods g where g.owner.name ='" + stu.getName() + "'";
				this.pageBean = goodsService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				System.out.println("goodshot = dddddddddddddddddddd");
				hql = "from Goods g where g.owner.name <>'" + stu.getName() + "' order by g.hot DESC limit 5";
				System.out.println("goodshot = hql == " + hql);
				List<Goods> goodshot = goodsService.getEntity(Goods.class, hql);
				if(goodshot.isEmpty() || goodshot.size()==0){
					goodshot = null;
				}
				System.out.println("goodshot = " + goodshot);
				getRequest().put("goodshot", goodshot);
				hql = "from Goods g where g.owner.name <>'" + stu.getName() + "' order by g.airTime DESC limit 5";
				System.out.println("goodshot = hql == " + hql);
				List<Goods> goodsnew = goodsService.getEntity(Goods.class, hql);
				System.out.println("goodsnew.size() = " + goodsnew.size());
				if(goodsnew.isEmpty() || goodsnew.size()==0){
					goodsnew = null;
				}
				System.out.println("goodsnew = " + goodsnew);
				getRequest().put("goodsnew", goodsnew);
			}
		}
		return INDEX;
	}
	
	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

	@Override
	public String view() {
		Student stu =null;
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof Student) {
				stu = (Student) author;
				goods = goodsService.getEntity(Goods.class, id);
				goods.setHot(goods.getHot() + 1);
				goodsService.updateEntity(goods);
				if(goods.getState() == 1){
					gsType = goods.getExchange().split(",");
					String hql = "from Goods g where g.owner.name <>'" + stu.getName() + "' and g.state <>'1' and (g.goodsType.name ='" + gsType[0] + "'";
					if(gsType.length != 0){
						for(int i=1;i<gsType.length;i++){
							hql += " or g.goodsType.name ='" + gsType[i] + "'";
						}
					}
					hql += ") order by g.hot DESC";
					List<Goods> goodslist = goodsService.getEntity(Goods.class, hql);
					if(goodslist.isEmpty() || goodslist.size()==0)
						goodslist = null;
					getRequest().put("goodslist", goodslist);
				}
			}
		}
		return super.view();
	}

	public String goReply() {
		return "replyPage";
	}

	public String reply() {
		if(rid == -1){
			Object author = this.getSession().get("user");
			if (author != null) {
				if (author instanceof People) {
					People people = (People) author;
					Log.init(getClass()).info("people name " + people.getName());
					reply.setAuthor(people);
				}
				reply.setTime(new Date());
				reply.setIstopic(true);
	
				goods = goodsService.getEntity(Goods.class, id);
				List<Topic> replys = goods.getReply();
				if (replys == null) {
					replys = new ArrayList<Topic>();
				}
				replys.add(reply);
				goods.setReply(replys);
				goodsService.updateEntity(goods);
			}
	
			Log.init(getClass()).info("add reply finish ");
		}
		else{
			Object author = this.getSession().get("user");
			if (author != null) {
				if (author instanceof People) {
					People people = (People) author;
					Log.init(getClass()).info("people name " + people.getName());
					reply.setAuthor(people);
				}
				reply.setTime(new Date());
				reply.setIstopic(false);
				topicService.addEntity(reply);

				Topic parent = topicService.getEntity(Topic.class, rid);
				List<Topic> list = parent.getReply();
				if (list == null) {
					list = new ArrayList<Topic>();
				}
				list.add(reply);
				parent.setReply(list);
				parent.setHasreply(true);
				topicService.updateEntity(parent);
			}
			goods = goodsService.getEntity(Goods.class, id);
			Log.init(getClass()).info("add subreply finish ");
		}

		return VIEW_PAGE;
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public TopicService<Topic, Integer> getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService<Topic, Integer> topicService) {
		this.topicService = topicService;
	}

	public Topic getReply() {
		return reply;
	}

	public void setReply(Topic reply) {
		this.reply = reply;
	}

	public GoodsTypeService<GoodsType, Integer> getGoodsTypeService() {
		return goodsTypeService;
	}

	public void setGoodsTypeService(GoodsTypeService<GoodsType, Integer> goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}

	public String[] getGsType() {
		return gsType;
	}

	public void setGsType(String[] gsType) {
		this.gsType = gsType;
	}
	
	public int getGtid() {
		return gtid;
	}

	public void setGtid(int gtid) {
		this.gtid = gtid;
	}
	
}
