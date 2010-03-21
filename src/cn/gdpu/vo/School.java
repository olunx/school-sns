package cn.gdpu.vo;

import java.util.Set;

public class School {
	private String name;
	private Set<Institute> institute;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
