package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.ImageService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.TwitterService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Twitter;

@SuppressWarnings("serial")
public class TwitterAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private int otherId;
	private Twitter twitter;
	private Image image;
	private ImageService<Image, Integer> imageService;
	private TwitterService<Twitter, Integer> twitterService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private PageBean pageBean;
	private int page;

	
	@Override
	public String goAdd() {
		
		//获取问答类型
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
		getRequest().put("jsonmap", jo);

		//获取物品类型
		List<GoodsType> list = goodsTypeService.getAllEntity(GoodsType.class);
		getRequest().put("goodsType", list);

		
		return super.goAdd();
	}

	@Override
	public String add() {
		People author = (People) this.getSession().get("user");
		if (author != null) {
			Log.init(getClass()).info("people name " + author.getName());
			twitter.setAuthor(author);
			twitter.setTime(new Date());
			twitter.setIstopic(true);

			if (image != null) {
				Log.init(getClass()).info("add topic image " + image);
				imageService.addEntity(image);
				twitter.setImage(image);
			}

			twitterService.addEntity(twitter);
			
			FeedAction.init().add(twitter, FeedAction.ADD_TWITTER);
		}

		Log.init(getClass()).info("add finish ");

		return super.add();
	}

	public String addMy() {
		this.add();
		return "listMy";
	}

	public String goReply() {
		return "replyPage";
	}

	public String reply() {

		People author = (People) this.getSession().get("user");
		if (author != null) {
			Log.init(getClass()).info("people name " + author.getName());
			twitter.setAuthor(author);
			twitter.setTime(new Date());
			twitter.setIstopic(false);
			twitterService.addEntity(twitter);

			Twitter parent = twitterService.getEntity(Twitter.class, id);
			List<Twitter> list = parent.getReply();
			if (list == null) {
				list = new ArrayList<Twitter>();
			}
			list.add(twitter);
			parent.setReply(list);
			parent.setHasreply(true);
			twitterService.updateEntity(parent);

			FeedAction.init().add(twitter, FeedAction.REPLY_TWITTER);
		}

		Log.init(getClass()).info("add finish ");

		return super.add();
	}

	@Override
	public String delete() {
		twitterService.deleteEntity(Twitter.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		twitterService.deleteManyEntity(Twitter.class, ids);
		return super.deleteMany();
	}

	@Override
	public String list() {
		Log.init(getClass()).info("list ");
		this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' order by t.time DESC", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}
		Log.init(getClass()).info("list finish");
		return super.list();
	}

	public String listMy() {
		Log.init(getClass()).info("listMy");
		People author = (People) this.getSession().get("user");
		if (author != null) {
			this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' and t.author = '" + author.getId()
					+ "' order by t.time DESC", 10, page);
			if (pageBean.getList().isEmpty()) {
				pageBean.setList(null);
			}
		}

		Log.init(getClass()).info("listMy finish");

		return super.list();
	}

	public String listOther() {
		Log.init(getClass()).info("listOther");
		this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' and t.author = '" + otherId
				+ "' order by t.time DESC", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}

		Log.init(getClass()).info("listOther finish");
		return super.list();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public int getOtherId() {
		return otherId;
	}

	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageService<Image, Integer> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
	}

	public TwitterService<Twitter, Integer> getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterService<Twitter, Integer> twitterService) {
		this.twitterService = twitterService;
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

	public IssueTypeService<IssueType, Integer> getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService<IssueType, Integer> issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public GoodsTypeService<GoodsType, Integer> getGoodsTypeService() {
		return goodsTypeService;
	}

	public void setGoodsTypeService(GoodsTypeService<GoodsType, Integer> goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}

}
