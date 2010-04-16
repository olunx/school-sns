package cn.gdpu.vo;

import java.util.Set;

public class School {
	private int id;
	private String name;
	private Province province;
	private Set<Institute> institute;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
