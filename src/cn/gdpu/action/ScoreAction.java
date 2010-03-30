package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.FetionService;
import cn.gdpu.service.ScoreService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Score;
import cn.gdpu.vo.Student;

public class ScoreAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String phone;
	private String pwd;
	private Map<String, Object> request;
	private FetionService fetionService;
	private ScoreService scoreService;
	private StudentService studentService;
	

	private Log logger = LogFactory.getLog(this.getClass());
	
	
	/**
	 * 列出所有记录
	 * 
	 * @return
	 */
	public String list() {
		return INDEX;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goAdd() {
		return ADD_PAGE;
	}

	/**
	 * 添加数据，返回首页。
	 * 
	 * @return
	 */
	public String add() {
		logger.info(this.getRequest().get("targetsFilePath"));
		List<String> filelist = (List<String>) this.getRequest().get("targetsFilePath");
		System.out.println("------------filelist= " + filelist);

		List<Score> scoreList = new ArrayList<Score>();
		if (filelist.size() > 0) {
			scoreList = StudentExcel.getStudentExcel().getScoreData(filelist.get(0));
		}
		for (Score s: scoreList){
			String sno = s.getStudent().getSno();
			System.out.println("s.sno " + s.getStudent().getSno());
			Student stu = studentService.getStudentByNo(sno);
			System.out.println("test        sdfs" +stu.getName());
			s.setStudent(stu);
			s.setClasses(stu.getClasses());
			s.setTime(new Date());
			scoreService.addEntity(s);
			System.out.println("test: "+s.getSubject());
		}
		return super.add();
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String goModify() {
		return MODIFY_PAGE;
	}

	/**
	 * 提交修改，返回首页。
	 * 
	 * @return
	 */
	public String modify() {
		return LIST;
	}

	/**
	 * 删除一条数据，返回首页。
	 * 
	 * @return
	 */
	public String delete() {
		return LIST;
	}

	/**
	 * 删除多条数据，返回首页。
	 * 
	 * @return
	 */
	public String deleteMany() {
		return INDEX;
	}

	//发送处理
	public String send() {
		
		if (fileName == null) {
			return "input";
		}

		String filePath = ServletActionContext.getServletContext().getRealPath("/upload") + "/" + fileName;
//		Map<String, Object> items = StudentExcel.getStudentExcel().getScoreData(filePath);   // getStudentRegExcel().getScoreData(filePath);
		
		System.out.println("发送成绩");
		//发送飞信
		//fetionService.sendScore(phone, pwd, items);
		
		return "input";
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setFetionService(FetionService fetionService) {
		this.fetionService = fetionService;
	}
	
	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

}
