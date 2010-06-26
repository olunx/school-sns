package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public class Twitter {
	private int id;
	private String content;
	private People author;
	private Date time;
	private Image image;
	private String type;
	private String link;
	private boolean istopic;
	private boolean hasreply;
	private List<Twitter> reply;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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

	public List<Twitter> getReply() {
		return reply;
	}

	public void setReply(List<Twitter> reply) {
		this.reply = reply;
	}

}
