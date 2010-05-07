package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;

import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.FetionService;
import cn.gdpu.service.ScoreService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Score;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class ScoreAction extends BaseAction {

	private String fileName;
	private String phone;
	private String pwd;
	private Map<String, Object> request;
	@SuppressWarnings("unused")
	private FetionService fetionService;
	private ScoreService<Score, Integer> scoreService;
	private StudentService<Student, Integer> studentService;
	private PageBean pageBean;
	private int page;


	/**
	 * 列出当前班级的所有学生的成绩记录
	 * 
	 * @return
	 */
	public String list() {
		this.pageBean = this.scoreService.queryForPage(Score.class, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return "listall";
	}
	
	/**
	 * 列出当前学生的所有记录
	 * 
	 * @return
	 */
	public String query() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof Student) {
			Student student = (Student) author;
			String sno = student.getSno();
			String hql = "from Score s where s.student.sno ='" + sno + "'";
			List<Score> scores = scoreService.getEntity(Score.class, hql);
			String data = "";
			if(scores.size() == 0){
				scores = null;	
			}
			else{
				for(Iterator<Score> iter= scores.iterator(); iter.hasNext();){
					Score s = iter.next();
					data += s.getSubject();
					if (iter.hasNext()) {
						data += ",";
					}
				}
				data += "/n";
				for(Iterator<Score> iter= scores.iterator(); iter.hasNext();){
					Score s = iter.next();
					data += s.getMarks();
					if (iter.hasNext()) {
						data += ",";
					}
				}
				data += "/n";
				for(Iterator<Score> iter= scores.iterator(); iter.hasNext();){
					Score s = iter.next();
					double avg = scoreService.getAvgSubject(s.getSubject());
					data += avg;
					if (iter.hasNext()) {
						data += ",";
					}
				}
				System.out.println(data);
				getRequest().put("data", data);
				getRequest().put("scores", scores);
			}
			return INDEX;
			}
		}
		return ERROR;
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
	@SuppressWarnings("unchecked")
	public String add() {
		List<String> filelist = (List<String>) this.getRequest().get("targetsFilePath");

		List<Score> scoreList = new ArrayList<Score>();
		if (filelist.size() > 0) {
			scoreList = StudentExcel.getStudentExcel().getScoreData(filelist.get(0));
		}
		for (Score s: scoreList){
			String sno = s.getStudent().getSno();
			Student stu = studentService.getStudentByNo(sno);
			s.setStudent(stu);
			s.setClasses(stu.getClasses());
			s.setTime(new Date());
			scoreService.addEntity(s);
		}
		Log.init(getClass()).info("成绩导入成功");
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

		@SuppressWarnings("unused")
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
	
	public ScoreService<Score, Integer> getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService<Score, Integer> scoreService) {
		this.scoreService = scoreService;
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
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
