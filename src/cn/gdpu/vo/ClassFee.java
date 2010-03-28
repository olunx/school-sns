package cn.gdpu.vo;

import java.util.Date;

public class ClassFee {
	private int id;              //费用ID
	private Student cmaker;       //经手人
	private double fee;			  //费用
	private String event;		  //执行事件
	private Date time;			  //事件执行时间
	private String remarks;		  //班费备注
	private Classes classes;	  //所属班级

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public Student getCmaker() {
		return cmaker;
	}

	public void setCmaker(Student cmaker) {
		this.cmaker = cmaker;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date date) {
		this.time = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
