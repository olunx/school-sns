package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.gdpu.service.ClassFeeService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.ClassFee;

@SuppressWarnings("serial")
public class ClassFeeAction extends BaseAction {

	private int id;
	private String ids;
	
	private ClassFee classfee;
	
	private ClassFeeService<ClassFee, Integer> classfeeService;
	
	private int page;
	private PageBean pageBean;

	@Override
	public String add() {
		classfee.setTime(new Date());
		String remarks = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "：" + "，创建班费记录；";
		classfee.setRemarks(remarks);
		classfeeService.addEntity(classfee);
		return super.add();
	}

	@Override
	public String delete() {
		classfeeService.deleteEntity(ClassFee.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy "  + ids);
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		return super.goAdd();
	}

	@Override
	public String goModify() {
		classfee = (ClassFee) classfeeService.getEntity(ClassFee.class, id);
		return super.goModify();
	}

	@Override
	public String list() {
		this.pageBean = classfeeService.queryForPage(ClassFee.class, 5, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		this.getRequest().put("totalMoney", classfeeService.getTotalMoney());
		return super.list();
	}

	@Override
	public String modify() {
		classfee = (ClassFee) classfeeService.getEntity(ClassFee.class, id);
		String remarks = classfee.getRemarks() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + "，fuchal修改了班费记录；";
		classfee.setRemarks(remarks);
		classfeeService.updateEntity(classfee);

		return super.modify();
	}

	@Override
	public String view() {
		this.setClassfee(classfeeService.getEntity(ClassFee.class, id));
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

}
