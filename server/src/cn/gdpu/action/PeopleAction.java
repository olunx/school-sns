package cn.gdpu.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.MutualFriend;
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
	private String oldPassword;
	private String rePassword;
	private String moreAction;

	@Override
	public String add() {
		peopleService.addEntity(people);

		return super.add();
	}

	@SuppressWarnings("unchecked")
	public String addManyStudent() {
		Log.init(getClass()).info("useraaaaa");
		People user = (People) this.getSession().get("user");
		if (user != null) {
			user = peopleService.getEntity(People.class, user.getId());
			Classes classes = user.getClasses();
			Institute institute = user.getInstitute();
			School school = user.getSchool();
			Log.init(getClass()).info("classes" + classes);
			Log.init(getClass()).info("school" + school);

			List<String> fileList = (List<String>) this.getRequest().get("targetsFilePath");
			Log.init(getClass()).info("fileList" + fileList);
			List<Student> peopleList = new ArrayList<Student>();
			if (fileList.size() > 0) {
				peopleList = StudentExcel.getStudentExcel().getStudentData(fileList.get(0));
				Log.init(getClass()).info("peopleList" + peopleList);
			}

			// 删除自己，添加学校学院班级的值
			Log.init(getClass()).info("remove");
			int length = peopleList.size();
			String username = user.getUsername();
			String name;
			People p;
			for (int i = 0; i < length; i++) {
				p = peopleList.get(i);
				name = p.getUsername();
				if (name.equalsIgnoreCase(username) || name == username) {
					peopleList.remove(i);
					length = peopleList.size();
				}else {
					p.setClasses(classes);
					p.setInstitute(institute);
					p.setSchool(school);
					peopleService.addEntity(p);
				}
			}
		}
		Log.init(getClass()).info("addManyStudent finish");
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
		People me = (People) this.getSession().get("user");
		if (friend != null && me != null) {
			me = peopleService.getEntity(People.class, me.getId());
			Set<People> myFriends = me.getFriends();
			Set<People> friendFollowers = friend.getFollower();

			if (myFriends.contains(friend)) {// 如果是朋友就删除
				myFriends.remove(friend);
				if (friendFollowers.contains(me)) friendFollowers.remove(me);
				FeedAction.init().add(me, friend, FeedAction.DEL_FRIEND);
			} else {
				myFriends.add(friend);
				if (!friendFollowers.contains(me)) friendFollowers.add(me);
				FeedAction.init().add(me, friend, FeedAction.ADD_FRIEND);
			}
			
			//me.setMutualFriends(findMutual(me, friend));//检查共同好友

			me.setFriends(myFriends);
			friend.setFollower(friendFollowers);
			Log.init(getClass()).debug("myFriends:" + myFriends);
			peopleService.updateEntity(me);
			peopleService.updateEntity(friend);

		}
		return "list";
	}
	
	/*//添加共同好友
	public List<MutualFriend> findMutual(People me, People friend){
		List<MutualFriend> mfs = me.getMutualFriends();
		if(mfs==null){
			mfs = new ArrayList<MutualFriend>();
		}
		Set<People> myfriends = me.getFriends();
		for(Iterator<People> oit = friend.getFriends().iterator(); oit.hasNext();){
			People of = oit.next();
			if(!myfriends.contains(of)){
				if(mfs.size() == 0){
					MutualFriend mf = new MutualFriend();
					mf.setPeople(of);
					Set<People> mu = new HashSet<People>();
					mu.add(friend);
					mf.setMutual(mu);
					mfs.add(mf);
				}else{
					int index = -1;
					for(int i =0; i<mfs.size(); i++){
						MutualFriend a = mfs.get(i);
						if(of.getId() == a.getPeople().getId()){
							index = i;
							break;
						}
					}
					if(index != -1){
						MutualFriend mf = mfs.get(index);
						Set<People> mu = mf.getMutual();
						if(!mu.contains(friend))
							mu.add(friend);
						mf.setMutual(mu);
						mfs.set(index, mf);
					}
				}
			}
		}
		System.out.println("mfs size = " + mfs.size());
		return mfs;
	}*/


	// 检查是否是朋友
	public static Boolean isMyFriend(Set<People> set, People people) {
		Log.init(People.class).debug("set:" + set + " people:" + people);
		if (set != null && people != null && set.contains(people)) {
			return true;
		}
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
		People user = (People) this.getSession().get("user");
		if (user != null) {
			user = peopleService.getEntity(People.class, user.getId());
			this.getRequest().put("friends", user.getFriends());
		}
		this.pageBean = this.peopleService.queryForPage(People.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);

		moreAction = "listPeople";
		
		return super.list();
	}

	/**
	 *查找学校的所有成员
	 */
	public String listSchool() {
		School school = schoolService.getEntity(School.class, id);
		String hql = "from People p where p.school.id ='" + school.getId() + "' order by p.activity DESC";
		this.pageBean = this.peopleService.queryForPage(hql, 20, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		moreAction = "listSchoolPeople";
		return super.list();
	}

	/**
	 *查找班级的所有成员
	 */
	public String listClasses() {
		People user = (People) this.getSession().get("user");
		if (user != null) {
			user = peopleService.getEntity(People.class, user.getId());
			this.getRequest().put("friends", user.getFriends());
		}
		Classes classes = classesService.getEntity(Classes.class, id);
		String hql = "from People p where p.classes.id ='" + classes.getId() + "' order by p.activity DESC";
		this.pageBean = this.peopleService.queryForPage(hql, 20, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		moreAction = "listClassesPeople";
		return super.list();
	}

	@Override
	public String goModify() {
		people = peopleService.getEntity(People.class, id);
		return super.goModify();
	}

	@Override
	public String modify() { // 修改个人资料
		People onepeople = peopleService.getEntity(People.class, people.getId());
		onepeople.setName(people.getName());
		onepeople.setEmail(people.getEmail());
		onepeople.setPhoneNo(people.getPhoneNo());
		onepeople.setNickname(people.getNickname());
		onepeople.setQq(people.getQq());
		onepeople.setDorm(people.getDorm());
		peopleService.updateEntity(onepeople);
		getRequest().put("modifysuc", true);
		getRequest().put("people", onepeople);
		return MODIFY_PAGE;
	}

	public String modifyPSW() { // 修改密码
		People onepeople = peopleService.getEntity(People.class, people.getId());

		if (!oldPassword.trim().equals(onepeople.getPassword())) { // 验证旧密码
			this.addFieldError("oldPassword", "旧密码不正确");
		}
		if (hasFieldErrors()) {
			getRequest().put("people", onepeople);
			return MODIFY_PAGE;
		}

		if (people.getPassword().trim().equals(rePassword) && oldPassword.trim().equals(onepeople.getPassword())) {
			onepeople.setPassword(people.getPassword());
			peopleService.updateEntity(onepeople);
			getRequest().put("modifypswsuc", true);
		}
		getRequest().put("people", onepeople);
		return MODIFY_PAGE;
	}

	public String modifyAvatar() { // 修改头像
		People onepeople = peopleService.getEntity(People.class, people.getId());
		Log.init(getClass()).info(image);
		Log.init(getClass()).info("image.getMinFileUrl()" + image.getMinFileUrl());
		imageService.addEntity(image);
		onepeople.setAvatar(image);
		peopleService.updateEntity(onepeople);
		getRequest().put("modifyavatarsuc", true);
		getRequest().put("people", onepeople);
		return MODIFY_PAGE;
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
				String hql;
				if (search.trim().equals("")) {
					hql = "from People";
				} else {
					hql = "from People p where p.id<>'" + people.getId() + "' and ( p.name like '%" + search + "%' or p.username like '%"
							+ search + "%' or p.sno like '%" + search + "%' ) order by p.activity DESC";
				}
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if (pageBean.getList().isEmpty())
					pageBean.setList(null);
			}
		}
		return super.list();

	}

	public String listFriend() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if (id == 0) {
					people = (People) author;
					people = peopleService.getEntity(People.class, ((People)author).getId());
				} else {
					people = peopleService.getEntity(People.class, id);
				}
					
				this.getRequest().put("friends", people.getFriends());
				
				String hql = "select friends from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if (pageBean.getList().isEmpty())
					pageBean.setList(null);
				return super.list();
			}
		}
		return ERROR;
	}

	public String listFollower() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if (id == 0) {
					people = (People) author;
					people = peopleService.getEntity(People.class, ((People)author).getId());
				} else {
					people = peopleService.getEntity(People.class, id);
				}
				this.getRequest().put("friends", people.getFriends());
				
				String hql = "select follower from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				//String hql = "from People p where (from People me where me.id = "+people.getId()+") in elements(p.friends)";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if (pageBean.getList().isEmpty())
					pageBean.setList(null);
				return super.list();
			}
		}
		return ERROR;
	}

	public String listVisitor() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people;
				if (id == 0) {
					people = (People) author;
				} else {
					people = peopleService.getEntity(People.class, id);
				}
				String hql = "select visitors from People p where p.id = '" + people.getId() + "' order by p.activity DESC";
				this.pageBean = this.peopleService.queryForPage(hql, 30, page);
				if (pageBean.getList().isEmpty())
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getMoreAction() {
		return moreAction;
	}

	public void setMoreAction(String moreAction) {
		this.moreAction = moreAction;
	}

}
