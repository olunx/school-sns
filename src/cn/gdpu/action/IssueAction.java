package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONObject;

import cn.gdpu.service.IssueService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.TopicService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Issue;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Topic;

@SuppressWarnings("serial")
public class IssueAction extends BaseAction implements Preparable {

	private IssueService<Issue, Integer> issueService;
	private TopicService<Topic, Integer> topicService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private Issue issue;
	private IssueType issueType;
	private Topic reply;
	private PageBean pageBean;
	private int page;
	private int id;
	private int rid;
	private int itid;
	private int itcid;
	private String isType[];
	private String search;

	@Override
	public void prepare() throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) ServletActionContext.getRequest();
		String action=  httpRequest.getServletPath().split("/")[2];
		String[] uri=action.split("\\.");
		if(uri[0].equals("addIssue")){
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
		}
	}
	
	@Override
	public String add() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				issue.setOwner(user);
			}
			issueType = issueTypeService.getEntity(IssueType.class, itid);
			issue.setIssueType(issueType);
			issue.setAirTime(new Date());
			issueService.addEntity(issue);
			FeedAction.init().add(issue, FeedAction.ADD_ISSUE);
		}
		return super.add();
	}

	@Override
	public String delete() {
		issueService.deleteEntity(Issue.class, id);
		return super.delete();
	}

	@Override
	public String goAdd() {
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
		return super.goAdd();
	}

	@Override
	public String list() {
		this.pageBean = this.issueService.queryForPage(Issue.class, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return "listall";
	}

	public String listme() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				//我的提问
				String hql = "from Issue i where i.owner.name ='" + user.getName() + "'";
				this.pageBean = issueService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
				//最热门的非已提问
				hql = "from Issue i where i.owner.name <>'" + user.getName() + "' order by i.hot DESC limit 5";
				List<Issue> issuehot = issueService.getEntity(Issue.class, hql);
				if(issuehot.isEmpty() || issuehot.size()==0){
					issuehot = null;
				}
				getRequest().put("issuehot", issuehot);
				//最新的非已提问
				hql = "from Issue i where i.owner.name <>'" + user.getName() + "' order by i.airTime DESC limit 5";
				List<Issue> issuenew = issueService.getEntity(Issue.class, hql);
				if(issuenew.isEmpty() || issuenew.size()==0){
					issuenew = null;
				}
				getRequest().put("issuenew", issuenew);
				
				//搜索的二级联动-增加了全部选项
				hql = "from IssueType it where it.isleaf = '0'";
				List<IssueType> its = issueTypeService.getEntity(IssueType.class, hql);
				Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
				Map<String, Object> itallmap = new LinkedHashMap<String, Object>();
				Map<String, Integer> itcallmap = new LinkedHashMap<String, Integer>();
				for(IssueType it : its){
					itallmap.put("key", -1);
					itallmap.put("defaultvalue", -1);
					itcallmap.put("全部", -1);
					for(IssueType itc : it.getChildType()){
						itcallmap.put(itc.getName(), itc.getId());
					}
					itallmap.put("values", itcallmap);
					
				}
				map.put("全部", itallmap);
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
		        System.out.println(jo);
				getRequest().put("jsonmap", jo);
			}
		}
		return INDEX;
	}

	@Override
	public String view() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				issue = issueService.getEntity(Issue.class, id);
				issue.setHot(issue.getHot() + 1);
				issueService.updateEntity(issue);
				
				//相关已解决问题
				String hql = "from Issue i where i.owner.name <>'" + user.getName() + "' and i.answer is not null and i.issueType.name ='" + issue.getIssueType().getName() + "' order by i.airTime DESC limit 5";
				System.out.println("hql1 ====" + hql);
				List<Issue> issuedown = issueService.getEntity(Issue.class, hql);
				System.out.println("list1.size = " + issuedown.size());
				if(issuedown.isEmpty() || issuedown.size()==0){
					issuedown = null;
				}
				getRequest().put("issuedown", issuedown);
				//相关未解决问题
				hql = "from Issue i where i.owner.name <>'" + user.getName() + "' and i.answer is null and i.issueType.name ='" + issue.getIssueType().getName() + "' order by i.airTime DESC limit 5";
				System.out.println("hql2 ====" + hql);
				List<Issue> issueup = issueService.getEntity(Issue.class, hql);
				System.out.println("list2.size = " + issueup.size());
				if(issueup.isEmpty() || issueup.size()==0){
					issueup = null;
				}
				getRequest().put("issueup", issueup);
			}
		}
		return super.view();
	}

	public String goReply() {
		return "replyPage";
	}

	public String reply() {
		if(rid == -1){
			Object author = this.getSession().get("user");
			if (author != null) {
				if (author instanceof People) {
					People people = (People) author;
					Log.init(getClass()).info("people name " + people.getName());
					reply.setAuthor(people);
				}
				reply.setTime(new Date());
				reply.setIstopic(true);
	
				issue = issueService.getEntity(Issue.class, id);
				List<Topic> replys = issue.getReply();
				if (replys == null) {
					replys = new ArrayList<Topic>();
				}
				replys.add(reply);
				issue.setReply(replys);
				issueService.updateEntity(issue);
			}
	
			Log.init(getClass()).info("add reply finish ");
		}
		else{
			Object author = this.getSession().get("user");
			if (author != null) {
				if (author instanceof People) {
					People people = (People) author;
					Log.init(getClass()).info("people name " + people.getName());
					reply.setAuthor(people);
				}
				reply.setTime(new Date());
				reply.setIstopic(false);
				topicService.addEntity(reply);

				Topic parent = topicService.getEntity(Topic.class, rid);
				List<Topic> list = parent.getReply();
				if (list == null) {
					list = new ArrayList<Topic>();
				}
				list.add(reply);
				parent.setReply(list);
				parent.setHasreply(true);
				topicService.updateEntity(parent);
			}
			issue = issueService.getEntity(Issue.class, id);
			Log.init(getClass()).info("add subreply finish ");
		}

		return VIEW_PAGE;
	}

	public String answer() {
		Topic answer = topicService.getEntity(Topic.class, rid);
		issue = issueService.getEntity(Issue.class, id);
		issue.setAnswer(answer);
		issue.setDeadline(new Date());
		issueService.updateEntity(issue);
		return VIEW_PAGE;
	}
	
	public String search() {
		Object author = this.getSession().get("user");
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				String hql = "";
				if(itid == -1){
					hql = "from Issue g where g.owner.name <>'" + people.getName() + "' and g.name like '%" + search + "%' order by g.hot DESC";
				}
				else{
					hql = "from Issue g where g.owner.name <>'" + people.getName() + "' and g.name like '%" + search + "%' and g.issueType.id ='" + itid + "' order by g.hot DESC";
				}
				this.pageBean = this.issueService.queryForPage(hql, 30, page);
				if(pageBean.getList().isEmpty())
		    		pageBean.setList(null);
			}
		}
		return "listall";
	}
	
	//setter and getter
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public TopicService<Topic, Integer> getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService<Topic, Integer> topicService) {
		this.topicService = topicService;
	}

	public Topic getReply() {
		return reply;
	}

	public void setReply(Topic reply) {
		this.reply = reply;
	}


	public IssueService<Issue, Integer> getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService<Issue, Integer> issueService) {
		this.issueService = issueService;
	}

	public IssueTypeService<IssueType, Integer> getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService<IssueType, Integer> issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public int getItid() {
		return itid;
	}

	public void setItid(int itid) {
		this.itid = itid;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public String[] getIsType() {
		return isType;
	}

	public void setIsType(String[] isType) {
		this.isType = isType;
	}
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getItcid() {
		return itcid;
	}

	public void setItcid(int itcid) {
		this.itcid = itcid;
	}
}
