package cn.gdpu.vo;

import java.util.Set;

public class MutualFriend {
	private int id;
	private People people;
	private Set<People> mutual;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	public Set<People> getMutual() {
		return mutual;
	}
	public void setMutual(Set<People> mutual) {
		this.mutual = mutual;
	}
	
}
