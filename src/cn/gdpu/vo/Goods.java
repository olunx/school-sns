package cn.gdpu.vo;

import java.util.Date;
import java.util.List;

public class Goods {
	private int id;
	private String name;
	private String content;
	private String image;
	private int quantity;
	private Student owner;
	private int state;
	private double value;
	private String record;
	private List<Topic> reply;
	private GoodsType goodsType;
	private String exchange;
	private int hot;
	private Date airTime;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Student getOwner() {
		return owner;
	}
	public void setOwner(Student owner) {
		this.owner = owner;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public List<Topic> getReply() {
		return reply;
	}
	public void setReply(List<Topic> reply) {
		this.reply = reply;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public GoodsType getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public Date getAirTime() {
		return airTime;
	}
	public void setAirTime(Date airTime) {
		this.airTime = airTime;
	}
}
