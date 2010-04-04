package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public abstract class Topic {
	private int id;
	private String title;
	private String content;
	private People author;
	private Date time;
	private boolean istopic;
	private List<Topic> post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public People getAuthor() {
		return author;
	}

	public void setAuthor(People author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isIstopic() {
		return istopic;
	}

	public void setIstopic(boolean istopic) {
		this.istopic = istopic;
	}

	public List<Topic> getPost() {
		return post;
	}

	public void setPost(List<Topic> post) {
		this.post = post;
	}

}
