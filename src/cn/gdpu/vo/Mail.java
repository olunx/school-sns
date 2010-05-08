package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public class Mail {
	private int id;
	private String title;
	private String content;
	private People sender;
	private People receiver;
	private Date time;
	private boolean isreaded;
	private boolean istopic;
	private boolean hasreply;
	private List<Mail> reply;

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

	public People getSender() {
		return sender;
	}

	public void setSender(People sender) {
		this.sender = sender;
	}

	public People getReceiver() {
		return receiver;
	}

	public void setReceiver(People receiver) {
		this.receiver = receiver;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isIsreaded() {
		return isreaded;
	}

	public void setIsreaded(boolean isreaded) {
		this.isreaded = isreaded;
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

	public List<Mail> getReply() {
		return reply;
	}

	public void setReply(List<Mail> reply) {
		this.reply = reply;
	}

}
