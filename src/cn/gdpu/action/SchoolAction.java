package cn.gdpu.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.gdpu.service.InstituteService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.ProvinceService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Admin;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Province;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Visitor;

@SuppressWarnings("serial")
public class SchoolAction extends BaseAction {
	private ProvinceService<Province, Integer> provinceService;
	private SchoolService<School, Integer> schoolService;
	private InstituteService<Institute, Integer> instituteService;
	private PeopleService<People, Integer> peopleService;
	private School school;
	private Province province;
	private Image image;
	private String content;
	private String address;
	private int id;
	private String[] names;
	private PageBean pageBean;
	private int page;
	
	@Override
	public String add() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
				school.setAvatar(image);
				province = provinceService.getEntity(Province.class, id);
				school.setProvince(province);
				schoolService.addEntity(school);
				
				for(int i=0; i<names.length; i++){
					Institute institute = new Institute();
					System.out.println("institute.name = " + names[i]);
					institute.setName(names[i]);
					institute.setSchool(school);
					instituteService.addEntity(institute);
					Log.init(getClass()).info(school.getName() + "学校创建 " + institute.getName() + "成功");
				}
				Log.init(getClass()).info(school.getName() + "学校创建成功");
				return "admin";
			}
		}
		return ERROR;
	}

	@Override
	public String delete() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
				schoolService.deleteEntity(School.class, id);
				return super.delete();
			}
		}
		return ERROR;
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
				List<Province> provinces = provinceService.getAllEntity(Province.class);
				getRequest().put("provinces", provinces);
				return super.goAdd();
			}
		}
		return ERROR;
	}

	@Override
	public String goModify() {
		return super.goModify();
	}

	@Override
	public String list() {
		pageBean = schoolService.queryForPage(School.class, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return INDEX;
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
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
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
				String hql = "from People p where p.school.id ='" + school.getId()+ "' order by p.activity DESC";
				List<People> peoplehot = peopleService.queryForLimit(hql, 0, 5);
				if(peoplehot.isEmpty() || peoplehot.size()==0){
					peoplehot = null;
				}
				getRequest().put("peoplehot", peoplehot);
				
				//学校新人
				hql = "from People p where p.school.id ='" + school.getId()+ "' order by p.regTime DESC";
				List<People> peoplenew = peopleService.queryForLimit(hql, 0, 5);
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
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (Student) people;
				school = schoolService.getEntity(School.class, id);
				
				List<People> admins = school.getAdmin();
				
				boolean ishas =false;         //加入管理员，最多三人
				for(int i=0; i<admins.size();i++){
					if(admins.get(i).getId() == user.getId()){
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
				System.out.println("---------------------------");
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

	public ProvinceService<Province, Integer> getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService<Province, Integer> provinceService) {
		this.provinceService = provinceService;
	}

	public InstituteService<Institute, Integer> getInstituteService() {
		return instituteService;
	}

	public void setInstituteService(InstituteService<Institute, Integer> instituteService) {
		this.instituteService = instituteService;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
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
}
