package cn.gdpu.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrivateUserList {
	private Map<String,PrivateUser> userList = new HashMap<String,PrivateUser>();
	private static PrivateUserList instance = null;
	
	public static synchronized PrivateUserList getInstance() {
		if (instance == null) {
			instance = new PrivateUserList();
		}
		return instance;
	}


	public void addUser(PrivateUser u) {
		this.userList.put(u.getUserid(),u);
	}
	public void removeUser(PrivateUser u) {
		this.userList.remove(u.getUserid());
	}


	public Map<String,PrivateUser> getUserList() {
		return userList;
	}
	
	public List<PrivateUser> getUserNameList(){
		List<PrivateUser> list = new ArrayList<PrivateUser>();
		for (Object o : userList.keySet()) {
			PrivateUser user = userList.get(o);
			list.add(user);
		}
		return list;
	}
}
