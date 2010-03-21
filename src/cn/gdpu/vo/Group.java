package cn.gdpu.vo;

import java.util.List;
import java.util.Set;

public class Group {
	private String name;
	private String intro;
	private String pic;
	private String works;
	private String type;
	private People admin;
	private List<Post> post;
	private Set<People> people;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getWorks() {
		return works;
	}

	public void setWorks(String works) {
		this.works = works;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public People getAdmin() {
		return admin;
	}

	public void setAdmin(People admin) {
		this.admin = admin;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public Set<People> getPeople() {
		return people;
	}

	public void setPeople(Set<People> people) {
		this.people = people;
	}
}
