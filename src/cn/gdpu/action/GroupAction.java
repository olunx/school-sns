package cn.gdpu.action;

import java.util.Set;

import cn.gdpu.service.GroupService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class GroupAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private Group group;
	private GroupService<Group, Integer> groupService;
	private StudentService<Student, Integer> studentService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				group.setAdmin((People) author);
			}
		}
		groupService.addEntity(group);
		return super.add();
	}

	@Override
	public String delete() {
		groupService.deleteEntity(Group.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		groupService.deleteManyEntity(Group.class, ids);
		return super.deleteMany();
	}

	@Override
	public String goModify() {
		group = groupService.getEntity(Group.class, id);
		return super.goModify();
	}

	@Override
	public String list() {
		Student stu = (Student) this.getSession().get("student");
		if (stu != null) {
			stu = studentService.getEntity(Student.class, stu.getId());
			this.getRequest().put("groups", stu.getGroups());
		}
		this.pageBean = this.groupService.queryForPage(Group.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		return super.list();
	}

	// 列出我创建的小组
	public String listMyCreate() {
		Log.init(getClass()).info("listMy");
		Student stu = (Student) this.getSession().get("student");
		if (stu != null) {
			this.pageBean = this.groupService.queryForPage("from Group g where g.admin = '" + stu.getId() + "'", 10, page);
			if (pageBean.getList().isEmpty())
				pageBean.setList(null);
		}
		return super.list();
	}

	public String join() {
		Log.init(getClass()).info("join");

		Student stu = (Student) this.getSession().get("student");
		Log.init(getClass()).info("stu " + stu);
		if (stu != null) {
			group = groupService.getEntity(Group.class, id);
			stu = studentService.getEntity(Student.class, stu.getId());
			Set<People> members = group.getMembers();
			if (members.contains(stu)) {
				members.remove(stu);
			} else {
				members.add(stu);
			}
			group.setMembers(members);
			groupService.updateEntity(group);
		}
		
		Log.init(getClass()).info("join完成，跳转。");
		
		return super.LIST;
	}

	@Override
	public String modify() {
		groupService.updateEntity(group);
		return super.modify();
	}

	@Override
	public String view() {
		group = groupService.getEntity(Group.class, id);
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupService<Group, Integer> getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService<Group, Integer> groupService) {
		this.groupService = groupService;
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

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

}
