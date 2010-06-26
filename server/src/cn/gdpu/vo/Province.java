package cn.gdpu.vo;

import java.util.Set;

public class Province {
	private int id;
	private String name;
	private Set<School> schools;
	
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
	public Set<School> getSchools() {
		return schools;
	}
	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

}
