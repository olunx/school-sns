package cn.gdpu.vo;

import java.util.Date;
import java.util.Set;

public class Attendance {
	private int id;
	private Set<Student> students;
	private Student clerk;
	private Date time;
	private String week;
	private String day;
	private Set<Course> course;

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Student getClerk() {
		return clerk;
	}

	public void setClerk(Student clerk) {
		this.clerk = clerk;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Attendance [aid=" + id + ", day=" + day + ", time=" + time + ", week=" + week + "]";
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
