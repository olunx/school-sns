package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public abstract class Topic<T> {
	private int id;
	private String title;
	private String content;
	private People author;
	private List<T> post;
	private Date time;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<T> getPost() {
		return post;
	}

	public void setPost(List<T> post) {
		this.post = post;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
