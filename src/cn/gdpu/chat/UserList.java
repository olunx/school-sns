package cn.gdpu.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserList {
	private Map<String,User> userList = new HashMap<String,User>();
	private static UserList instance = null;
	
	public static synchronized UserList getInstance() {
		if (instance == null) {
			instance = new UserList();
		}
		return instance;
	}


	public void addUser(User u) {
		this.userList.put(u.getUserid(),u);
	}
	public void removeUser(User u) {
		this.userList.remove(u.getUserid());
	}


	public Map<String,User> getUserList() {
		return userList;
	}
	
	public List<User> getUserNameList(){
		List<User> list = new ArrayList<User>();
		for (Object o : userList.keySet()) {
			User user = userList.get(o);
			list.add(user);
		}
		return list;
	}
}
