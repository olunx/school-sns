package cn.gdpu.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.TwitterService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Twitter;
import cn.gdpu.vo.Visitor;

@SuppressWarnings("serial")
public class TAction extends BaseAction {

	private int id;
	private People people;
	private PageBean pageBean;
	private int page;
	private PeopleService<People, Integer> peopleService;
	private TwitterService<Twitter, Integer> twitterService;

	@Override
	public String execute() throws Exception {
		Object peo = this.getSession().get("user");
		if (peo != null) {
			if (peo instanceof People) {
				People user = (People) peo;
		
				String username = ActionContext.getContext().getName();
				
				Log.init(getClass()).info("TwitterAction username: " + username);
				
				people = peopleService.getPeopleByUsername(username);
				
				List<Visitor> visitors = (List<Visitor>) people.getVisitors();
				
				Visitor visitor = new Visitor();
				visitor.setPeople(user);
				visitor.setTime(new Date());
				
				boolean ishas =false;
				for(int i=0; i<visitors.size();i++){
					if(visitors.get(i).getPeople().getId() == user.getId()){
						Visitor old = visitors.get(i);
						old.setTime(new Date());
						visitors.set(i, old);
						ishas=true;
					}
				}
				
				if(ishas != true && visitor.getPeople().getId() != people.getId()){
					if(visitors.size()>=10){
						visitors.remove(0);
					}
					visitors.add(visitor);
				}
				
				people.setVisitors(visitors);
				
				peopleService.updateEntity(people);
				
				Log.init(getClass()).info("TwitterAction id: " + people.getId());
				
				this.pageBean = this.twitterService.queryForPage("from Twitter t where t.istopic = '1' and t.author.id = '" + people.getId()
						+ "' order by t.time DESC", 10, page);
				if (pageBean.getList().isEmpty()) {
					pageBean.setList(null);
				}

				Log.init(getClass()).info("listOther finish");
				
				return super.SUCCESS;
			}
		}
		return ERROR;
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

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public TwitterService<Twitter, Integer> getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterService<Twitter, Integer> twitterService) {
		this.twitterService = twitterService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
