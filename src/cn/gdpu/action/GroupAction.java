package cn.gdpu.action;

import java.util.Set;

import cn.gdpu.service.GroupService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class GroupAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private Group group;
	private GroupService<Group, Integer> groupService;
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
		this.pageBean = this.groupService.queryForPage(Group.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		return super.list();
	}

	// 列出我创建的小组
	public String listMyCreate() {
		Log.init(getClass()).info("listMy");
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				this.pageBean = this.groupService.queryForPage("from Group g where g.admin = '" + people.getId() + "'", 10, page);
				if (pageBean.getList().isEmpty())
					pageBean.setList(null);
			}
		}
		return super.list();
	}

	public String join() {
		Log.init(getClass()).info("join");
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				group = groupService.getEntity(Group.class, id);
				Set<People> member = group.getMember();
				member.add(people);
				group.setMember(member);
				groupService.updateEntity(group);
			}
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

}
