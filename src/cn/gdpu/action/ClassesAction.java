package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;

import cn.gdpu.service.ClassesService;
import cn.gdpu.service.InstituteService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.People;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Topic;
import cn.gdpu.vo.Visitor;

@SuppressWarnings("serial")
public class ClassesAction extends BaseAction implements Preparable{
	private SchoolService<School, Integer> schoolService;
	private InstituteService<Institute, Integer> instituteService;
	private ClassesService<Classes, Integer> classesService;
	private PeopleService<People, Integer> peopleService;
	private TopicService<Topic, Integer> topicService;
	private School school;
	private Institute institute;
	private Classes classes;
	private Topic reply;
	private String content;
	private int id;
	private int rid;
	private int audit;

	@Override
	public void prepare() throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) ServletActionContext.getRequest();
		String action=  httpRequest.getServletPath().split("/")[2];
		String[] uri=action.split("\\.");
		if(uri[0].equals("addClasses")){
			Object student = this.getSession().get("student");
			if (student != null) {
				if (student instanceof Student) {
					People user = (People) student;
					school = user.getSchool();
					Set<Institute> institutes = school.getInstitute();
					getRequest().put("institutes", institutes);
				}
			}
		}
	}
	
	@Override
	public String add() {
		institute = instituteService.getEntity(Institute.class, id);
		classes.setInstitute(institute);
		classesService.addEntity(classes);
		return "add";
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
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				People user = (People) student;
				school = user.getSchool();
				Set<Institute> institutes = school.getInstitute();
				getRequest().put("institutes", institutes);
				return super.goAdd();
			}
		}
		return ERROR;
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

	@Override
	public String view() {
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				
				if(id == -1){							//未加入班级									
					return "goPerfectReg";
				}
				
				if(user.getPermission() == 1){          //未审核通过不能访问
					return ERROR;
				}
				classes = classesService.getEntity(Classes.class, id);
				boolean isAdmin = false;
				for(People peo:classes.getAdmins()){
					if(peo.getId() == user.getId()){
						isAdmin = true;
						break;
					}
				}
				getRequest().put("isAdmin", isAdmin);
				
				List<Visitor> visitors = (List<Visitor>) classes.getVisitors();
				Visitor visitor = new Visitor();
				visitor.setPeople(user);
				visitor.setTime(new Date());
				boolean ishas =false;
				for(int i=0; i<visitors.size();i++){
					if(visitors.get(i).getPeople().getId() == user.getId()){
						Visitor old = visitors.get(i);
						old.setTime(new Date());
						visitors.set(i, old);
						ishas=true;
						break;
					}
				}
				if(ishas != true){
					if(visitors.size()>=10){
						visitors.remove(0);
					}
					visitors.add(visitor);
				}
				classes.setVisitors(visitors);
				classesService.updateEntity(classes);
				Log.init(getClass()).info(user.getName() + "访问班级: " + classes.getName());
				
				//班级人气王
				String hql = "from People p where p.classes.id ='" + classes.getId()+ "' and p.permission<>1 order by p.activity DESC";
				List<People> peoplehot = peopleService.queryForLimit(hql, 0, 5);
				if(peoplehot.isEmpty() || peoplehot.size()==0){
					peoplehot = null;
				}
				getRequest().put("peoplehot", peoplehot);
				
				//班级新人
				hql = "from People p where p.classes.id ='" + classes.getId()+ "' and p.permission<>1 order by p.regTime DESC";
				List<People> peoplenew = peopleService.queryForLimit(hql, 0, 5);
				if(peoplenew.isEmpty() || peoplenew.size()==0){
					peoplenew = null;
				}
				getRequest().put("peoplenew", peoplenew);
				
				//申请加入班级的
				hql = "from People p where p.classes.id ='" + classes.getId()+ "' and p.permission=1 order by p.regTime";
				List<People> applicant = peopleService.getEntity(People.class, hql);
				if(applicant.isEmpty() || applicant.size()==0){
					applicant = null;
				}
				getRequest().put("applicant", applicant);
				
				return super.view();
			}
		}
		return ERROR;
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
	
				classes = classesService.getEntity(Classes.class, id);
				List<Topic> replys = classes.getReplys();
				if (replys == null) {
					replys = new ArrayList<Topic>();
				}
				replys.add(reply);
				classes.setReplys(replys);
				classesService.updateEntity(classes);
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
			classes = classesService.getEntity(Classes.class, id);
			Log.init(getClass()).info("add subreply finish ");
		}

		return "view";
	}

	public String joinAdmin(){
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				classes = classesService.getEntity(Classes.class, id);
				List<People> admins = classes.getAdmins();
				
				boolean ishas =false;         //加入管理员，最多三人
				for(int i=0; i<admins.size();i++){
					if(admins.get(i).getId() == user.getId()){
						ishas=true;
						break;
					}
				}
				if(ishas != true){
					if(admins.size()>=3){
						admins.remove(0);
					}
					admins.add(user);
				}
				classes.setAdmins(admins);
				classesService.updateEntity(classes);
				Log.init(getClass()).info(user.getName() + "成为 " + classes.getName() + " 班级管理员");
				return "view";
			}
		}
		
		return ERROR;
	}
	
	public String notice() {
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
				classes = classesService.getEntity(Classes.class, id);
				classes.setIntro(content + " 由" + user.getName() + " 发布于" + adf.format(new Date()));
				classesService.updateEntity(classes);
				return "view";
			}
		}
		return ERROR;
	}
	
	public String audit(){
		if(audit == 1){
			People user = peopleService.getEntity(People.class, id);
			user.setPermission(2);
			classes = user.getClasses();
			peopleService.updateEntity(user);
		}
		return "view";
	}
	
	public SchoolService<School, Integer> getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService<School, Integer> schoolService) {
		this.schoolService = schoolService;
	}

	public ClassesService<Classes, Integer> getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService<Classes, Integer> classesService) {
		this.classesService = classesService;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public InstituteService<Institute, Integer> getInstituteService() {
		return instituteService;
	}

	public void setInstituteService(InstituteService<Institute, Integer> instituteService) {
		this.instituteService = instituteService;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

}
