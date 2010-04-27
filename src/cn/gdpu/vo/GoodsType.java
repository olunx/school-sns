package cn.gdpu.vo;

import java.util.HashSet;
import java.util.Set;

public class GoodsType {
	private int id;
	private String name;
	private Set<Goods> goods = new HashSet<Goods>();
	
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
	public Set<Goods> getGoods() {
		return goods;
	}
	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
}
