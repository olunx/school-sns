package cn.gdpu.vo;

import java.util.Set;

public class Teacher extends People {
	private int id;
	private School school;
	private String division;
	private Duty duty;
	private Set<Excel> excel;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Duty getDuty() {
		return duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Excel> getExcel() {
		return excel;
	}

	public void setExcel(Set<Excel> excel) {
		this.excel = excel;
	}
}
