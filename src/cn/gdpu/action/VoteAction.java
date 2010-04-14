package cn.gdpu.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import cn.gdpu.service.VoteItemService;
import cn.gdpu.service.VoteService;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.VoteItemComparator;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Vote;
import cn.gdpu.vo.VoteItem;

@SuppressWarnings("serial")
public class VoteAction extends BaseAction {

	private VoteService<Vote, Integer> voteService;
	private VoteItemService<VoteItem, Integer> voteItemService;
	private Vote vote;
	private VoteItem voteItem;
	private String[] content;	
	private String time;
	private int vid;
	private int[] vids;
	private int[] viid;

	private PageBean pageBean;
	private int page;
	
	
	
	public String add() {
		Object people = this.getSession().get("user");
		if (people != null) {
			if (people instanceof People) {
				People user = (People) people;
				vote.setAuthor(user);
				vote.setAirTime(new Date());
				SimpleDateFormat timesdf =   new SimpleDateFormat("HH:mm:ss");
		        String str = timesdf.format(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse(time + " " + str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				vote.setDeadline(date);
				VoteItemComparator<VoteItem> voteItemComparator = new VoteItemComparator<VoteItem>();
				Set<VoteItem> items = new TreeSet<VoteItem>(voteItemComparator);
				for(int i=0;i<content.length;i++){
					if(content[i] == null || content[i].trim().equals(""))
						continue;
					boolean exit = false;
					for(int j=0 ;j<i ; j++){
						if(content[j].equals(content[i])){
							exit = true;
							break;
						}					
					}
					if(exit)
						continue;
					VoteItem voteItem = new VoteItem();
					voteItem.setContent(content[i]);
					items.add(voteItem);
				}
				if(items.isEmpty()){
					this.addFieldError("content", "投票选项不能全为空");
				}
				if(hasFieldErrors())
					return INPUT;
				vote.setItems(items);
				voteService.addEntity(vote);
			}
		}
		return super.add();
	}

	@Override
	public String delete() {
		voteService.deleteEntity(Vote.class, vid);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		return super.goAdd();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		this.pageBean = this.voteService.queryForPage(Vote.class, 30, page);
		if(pageBean.getList().isEmpty())
    		pageBean.setList(null);
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

	public String goVoting() throws ParseException {
		Vote vote = (Vote) voteService.getEntity(Vote.class, vid);
		getRequest().put("vote", vote);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = sdf.format(new Date());
        Date toDay = sdf.parse(str1);
		if(toDay.after(vote.getDeadline()))     //如果投票过期 ，返回timeout
			return super.view();
		Set<People> voters = vote.getVoters();
		Student voter = (Student) getSession().get("student");
		if(voter == null){
			return super.view();
		}
		for(People stu : voters ){
			if(stu.getId() == voter.getId())					//如果投票人已经投票，返回voterexist
				return super.view();
		}
		return "govoting";
	}

	public String voting() throws Exception {
		vote =	(Vote) voteService.getEntity(Vote.class, vid);
		if(viid == null)										//验证投票先是否为空
			this.addFieldError("viid", "投票选项不能为空");
		if(hasFieldErrors()){
			getRequest().put("vote", vote);
			return "govoting";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = sdf.format(new Date());
        Date toDay = sdf.parse(str1);
		if(toDay.after(vote.getDeadline()))     //如果投票过期 ，返回timeout
			return super.view();
		Set<People> voters = vote.getVoters();
		Student voter = (Student) getSession().get("student");
		if(voter == null)
			return LIST;
		for(People stu : voters ){
			if(stu.getId() == voter.getId())			//如果投票人已经投票，返回voterexist
				return super.view();
		}
		if (!voters.add(voter))
			return super.view();
		vote.setVoters(voters);
		for(int i=0;i<viid.length;i++){
			voteItem = (VoteItem) voteItemService.getEntity(VoteItem.class, viid[i]);
			System.out.println("test = " + voteItem.getContent());
			voteItem.setNum(voteItem.getNum()+1);
		}		
		voteService.updateEntity(vote);
		return super.view();
	}

	@Override
	public String view() {
		String data = "";
		vote = (Vote) voteService.getEntity(Vote.class, vid);
		
		for(Iterator<VoteItem> iter = vote.getItems().iterator();iter.hasNext(); ){
			VoteItem voteItem = (VoteItem) iter.next();
			data += voteItem.getContent();
			if(iter.hasNext()){
				data += ",";
			}
		}
		data +="-";
		for(Iterator<VoteItem> iter = vote.getItems().iterator();iter.hasNext(); ){
			VoteItem voteItem = (VoteItem) iter.next();
			data +=voteItem.getNum();
			if(iter.hasNext()){
				data += ",";
			}
		}
		getRequest().put("data", data);
		return "view";
	}

	
	//setter and getter
	public VoteService<Vote, Integer> getVoteService() {
		return voteService;
	}

	public void setVoteService(VoteService<Vote, Integer> voteService) {
		this.voteService = voteService;
	}

	public VoteItemService<VoteItem, Integer> getVoteItemService() {
		return voteItemService;
	}

	public void setVoteItemService(VoteItemService<VoteItem, Integer> voteItemService) {
		this.voteItemService = voteItemService;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public VoteItem getVoteItem() {
		return voteItem;
	}

	public void setVoteItem(VoteItem voteItem) {
		this.voteItem = voteItem;
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
	
	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int[] getVids() {
		return vids;
	}

	public void setVids(int[] vids) {
		this.vids = vids;
	}

	public int[] getViid() {
		return viid;
	}

	public void setViid(int[] viid) {
		this.viid = viid;
	}

}
