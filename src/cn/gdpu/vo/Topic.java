package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public class Topic {
	private int id;
	private String title;
	private String content;
	private People author;
	private Date time;
	private boolean istopic;
	private boolean hasreply;
	private List<Topic> reply;

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

	public boolean isHasreply() {
		return hasreply;
	}

	public void setHasreply(boolean hasreply) {
		this.hasreply = hasreply;
	}

	public List<Topic> getReply() {
		return reply;
	}

	public void setReply(List<Topic> reply) {
		this.reply = reply;
	}

}
