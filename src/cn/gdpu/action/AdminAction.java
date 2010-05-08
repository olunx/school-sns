package cn.gdpu.action;

import java.util.List;

import cn.gdpu.service.AdminService;
import cn.gdpu.vo.Admin;

@SuppressWarnings("serial")
public class AdminAction extends BaseAction {
	
	private AdminService<Admin, Integer> adminService;
	private Admin admin;
	private int id;

	@Override
	public String add() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
				Admin admin = (Admin) people;
			}
		}
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
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
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
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof Admin) {
				List<Admin> admins = adminService.getAllEntity(Admin.class);
				getRequest().put("admins", admins);
				return super.view();
			}
		}
		return ERROR;
	}
	
	//setter and getter
	
	public AdminService<Admin, Integer> getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService<Admin, Integer> adminService) {
		this.adminService = adminService;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
