package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gdpu.service.GoodsService;
import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.ImageService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class GoodsAction extends BaseAction {

	private GoodsService<Goods, Integer> goodsService;
	private TopicService<Topic, Integer> topicService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private ImageService<Image, Integer> imageService;
	private Goods goods;
	private GoodsType goodsType;
	private Image image;
	private Topic reply;
	private PageBean pageBean;
	private int page;
	private int id;
	private int rid;
	private int gtid;
	private String gsType[];
	private String search;

	@Override
	public String add() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				goods.setOwner(user);
			}
			System.out.println("image url = " + image);
			goods.setImage(image);
			System.out.println("image -----------url = " + image.getMinFileUrl());
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
			FeedAction.init().add(goods, FeedAction.ADD_GOODS);
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
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				String hql = "from Goods g where g.owner.name ='" + user.getName() + "'";
				this.pageBean = goodsService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				hql = "from Goods g where g.owner.name <>'" + user.getName() + "' order by g.hot DESC limit 5";
				List<Goods> goodshot = goodsService.getEntity(Goods.class, hql);
				if(goodshot.isEmpty() || goodshot.size()==0){
					goodshot = null;
				}
				getRequest().put("goodshot", goodshot);
				hql = "from Goods g where g.owner.name <>'" + user.getName() + "' order by g.airTime DESC limit 5";
				List<Goods> goodsnew = goodsService.getEntity(Goods.class, hql);
				if(goodsnew.isEmpty() || goodsnew.size()==0){
					goodsnew = null;
				}
				getRequest().put("goodsnew", goodsnew);
				List<GoodsType> goodsType = goodsTypeService.getAllEntity(GoodsType.class);
				if(goodsType.isEmpty() || goodsType.size()==0){
					goodsType = null;
				}
				getRequest().put("goodsType", goodsType);
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
		People user = null;
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				user = (Student) author;
				goods = goodsService.getEntity(Goods.class, id);
				goods.setHot(goods.getHot() + 1);
				goodsService.updateEntity(goods);
				
				if(goods.getState() == 1){
					gsType = goods.getExchange().split(",");
					String hql = "from Goods g where g.owner.name <>'" + user.getName() + "' and g.state <>'1' and (g.goodsType.name ='" + gsType[0] + "'";
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

	public String search() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				String hql = "";
				if(gtid == -1){
					hql = "from Goods g where g.owner.name <>'" + people.getName() + "' and g.name like '%" + search + "%' order by g.hot DESC";
				}
				else{
					hql = "from Goods g where g.owner.name <>'" + people.getName() + "' and g.name like '%" + search + "%' and g.goodsType.id ='" + gtid + "' order by g.hot DESC";
				}
				this.pageBean = this.goodsService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
			}
		}
		return "listall";
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public ImageService<Image, Integer> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
