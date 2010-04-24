package cn.gdpu.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.GroupService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

	private int id;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private GroupService<Group, Integer> groupService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private Student student;

	public String home() {
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
		}
		
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
		
		return "home";
	}
	
	public String center() {
		return "center";
	}

	@Override
	public String execute() throws Exception {
		
		String username = ActionContext.getContext().getName();
		
		Log.init(getClass()).info("TwitterAction username: " + username);
		
		People people = peopleService.getPeopleByUsername(username);
		
		this.id = people.getId();
		
		Log.init(getClass()).info("TwitterAction id: " + id);
		
		return super.SUCCESS;
	}
	
	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

	public GroupService<Group, Integer> getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService<Group, Integer> groupService) {
		this.groupService = groupService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
