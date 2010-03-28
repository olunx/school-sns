package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import cn.gdpu.service.ClassFeeService;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.ClassFee;

@SuppressWarnings("serial")
public class ClassFeeAction extends BaseAction {
	
	
	private ClassFeeService classfeeService;
	private ClassFee classfee;
	private PageBean pageBean;
	private int page;
	private int id;
	private String fee;
	
	
	@Override
	public String add() {
		classfee.setFee(Double.parseDouble(fee));
		Date date = new Date();
		classfee.setTime(date);
//		String remarks = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + "：" + cmaker.getRealName() + "，创建班费记录；";
		String remarks = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + "：" + "，创建班费记录；";		
		classfee.setRemarks(remarks);
		classfeeService.addEntity(classfee);
		return super.add();
	}
	
	@Override
	@SkipValidation
	public String delete() {
		classfeeService.deleteEntity(ClassFee.class, id);
		return super.delete();
	}
	
	@Override
	@SkipValidation
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}
	
	@Override
	@SkipValidation
	public String goAdd() {
		return super.goAdd();
	}
	
	@Override
	@SkipValidation
	public String goModify() {
		classfee = (ClassFee) classfeeService.getEntity(ClassFee.class, id);
		getSession().put("classfee", classfee);
		return super.goModify();
	}
	
	
	@Override
	@SkipValidation
	public String list() {
		this.pageBean = classfeeService.queryForPage(ClassFee.class, 5, page);
        if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
        getRequest().put("total", classfeeService.getTotalMoney());
		return super.list();
	}
	
	@Override
	public String modify() {
		ClassFee classfee1 = (ClassFee) classfeeService.getEntity(ClassFee.class, id);
		classfee1.setEvent(classfee.getEvent());
		classfee1.setFee(Double.parseDouble(fee));
//		String remarks = classfee1.getRemarks() +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + cmaker.getRealName() + "，修改了班费记录；";
		String remarks = classfee1.getRemarks() +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + "，fuchal修改了班费记录；";
		
		classfee1.setRemarks(remarks);
		classfeeService.updateEntity(classfee1);
		getRequest().put("classfee", classfee1);
		return super.modify();
	}
	
	//setter and getter
	public ClassFeeService getClassfeeService() {
		return classfeeService;
	}
	public void setClassfeeService(ClassFeeService classfeeService) {
		this.classfeeService = classfeeService;
	}
	public ClassFee getClassfee() {
		return classfee;
	}
	public void setClassfee(ClassFee classfee) {
		this.classfee = classfee;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	
}
