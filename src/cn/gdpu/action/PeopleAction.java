package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.gdpu.service.ImageService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class PeopleAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private People people;
	private PeopleService<People, Integer> peopleService;
	private PageBean pageBean;
	private int page;
	private Image image;
	private ImageService<Image, Integer> imageService;

	@Override
	public String add() {
		peopleService.addEntity(people);

		return super.add();
	}

	@SuppressWarnings("unchecked")
	public String addMany() {
		List<String> fileList = (List<String>) this.getRequest().get("targetsFilePath");
		Log.init(getClass()).info("fileList" + fileList);
		List<People> peopleList = new ArrayList<People>();
		if (fileList.size() > 0) {
			peopleList = StudentExcel.getStudentExcel().getStudentData(fileList.get(0));
			Log.init(getClass()).info("peopleList" + peopleList);
		}
		for (People s : peopleList) {
			peopleService.addEntity(s);
		}

		return super.add();
	}

	@Override
	public String delete() {
		this.peopleService.deleteEntity(People.class, id);
		return super.delete();
	}

	// 加为好友
	public String follow() {
		People friend = peopleService.getEntity(People.class, id);
		People me = (People) this.getSession().get("student");
		if (friend != null && me != null) {
			me = peopleService.getEntity(People.class, me.getId());
			Set<People> myFriends = me.getFriends();
			if (myFriends.contains(friend)) {// 如果是朋友就删除
				myFriends.remove(friend);
				FeedAction.init().add(me, friend, FeedAction.DEL_FRIEND);
			} else {
				myFriends.add(friend);
				FeedAction.init().add(me, friend, FeedAction.ADD_FRIEND);
			}
			me.setFriends(myFriends);
			peopleService.updateEntity(me);
		}
		return "list";
	}

	// 检查是否是朋友
	public static Boolean isMyFriend(Set<People> set, People people) {
		if (set != null && people != null && set.contains(people))
			return true;
		return false;
	}

	// 检查是否我的群组
	public static Boolean isMyGroup(Set<Group> set, Group group) {
		System.out.println("set " + set);
		System.out.println("group " + group);
		if (set != null && group != null && set.contains(group))
			return true;
		return false;
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		peopleService.deleteManyEntity(People.class, ids);
		return super.deleteMany();
	}

	@Override
	public String list() {
		People people = (People) this.getSession().get("user");
		if (people != null) {
			people = peopleService.getEntity(People.class, people.getId());
			this.getRequest().put("friends", people.getFriends());
		}
		this.pageBean = this.peopleService.queryForPage(People.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);

		return super.list();
	}

	@Override
	public String goModify() {
		people = peopleService.getEntity(People.class, id);
		return super.goModify();
	}

	@Override
	public String modify() {
		Log.init(getClass()).info(image);
		Log.init(getClass()).info("image.getMinFileUrl()" + image.getMinFileUrl());
		imageService.addEntity(image);
		people.setAvatar(image);
		peopleService.updateEntity(people);
		return super.modify();
	}

	@Override
	public String view() {
		people = peopleService.getEntity(People.class, id);
		Log.init(getClass()).info("People.getAvatar() " + people.getAvatar());
		return super.view();
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

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
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

}
