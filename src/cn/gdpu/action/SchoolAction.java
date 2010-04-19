package cn.gdpu.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.gdpu.service.PeopleService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Visitor;

@SuppressWarnings("serial")
public class SchoolAction extends BaseAction {
	private SchoolService<School, Integer> schoolService;
	private PeopleService<People, Integer> peopleService;
	private School school;
	private Image image;
	private String content;
	private String address;
	private int id;

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return super.add();
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		// TODO Auto-generated method stub
		return super.goAdd();
	}

	@Override
	public String goModify() {
		return super.goModify();
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	@Override
	public String modify() {
		school = schoolService.getEntity(School.class, id);
		school.setAvatar(image);
		school.setContent(content);
		school.setAddress(address);
		schoolService.updateEntity(school);
		return VIEW_PAGE;
	}

	@Override
	public String view() {
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				school = schoolService.getEntity(School.class, id);
				boolean isAdmin = false;
				for(People peo : school.getAdmin()){
					if(peo.getUsername().trim().equals(user.getUsername().trim()))
						isAdmin = true;
				}
				getRequest().put("isAdmin", isAdmin);
				
				List<Visitor> visitors = (List<Visitor>) school.getVisitor();
				Visitor visitor = new Visitor();
				visitor.setPeople(user);
				visitor.setTime(new Date());
				boolean ishas =false;
				for(int i=0; i<visitors.size();i++){
					if(visitors.get(i).getPeople().getId() == user.getId()){
						visitors.set(i, visitor);
						ishas=true;
					}
				}
				if(ishas != true){
					if(visitors.size()>=10){
						visitors.remove(0);
					}
					visitors.add(visitor);
				}
				school.setVisitor(visitors);
				schoolService.updateEntity(school);
				Log.init(getClass()).info(user.getName() + "访问学校: " + school.getName());
				
				//人气王
				String hql = "from People p where p.school.id ='" + school.getId()+ "' order by p.activity DESC limit 10";
				List<People> peoplehot = peopleService.getEntity(People.class, hql);
				if(peoplehot.isEmpty() || peoplehot.size()==0){
					peoplehot = null;
				}
				getRequest().put("peoplehot", peoplehot);
				
				//学校新人
				hql = "from People p where p.school.id ='" + school.getId()+ "' order by p.regTime DESC limit 10";
				List<People> peoplenew = peopleService.getEntity(People.class, hql);
				if(peoplenew.isEmpty() || peoplenew.size()==0){
					peoplenew = null;
				}
				getRequest().put("peoplenew", peoplenew);
				return super.view();
			}
		}
		return ERROR;
	}
	
	public String joinAdmin(){
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				People user = (Student) student;
				school = schoolService.getEntity(School.class, id);
				
				List<People> admins = school.getAdmin();
				
				boolean ishas =false;         //加入管理员，最多三人
				for(int i=0; i<admins.size();i++){
					if(admins.get(i).getId() == user.getId()){
						admins.set(i, user);
						ishas=true;
					}
				}
				if(ishas != true){
					if(admins.size()>=3){
						admins.remove(0);
					}
					admins.add(user);
				}
				school.setAdmin(admins);
				
				schoolService.updateEntity(school);
				Log.init(getClass()).info(user.getName() + "成为 " + school.getName() + " 学校管理员");
			}
		}
		
		return "view";
	}

	public SchoolService<School, Integer> getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService<School, Integer> schoolService) {
		this.schoolService = schoolService;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
