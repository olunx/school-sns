package com.fatkun.fetion;

import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.fatkun.fetion.FetionUtil;

/**
 * 
 * @author Fatkun , Time:2009-09-13 Website : http://www.fatkun.com 飞信线程
 * 
 */
public class FetionSocket {
	private String sid;// 飞信号
	private String phone;// 手机号
	private String domain = "fetion.com.cn";
	private String passwd;// 密码
	private String sipc_proxy = "221.130.46.141:8080";// 登陆地址
	public static List<ContactGroup> contactList = new ArrayList<ContactGroup>();// 所有联系人
	// private String ssi_app_sign_in =
	// "http://uid.fetion.com.cn/ssiportal/SSIAppSignIn.aspx";
	// private String ssi_app_sign_out =
	// "http://ssi.fetion.com.cn/ssiportal/SSIAppSignOut.aspx";
	private String FNonce;// 登陆验证需要
	private final String FCnonce = "7036EA07568E7C4D6D49FD76141062FE";

	// 登陆字符串
	private final String loginStr = "<args><device type=\"PC\" version=\"33\" client-version=\"3.3.0370\" /><caps value=\"simple-im;im-session;temp-group;personal-group\" /><events value=\"contact;permission;system-message;personal-group\" /><user-info attributes=\"all\" /><presence><basic value=\"400\" desc=\"\" /></presence></args>";
	// 联系人字符串
	private final String contactsStr = "<args><contacts><buddy-lists /><buddies attributes=\"all\" /><mobile-buddies attributes=\"all\" /><chat-friends /><blacklist /></contacts></args>";
	// 个人信息字符串
	private final String personalInfoStr = "<args><personal version=\"11\" attributes=\"all\" /><services version=\"11\" attributes=\"all\" /><config version=\"109\" attributes=\"all\" /><mobile-device attributes=\"all\" /></args>";
	// 联系人信息字符串
	private String contactInfoStr = "<args><contacts attributes=\"all\" extended-attributes=\"score-level\"><contact uri=\"%s\" /></contacts></args>";
	private Socket socket;
	private DataInputStream fetionDis;
	private DataOutputStream fetionDos;

	public FetionSocket(String phone, String passwd) {
		this.phone = phone;
		this.passwd = passwd;
	}

	public boolean openSocket(String _sipc_proxy) {
		String[] sipc_proxyArr = _sipc_proxy.split(":");// 分解登陆服务器地址
		try {
			socket = new Socket(sipc_proxyArr[0], Integer.parseInt(sipc_proxyArr[1]));// 连接
			fetionDis = new DataInputStream(socket.getInputStream());
			fetionDos = new DataOutputStream(socket.getOutputStream());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 发送
	public boolean socketWrite(String sendStr) throws IOException {
		if (socket == null) {
			openSocket(sipc_proxy);
		}
		if (fetionDos != null) {
			fetionDos.write(sendStr.getBytes("utf-8"));
			fetionDos.flush();
			return true;
		}
		return false;
	}

	// 读取一行
	private String socketReadLine() throws IOException {
		byte[] buf = new byte[1];
		StringBuffer sb = new StringBuffer("");
		while (true) {
			fetionDis.read(buf, 0, 1);
			sb.append(new String(buf));
			if (sb.toString().endsWith("\r\n"))
				break;
		}
		// System.out.print(sb.toString());
		return sb.toString();
	}

	// 读取
	public String socketRead() throws IOException {
		String newLine = "";
		String readStr = "";
		String recStr = "";
		int msgLen = 0;

		String cmdLine = socketReadLine();
		readStr = cmdLine;
		if (cmdLine.length() <= 0)
			return "";
		do {
			newLine = socketReadLine();
			readStr += newLine;
			// System.out.println(newLine);
			if (newLine.length() > 0) {
				if (newLine.charAt(0) == 'W') {
					FNonce = FetionUtil.getInstance().centerStr(newLine, "nonce=\"(.*?)\"").get(0);
				} else if (newLine.charAt(0) == 'L') {
					msgLen = Integer.parseInt(FetionUtil.getInstance().centerStr(newLine, "L: (\\d+)").get(0));
				}
			} else {
				break;
			}
		} while (!readStr.contains("\r\n\r\n"));
		// 如果需要接收字节流(有L参数)
		if (msgLen > 0) {
			byte[] buf = new byte[msgLen];
			int size = 0;
			int totalsize = 0;
			// System.out.print("buf.length" + buf.length + "------");
			while (totalsize < msgLen) {
				size = fetionDis.read(buf, size, msgLen - totalsize);
				totalsize += size;
				// System.out.print(size + " ");
			}
			recStr = new String(buf);
			readStr += recStr;
		}
		 System.out.println("[REC]<<<<=====================================");
		 System.out.println(readStr);
		 System.out.println("==============================================");

		return readStr;
	}

	public String build_reponse_A() {
		String response = FetionUtil.getInstance().getResponseStr(sid, passwd, domain, FNonce, FCnonce);
		return "Digest response=\"" + response + "\",cnonce=\"" + FCnonce + "\"";
	}

	// 初始化(得到服务器登陆IP"sipc_proxy"以及飞信号"sid")
	public void init() throws IOException {
		// sid = "";
		ArrayList<String> loginInfo;
		//loginInfo = new FetionInit().InitAction(phone, passwd);
		sid = new FetionInit().getSid(phone, passwd);
		//sipc_proxy = loginInfo.get(0);
		// ssi_app_sign_in = loginInfo.get(1);
		// ssi_app_sign_out = loginInfo.get(2);

		openSocket(sipc_proxy);
	}

	// 登陆飞信
	public boolean login() throws IOException {
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("R", new String[] { "F: " + sid, "I: 1" }, loginStr));
		socketRead();
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("R", new String[] { "F: " + sid, "A: " + build_reponse_A(), "I: 1" }, loginStr));
		return FetionUtil.getInstance().getSIPPResponse(socketRead());
	}

	// 退出飞信
	public void logout() throws IOException {
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("R", new String[] { "F: " + sid, "X: 0", "I: 1" }, ""));
		socketRead();
	}

	// 得到联系人
	public List<ContactGroup> getContacts() throws IOException {
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("S", new String[] { "F: " + sid, "N: GetContactList", "I: 3" }, contactsStr));
		if (FetionUtil.getInstance().getSIPPResponse(socketRead()))
			return contactList;
		else
			return null;
	}

	// 得到自己的详细信息
	public void getPersonalInfo() throws IOException {
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("S", new String[] { "F: " + sid, "N: GetPersonalInfo", "I: 1" }, personalInfoStr));
		socketRead();
	}

	// 得到联系人详细信息
	public void getContactsInfo(String userSid) throws IOException {
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("S", new String[] { "F: " + sid, "N: GetContactsInfo", "I: 1" }, String.format(contactInfoStr, userSid)));
		socketRead();
		socketRead();
	}

	// 发送消息
	public void sendMsg(String msg, String to) throws IOException {
		if (to.length() == 11)
			to = "tel:" + to;
		else
			to = "sip:" + to + "@fetion.com.cn;p=1234";
		socketWrite(FetionUtil.getInstance().buildSIPPRequest("M", new String[] { "F: " + sid, "T: " + to, "N: SendSMS", "I: 1" }, msg));
		socketRead();
	}

	public void socketClose() throws IOException {
		fetionDis.close();
		fetionDos.close();
		socket.close();
	}

}
