package cn.gdpu.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.gdpu.service.SchoolService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Visitor;

public class SchoolAction extends BaseAction {
	private SchoolService<School, Integer> schoolService;
	private School school;
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
				school = schoolService.getEntity(School.class, id);
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
				return super.view();
			}
		}
		return ERROR;
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
	
}
