package cn.gdpu.chat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class UserList {
	private LinkedList<User> userList = new LinkedList<User>();
	private static UserList instance = null;
	
	public static synchronized UserList getInstance() {
		if (instance == null) {
			instance = new UserList();
		}
		return instance;
	}


	public void addUser(User u) {
		this.userList.add(u);
	}
	public void removeUser(User u) {
		this.userList.remove(u);
	}


	public LinkedList<User> getUserList() {
		return userList;
	}
}
