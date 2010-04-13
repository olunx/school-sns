package cn.gdpu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.Student;
import cn.gdpu.vo.Vote;
import cn.gdpu.vo.VoteItem;



public class VoteServiceTest {

	private static VoteService voteService;
	private static VoteItemService voteItemService;
	private static StudentService studentService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			voteService = (VoteService) ctx.getBean("voteService");
			voteItemService = (VoteItemService) ctx.getBean("voteItemService");
			studentService = (StudentService) ctx.getBean("studentService");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	
	//测试新建投票
	@Test
	public void insertVote(){
		Student author = studentService.getStudentByNo("0707501145");
		System.out.println("test= " + author.getName());
		Vote vote = new Vote();
		HashSet<VoteItem> items = new HashSet<VoteItem>();
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		vote.setAirTime(date);

		cal.setTime(date);
		cal.set(Calendar.DATE,(cal.get(Calendar.DATE)+1));
		vote.setDeadline(cal.getTime());
		
		vote.setAuthor(author);
		vote.setSummary("你对这个投票系统是否满意？");
		vote.setTitle("测试投票系统");
		vote.setType(1);
		
		
		VoteItem voteItem1 = new VoteItem();
		voteItem1.setNum(0);
		voteItem1.setContent("很满意");
		voteItem1.setVote(vote);
		
		VoteItem voteItem2 = new VoteItem();
		voteItem2.setContent("基本满意");
		voteItem2.setVote(vote);
		
		VoteItem voteItem3 = new VoteItem();
		voteItem3.setContent("不满意");
		voteItem3.setVote(vote);
		
		items.add(voteItem1);
		items.add(voteItem2);
		items.add(voteItem3);
		
		//voteService.addVoteItem(voteItem1);
		//voteService.addVoteItem(voteItem2);
		//voteService.addVoteItem(voteItem3);
		System.out.println("testtesttewstsdfdddddddd");
//		voteItemService.addEntity(items);
		vote.setItems(items);		
		
		HashSet<Student> voters = new HashSet<Student>();
//		Student voter = studentService.getStudentByNo("");
		voters.add(author);
		vote.setVoters(voters);
		
		voteService.addEntity(vote);
		System.out.println("-----insertVote()-----");
	}

	//测试查询所有投票
	@Test
	public void queryAllVotes(){
		List<Vote> votes = new ArrayList<Vote>();
		votes = voteService.getAllEntity(Vote.class);
		if(votes != null)
			for(Vote vote : votes){
				System.out.println("voteId:" + vote.getId() + ",votesTitle:" + vote.getTitle());
				for(Iterator iter = vote.getItems().iterator();iter.hasNext(); ){
					VoteItem voteItem = (VoteItem) iter.next();
					System.out.println(voteItem.getContent() + ":得票" +voteItem.getNum());
				}
					
			}
		System.out.println("-----queryAllVotes()-----");
	}
	
	
	
	//测试按ID查询投票
	@Test 
	public void queryVoteByID(){
		int vid = 1;
		Vote vote = (Vote) voteService.getEntity(Vote.class, vid);
		if(vote != null){			
			System.out.println("voteId:" + vote.getId() + ",votesTitle:" + vote.getTitle());
			for(Iterator iter = vote.getItems().iterator();iter.hasNext(); ){
				VoteItem voteItem = (VoteItem) iter.next();
				System.out.println(voteItem.getContent() + ":得票" +voteItem.getNum());
			}
		}
		System.out.println("-----queryClassFeeByID()-----");

	}
	
	//测试按ID删除投票
	@Test
	public void deleteVoteById(){
		int vid = 2;
		try {
			voteService.deleteEntity(Vote.class, vid);
			System.out.println("------成功删除投票-------");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//测试修改投票内容
	@Test
	public void updataVote(){
		Vote vote = new Vote();
		vote.setId(3);
		vote.setSummary("我吹的牛怎么样？");
		vote.setTitle("吹牛");
		
		VoteItem voteItem1 = new VoteItem();
		voteItem1.setNum(0);
		voteItem1.setContent("很好很强大");
		voteItem1.setVote(vote);
		
		VoteItem voteItem2 = new VoteItem();
		voteItem2.setContent("一般");
		voteItem2.setVote(vote);
		
		VoteItem voteItem3 = new VoteItem();
		voteItem3.setContent("垃圾");
		voteItem3.setVote(vote);
		
		HashSet<VoteItem> items = new HashSet<VoteItem>();
		items.add(voteItem1);
		items.add(voteItem2);
		items.add(voteItem3);
		
		vote.setItems(items);
		
		try {
			voteService.updateEntity(vote);
			System.out.println("-------------修改成功----------------");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	//热门投票
	@Test
	public void getHotVotes(){
		List<Vote> votes = new ArrayList<Vote>();
//		votes = voteService.getHotVotes();
		votes = voteService.getAllEntity(Vote.class);
		if(votes != null)
			for(Vote vote : votes){
				System.out.println("voteId:" + vote.getId() + ",votesTitle:" + vote.getTitle());
				for(Iterator iter = vote.getItems().iterator();iter.hasNext(); ){
					VoteItem voteItem = (VoteItem) iter.next();
					System.out.println(voteItem.getContent() + ":得票" +voteItem.getNum());
				}
					
			}
		System.out.println("-----getHotVotes()-----");
	}
}
