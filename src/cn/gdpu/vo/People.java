package cn.gdpu.vo;

import java.util.Date;
import java.util.Set;

public class People {
	private int id;
	private School school;
	private Institute institute;
	private Classes classes;
	private int entryYear;

	private String dorm;
	private String username;
	private String password;
	private String name;
	private String sex;
	private int permission;
	private Image avatar;
	private String phoneNo;
	private String email;
	private String qq;
	private Date birthday;

	private String activity;
	private Long point;
	private String nickname;
	private String status;

	private Date lastlogin;
	private Set<Excel> excel;
	private Set<Duty> duty;
	private Set<Vote> votes;
	private Set<Goods> goods;
	private Set<Issue> issue;
	private Set<Hobby> hobby;
	private Set<Group> groups;
	private Set<Achievement> achievement;
	private Set<People> friends;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public int getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Set<Excel> getExcel() {
		return excel;
	}

	public void setExcel(Set<Excel> excel) {
		this.excel = excel;
	}

	public Set<Duty> getDuty() {
		return duty;
	}

	public void setDuty(Set<Duty> duty) {
		this.duty = duty;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}

	public Set<Issue> getIssue() {
		return issue;
	}

	public void setIssue(Set<Issue> issue) {
		this.issue = issue;
	}

	public Set<Hobby> getHobby() {
		return hobby;
	}

	public void setHobby(Set<Hobby> hobby) {
		this.hobby = hobby;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Achievement> getAchievement() {
		return achievement;
	}

	public void setAchievement(Set<Achievement> achievement) {
		this.achievement = achievement;
	}

	public Set<People> getFriends() {
		return friends;
	}

	public void setFriends(Set<People> friends) {
		this.friends = friends;
	}

}
