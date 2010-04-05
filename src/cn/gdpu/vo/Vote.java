package cn.gdpu.vo;

import java.util.Date;
import java.util.Set;

public class Vote {
	private int id;			  	 	//投票ID
	private Student author;      	    //投票发起人
	private String title;		   		//投票标题
	private String summary;		   		 //投票描述
	private Set<VoteItem> items;	    //投票选项           一个投票有多个选项(one-to-many)
	private int type;				//投票类型，多选或许单选
	private int scope;					//可见范围
	private Date airTime;			    //投票发起时间
	private Date deadline;		  	    //投票结束时间
	private Set<Student> voters;		//投票人               多个投票可以由多个学生投票(many-to-many)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getAuthor() {
		return author;
	}
	public void setAuthor(Student author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Set<VoteItem> getItems() {
		return items;
	}
	public void setItems(Set<VoteItem> items) {
		this.items = items;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	public Date getAirTime() {
		return airTime;
	}
	public void setAirTime(Date airTime) {
		this.airTime = airTime;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Set<Student> getVoters() {
		return voters;
	}
	public void setVoters(Set<Student> voters) {
		this.voters = voters;
	}
	
	
}
