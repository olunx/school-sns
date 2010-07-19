package cn.gdpu.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.service.TwitterService;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.MutualFriend;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Twitter;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

	private int id;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private TwitterService<Twitter, Integer> twitterService;
	private Student student;
	private People people;

	public String home() {
		//重新设置session对象
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
		}
		
		//获取问答类型
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

		//获取物品类型
		List<GoodsType> list = goodsTypeService.getAllEntity(GoodsType.class);
		getRequest().put("goodsType", list);

		
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
			hql = "from Twitter t where t.istopic = '1' and t.author.id = '" + user.getId()
					+ "' order by t.time DESC";
			int length = this.twitterService.getEntity(Twitter.class, hql).size();         
			getRequest().put("twittersize", length);
		}
		
		Set<People> maybeMeet = new HashSet<People>();
		people = peopleService.getEntity(People.class, user.getId());
		Set<People> friends = people.getFriends();
		if (people.getSchool() != null) {
			if (people.getClasses() != null) {
				// 推荐班级好友 同一班级的
				hql = "from People p where p.classes.id ='" + people.getClasses().getId() + "' and p.id <> '" + people.getId()
						+ "' order by rand()";
				List<People> cpeos = peopleService.queryForLimit(hql, 0, 5);
				for (People peo : cpeos) {
					if(!friends.contains(peo)){
						maybeMeet.add(peo);
					}
				}
				// 推荐学校好友不同一班级的
				hql = "from People p where p.school.id ='" + people.getSchool().getId() + "' and p.classes.id <>'"
						+ people.getClasses().getId() + "' and p.id <> '" + people.getId() + "' order by rand()";
				List<People> speos = peopleService.queryForLimit(hql, 0, 5);
				System.out.println("speos"+speos+" hql:"+hql);
				for (People peo : speos) {
					if(!friends.contains(peo)){
						maybeMeet.add(peo);
					}
				}
			} else { // 推荐学校好友
				hql = "from People p where p.school.id ='" + people.getSchool().getId() + "' and p.id <> '" + people.getId()
						+ "' order by rand()";
				List<People> speos = peopleService.queryForLimit(hql, 0, 5);
				for (People peo : speos) {
					if(!friends.contains(peo)){
						maybeMeet.add(peo);
					}
				}

			}
			// 推荐好友不同一学校的
			hql = "from People p where p.school.id <>'" + people.getSchool().getId() + "' and p.id <> '" + people.getId()
					+ "' order by rand()";
			List<People> peos = peopleService.queryForLimit(hql, 0, 5);
			for (People peo : peos) {
				if(!friends.contains(peo)){
					maybeMeet.add(peo);
				}
			}
		} else {
			hql = "from People p where p.id <> '" + people.getId() + "' order by rand()"; // 推荐好友
			List<People> peos = peopleService.queryForLimit(hql, 0, 5);
			for (People peo : peos) {
				if(!friends.contains(peo)){
					maybeMeet.add(peo);
				}
			}
		}
		this.getSession().put("maybeMeet", maybeMeet);
		
		List<MutualFriend> mfs = findMutual(people);
		if(mfs.size() == 0)
			mfs=null;
		this.getSession().put("mutualfriend", mfs);
		
		return "home";
	}
	
	public String center() {
		home();
		return "center";
	}
	
	//检查用户与其好友的共同好友
	public List<MutualFriend> findMutual(People me){
		List<MutualFriend> mfs = new ArrayList<MutualFriend>();
		Set<People> myfriends = me.getFriends();
		for(Iterator<People> mit = myfriends.iterator(); mit.hasNext();){
			People other = mit.next();
			if(other.getFriends() != null && other.getFriends().size() != 0){
				for(Iterator<People> oit = other.getFriends().iterator(); oit.hasNext();){
					People of = oit.next();
					if(!myfriends.contains(of) && of.getId() != me.getId()){
						if(mfs.size() == 0){
							MutualFriend mf = new MutualFriend();
							mf.setPeople(of);
							Set<People> mu = new HashSet<People>();
							mu.add(other);
							mf.setMutual(mu);
							mfs.add(mf);
						}else{
							int index = -1;
							for(int i =0; i<mfs.size(); i++){
								MutualFriend a = mfs.get(i);
								if(of.getId() == a.getPeople().getId()){
									index = i;
									break;
								}
							}
							if(index == -1){
								MutualFriend mf = new MutualFriend();
								mf.setPeople(of);
								Set<People> mu = new HashSet<People>();
								mu.add(other);
								mf.setMutual(mu);
								mfs.add(mf);
								
							}else{
								MutualFriend mf = mfs.get(index);
								Set<People> mu = mf.getMutual();
								if(!mu.contains(other))
									mu.add(other);
								mf.setMutual(mu);
								mfs.set(index, mf);
							}
						}
					}
				}
			}
		}
		return mfs;
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public TwitterService<Twitter, Integer> getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterService<Twitter, Integer> twitterService) {
		this.twitterService = twitterService;
	}

	public IssueTypeService<IssueType, Integer> getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService<IssueType, Integer> issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public GoodsTypeService<GoodsType, Integer> getGoodsTypeService() {
		return goodsTypeService;
	}

	public void setGoodsTypeService(GoodsTypeService<GoodsType, Integer> goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

}
