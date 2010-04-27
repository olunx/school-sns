package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import cn.gdpu.service.ClassesService;
import cn.gdpu.service.ImageService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class PeopleAction extends BaseAction {

	private int id;
	private int schoolId;
	private int classesId;
	private Integer[] ids;
	private String search;
	private People people;
	private PeopleService<People, Integer> peopleService;
	private SchoolService<School, Integer> schoolService;
	private ClassesService<Classes, Integer> classesService;
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
		List<Student> peopleList = new ArrayList<Student>();
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
			Set<People> followers = friend.getFollower();
			
			if (myFriends.contains(friend)) {// 如果是朋友就删除
				myFriends.remove(friend);
				FeedAction.init().add(me, friend, FeedAction.DEL_FRIEND);
			} else {
				myFriends.add(friend);
				FeedAction.init().add(me, friend, FeedAction.ADD_FRIEND);
			}
			
			if (followers.contains(me)) {// 如果已经被关注就删除
				followers.remove(me);
			} else {
				followers.add(me);
			}
			
			me.setFriends(myFriends);
			peopleService.updateEntity(me);
			
			friend.setFollower(followers);
			peopleService.updateEntity(friend);
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
		System.out.println("group " + group.getName());
		System.out.println("group set " + set);
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
		People onepeople = peopleService.getEntity(People.class, people.getId());
		Log.init(getClass()).info(image);
		Log.init(getClass()).info("image.getMinFileUrl()" + image.getMinFileUrl());
		imageService.addEntity(image);
		onepeople.setAvatar(image);
		onepeople.setUsername(people.getUsername());
		onepeople.setPassword(people.getPassword());
		onepeople.setName(people.getName());
		onepeople.setDorm(people.getDorm());
		peopleService.updateEntity(onepeople);
		return super.modify();
	}

	@Override
	public String view() {
		people = peopleService.getEntity(People.class, id);
		Log.init(getClass()).info("People.getAvatar() " + people.getAvatar());
		return super.view();
	}

	public String search() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				String hql = "from People p where p.id<>'" + people.getId() + "' and ( p.name like '%" + search + "%' or p.username like '%" + search + "%' or p.sno like '%" + search + "%' ) order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
			}
		}
		return super.list();

	}
	
	/**
	 *查找学校的所有成员
	 */
	public String school() {
		School school = schoolService.getEntity(School.class, id);
		String hql = "from People p where p.school.id ='" + school.getId() + "' order by p.activity DESC";
		this.pageBean = this.peopleService.queryForPage(hql, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return super.list();
	}
	
	/**
	 *查找班级的所有成员
	 */
	public String classes() {
		Classes classes= classesService.getEntity(Classes.class, id);
		String hql = "from People p where p.classes.id ='" + classes.getId() + "' order by p.activity DESC";
		this.pageBean = this.peopleService.queryForPage(hql, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return super.list();

	}
	
	public String listFriend(){
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if(id == 0){
					people = (People) author;
				}
				else{
					people = peopleService.getEntity(People.class, id);
				}
				String hql = "select friends from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				return super.list();
			}
		}
		return ERROR;
	}
	
	public String listFollower(){
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if(id == 0){
					people = (People) author;
				}
				else{
					people = peopleService.getEntity(People.class, id);
				}
				String hql = "select follower from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				return super.list();
			}
		}
		return ERROR;
	}
	
	public String listVisitor(){
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if(id == 0){
					people = (People) author;
				}
				else{
					people = peopleService.getEntity(People.class, id);
				}
				String hql = "select visitors from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				return "listvisitor";
			}
		}
		return ERROR;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public SchoolService<School, Integer> getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService<School, Integer> schoolService) {
		this.schoolService = schoolService;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public ClassesService<Classes, Integer> getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService<Classes, Integer> classesService) {
		this.classesService = classesService;
	}

}
