package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public class Issue {
	private int id;
	private String name;
	private String content;
	private People owner;
	private int state;
	private int value;        
	private List<Topic> reply;
	private Topic answer;
	private IssueType issueType;
	private int hot;
	private Date airTime;
	private Date deadline;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public People getOwner() {
		return owner;
	}
	public void setOwner(People owner) {
		this.owner = owner;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<Topic> getReply() {
		return reply;
	}
	public void setReply(List<Topic> reply) {
		this.reply = reply;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public Date getAirTime() {
		return airTime;
	}
	public void setAirTime(Date airTime) {
		this.airTime = airTime;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Topic getAnswer() {
		return answer;
	}
	public void setAnswer(Topic answer) {
		this.answer = answer;
	}
}
