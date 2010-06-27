package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gdpu.service.ImageService;
import cn.gdpu.service.TwitterService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Twitter;

@SuppressWarnings("serial")
public class TwitterAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private int otherId;
	private Twitter twitter;
	private List<Twitter> twitterList;
	private Image image;
	private ImageService<Image, Integer> imageService;
	private TwitterService<Twitter, Integer> twitterService;
	private PageBean pageBean;
	private int page;

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
//		Log.init(getClass()).info("list ");
//		this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' order by t.time DESC", 10, page);
//		if (pageBean.getList().isEmpty()) {
//			pageBean.setList(null);
//		}
//		Log.init(getClass()).info("list finish");
		return "wall";
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
		if (otherId == 0) return list();
		id = otherId;
		this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' and t.author = '" + otherId
				+ "' order by t.time DESC", 10, page);
		if (pageBean.getList().isEmpty()) {
			pageBean.setList(null);
		}

		Log.init(getClass()).info("listOther finish");
		return super.list();
	}
	
	/**
	 * 微博墙
	 * @return
	 */
	public String twitterWall() {
		List<Twitter> tempList = new ArrayList<Twitter>();
		twitterList = twitterService.queryForLimit("from Twitter t where t.istopic = '1' order by t.time DESC", 0, 15);
		Log.init(getClass()).info(twitterList);
		return SUCCESS;
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

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
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

	public List<Twitter> getTwitterList() {
		return twitterList;
	}

	public void setTwitterList(List<Twitter> twitterList) {
		this.twitterList = twitterList;
	}

}
