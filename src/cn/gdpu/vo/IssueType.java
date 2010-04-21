package cn.gdpu.vo;

import java.util.HashSet;
import java.util.Set;

public class IssueType {
	private int id;
	private String name;
	private boolean isleaf;
	private Set<Issue> issue = new HashSet<Issue>();
	private IssueType parent;
	private Set<IssueType> childType = new HashSet<IssueType>();
	
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
	public Set<Issue> getIssue() {
		return issue;
	}
	public void setIssue(Set<Issue> issue) {
		this.issue = issue;
	}
	public boolean isIsleaf() {
		return isleaf;
	}
	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}
	public boolean hasChild() {
		return !isleaf;
	}
	public IssueType getParent() {
		return parent;
	}
	public void setParent(IssueType parent) {
		this.parent = parent;
	}
	public Set<IssueType> getChildType() {
		return childType;
	}
	public void setChildType(Set<IssueType> childType) {
		this.childType = childType;
	}
}
