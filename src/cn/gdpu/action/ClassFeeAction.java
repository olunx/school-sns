package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.gdpu.service.ClassFeeService;
import cn.gdpu.service.ClassesService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.ClassFee;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class ClassFeeAction extends BaseAction {

	private int id;
	private String ids;
	private Classes classes;
	private ClassFee classfee;
	
	private ClassFeeService<ClassFee, Integer> classfeeService;
	private ClassesService<Classes, Integer> classesService;
	private int page;
	private PageBean pageBean;

	@Override
	public String add() {
		Object people = this.getSession().get("student");
		if (people != null) {
			if (people instanceof Student) {
				Student student = (Student) people;
				
				classes = student.getClasses();
				boolean isAdmin = false;
				for(People peo : classes.getAdmins()){
					if(peo.getUsername().trim().equals(student.getUsername().trim()))
						isAdmin = true;
				}
				if(isAdmin){
					classfee.setTime(new Date());
					String remarks = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "：" +student.getName() + "，创建班费记录；";
					classfee.setRemarks(remarks);
					classfee.setCmaker(student);
					classfee.setClasses(student.getClasses());
					classfeeService.addEntity(classfee);
					Log.init(getClass()).info(student.getName() + " 成功添加班费：" + classfee.getFee());
					return super.add();
				}
				getRequest().put("isAdmin", isAdmin);
			}
		}
		return ERROR;
	}

	@Override
	public String delete() {
		Object people = this.getSession().get("student");
		if (people != null) {
			if (people instanceof Student) {
				Student student = (Student) people;
				
				classes = student.getClasses();
				boolean isAdmin = false;
				for(People peo : classes.getAdmins()){
					if(peo.getUsername().trim().equals(student.getUsername().trim()))
						isAdmin = true;
				}
				if(isAdmin){
					classfeeService.deleteEntity(ClassFee.class, id);
					return super.delete();
				}
				getRequest().put("isAdmin", isAdmin);
			}
		}
		return ERROR;
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy "  + ids);
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		Object people = this.getSession().get("student");
		if (people != null) {
			if (people instanceof Student) {
				Student student = (Student) people;
				
				classes = student.getClasses();
				boolean isAdmin = false;
				for(People peo : classes.getAdmins()){
					if(peo.getUsername().trim().equals(student.getUsername().trim()))
						isAdmin = true;
				}
				if(isAdmin){
					return super.goAdd();
				}
				getRequest().put("isAdmin", isAdmin);
			}
		}
		return ERROR;
	}

	@Override
	public String goModify() {
		Object people = this.getSession().get("student");
		if (people != null) {
			if (people instanceof Student) {
				Student student = (Student) people;
				
				classes = student.getClasses();
				boolean isAdmin = false;
				for(People peo : classes.getAdmins()){
					if(peo.getUsername().trim().equals(student.getUsername().trim()))
						isAdmin = true;
				}
				if(isAdmin){
					classfee = (ClassFee) classfeeService.getEntity(ClassFee.class, id);
					return super.goModify();
				}
				getRequest().put("isAdmin", isAdmin);
			}
		}
		return ERROR;
	}

	@Override
	public String list() {
		Object people = this.getSession().get("student");
			if (people != null) {
				if (people instanceof Student) {
					Student student = (Student) people;
					classes = student.getClasses();
					boolean isAdmin = false;
					for(People peo : classes.getAdmins()){
						if(peo.getUsername().trim().equals(student.getUsername().trim()))
							isAdmin = true;
					}
					getRequest().put("isAdmin", isAdmin);
					
					this.pageBean = classfeeService.queryForPage(ClassFee.class, 15, page);
					if (pageBean.getList().isEmpty())
						pageBean.setList(null);
					
					String data = "";
					String data1 = "";
					String data2 = "";
					String data3 = "";
					double feecount = 0;
					List<ClassFee> classfees = classfeeService.getAllEntity(ClassFee.class);
					for(Iterator<ClassFee> iter= classfees.iterator(); iter.hasNext();){
						ClassFee cf = iter.next();
						data1 += new SimpleDateFormat("MM/dd").format(cf.getTime());
						feecount += cf.getFee();
						data2 += feecount;
						data3 += cf.getFee();
						if (iter.hasNext()) {
							data1 += ",";
							data2 += ",";
							data3 += ",";
						}
					}
					data = data1 + "/n" + data2 + "/n" + data3;
					
					getRequest().put("data", data);
 					this.getRequest().put("totalMoney", classfeeService.getTotalMoney());
					return super.list();
				}
			}
			return ERROR;
	}

	@Override
	public String modify() {
		Object people = this.getSession().get("student");
		if (people != null) {
			if (people instanceof Student) {
				Student student = (Student) people;
				classes = student.getClasses();
				boolean isAdmin = false;
				for(People peo : classes.getAdmins()){
					if(peo.getUsername().trim().equals(student.getUsername().trim()))
						isAdmin = true;
				}
				if(isAdmin){
					ClassFee classfee1 = classfeeService.getEntity(ClassFee.class, id);
					String remarks = classfee.getRemarks() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + "，" + student.getName() +" 修改了班费记录；";
					classfee1.setRemarks(remarks);
					classfee1.setFee(classfee.getFee());
					classfee1.setEvent(classfee.getEvent());
					classfeeService.updateEntity(classfee1);
					Log.init(getClass()).info(student.getName() + " 成功修改班费：" + classfee.getFee());
					return super.modify();
				}
				getRequest().put("isAdmin", isAdmin);
			}
		}
		return ERROR;
	}

	@Override
	public String view() {
		classfee = classfeeService.getEntity(ClassFee.class, id);
		return super.view();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ClassFee getClassfee() {
		return classfee;
	}

	public void setClassfee(ClassFee classfee) {
		this.classfee = classfee;
	}

	public ClassFeeService<ClassFee, Integer> getClassfeeService() {
		return classfeeService;
	}

	public void setClassfeeService(ClassFeeService<ClassFee, Integer> classfeeService) {
		this.classfeeService = classfeeService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public ClassesService<Classes, Integer> getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService<Classes, Integer> classesService) {
		this.classesService = classesService;
	}
}
