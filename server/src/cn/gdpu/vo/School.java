package cn.gdpu.vo;

import java.util.List;
import java.util.Set;

public class School {
	private int id;
	private String name;
	private Image avatar;
	private String content;
	private Province province;
	private Set<Institute> institute;
	private String address;
	private List<People> admin;
	private List<Visitor> visitor;
	
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
	public Image getAvatar() {
		return avatar;
	}
	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Set<Institute> getInstitute() {
		return institute;
	}
	public void setInstitute(Set<Institute> institute) {
		this.institute = institute;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<People> getAdmin() {
		return admin;
	}
	public void setAdmin(List<People> admin) {
		this.admin = admin;
	}
	public List<Visitor> getVisitor() {
		return visitor;
	}
	public void setVisitor(List<Visitor> visitor) {
		this.visitor = visitor;
	}
}
