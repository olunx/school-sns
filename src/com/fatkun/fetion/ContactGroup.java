package com.fatkun.fetion;

import java.util.ArrayList;
import java.util.List;

public class ContactGroup {
	public List<Contact> Contacts; 
	public String name;
	public int id;
	
	public ContactGroup(String name, int id) {
		Contacts = new ArrayList<Contact>();
		this.name = name;
		this.id = id;
	}

}
