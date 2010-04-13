package cn.gdpu.vo;

import java.util.Date;
import java.util.Set;

public class Student extends People {
	private int id;
	private School school;
	private Institute institute;
	private Classes classes;
	private String dorm;
	private int entryYear;
	private String sno;
	private String nickname;
	private String status;
	private String activity;
	private Date lastlogin;
	private Long point;
	private Set<Duty> duty;
	private Set<Hobby> hobby;
	private Set<Group> groups;
	private Set<Achievement> achievement;
	private Set<Student> friends;
	private Set<Score> scores;
	private Set<Vote> votes;
	private Set<Goods> goods;
	

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
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

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public int getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Set<Duty> getDuty() {
		return duty;
	}

	public void setDuty(Set<Duty> duty) {
		this.duty = duty;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Student> getFriends() {
		return friends;
	}

	public void setFriends(Set<Student> friends) {
		this.friends = friends;
	}
	
	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
}
