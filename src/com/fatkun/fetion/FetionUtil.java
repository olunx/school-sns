package com.fatkun.fetion;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class FetionUtil {
	private final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	private static FetionUtil instance;

	public static FetionUtil getInstance() {
		return instance == null ? new FetionUtil() : instance;
	}

	/**
	 *  正则得到匹配的内容
	 * @param sourceStr
	 * @param patternStr
	 * @return
	 */
	public ArrayList<String> centerStr(String sourceStr, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(sourceStr);
		ArrayList<String> list = new ArrayList<String>();
		while (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				list.add(matcher.group(i));
			}
		}
		return list;
	}

	// 计算response
	public String getResponseStr(String _sid, String _passwd, String _domain, String _nonce, String _cnonce) {
		String md5Str = ":" + _nonce + ":" + _cnonce;
		String md5Str2 = _sid + ":" + _domain + ":" + _passwd;
		String H1 = computeH1(md5Str2, md5Str);
		md5Str = "REGISTER:" + _sid;
		String H2 = MD5Encode(md5Str);
		md5Str = H1 + ":" + _nonce + ":" + H2;
		return MD5Encode(md5Str);
	}

	public String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuilder()).append(hexDigits[d1]).append(hexDigits[d2]).toString();
	}

	public String MD5Encode(String origin) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(origin.getBytes("utf-8")));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public String computeH1(String s1, String s2) {
		String res = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte key[] = md.digest(s1.getBytes("utf-8"));
			byte ss[] = s2.getBytes("utf-8");
			byte t[] = new byte[key.length + ss.length];
			for (int i = 0; i < key.length; i++)
				t[i] = key[i];

			for (int i = 0; i < ss.length; i++)
				t[key.length + i] = ss[i];

			res = byteArrayToHexString(md.digest(t));
		} catch (Exception e) {
		}
		return res;
	}

	/**
	 * 从xml得到所有联系人
	 * @param xmlStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ContactGroup> getContactsList(String xmlStr) {
		List<ContactGroup> groups = new ArrayList<ContactGroup>();
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			Iterator iter = root.element("contacts").elementIterator();
			groups.add(new ContactGroup("未分组", 0));
			while (iter.hasNext()) {
				Element elem = (Element) iter.next();
				if (elem.getName().equals("buddy-lists")) {// 联系人组
					List list = elem.elements("buddy-list");
					Iterator iter2 = list.iterator();
					while (iter2.hasNext()) {
						Element elem2 = (Element) iter2.next();
						ContactGroup cg = new ContactGroup(elem2.attributeValue("name"), Integer.parseInt(elem2.attributeValue("id")));
						groups.add(cg);
					}
				} else if (elem.getName().equals("buddies") || elem.getName().equals("mobile-buddies")) {// 联系人信息
					List list = null;
					if (elem.getName().equals("buddies"))
					list = elem.elements("buddy");
					if (elem.getName().equals("mobile-buddies"))
						list = elem.elements("mobile-buddy");
					Iterator iter2 = list.iterator();
					while (iter2.hasNext()) {
						Element elem2 = (Element) iter2.next();
						String uri = elem2.attributeValue("uri");
						String lname = elem2.attributeValue("local-name");
						String userid = elem2.attributeValue("user-id");
						int groupid = 0;
						try {
							groupid = Integer.parseInt(elem2.attributeValue("buddy-lists"));
						} catch (Exception e) {
							groupid = 0;
						}
						Contact ct = new Contact(uri);
						ct.lname = lname;
						ct.userid = userid;
						ct.groupid = groupid;

						for (int i = 0; i < groups.size(); i++) {
							if (groups.get(i).id == groupid) {
								groups.get(i).Contacts.add(ct);
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
		}
		return groups;
	}

	/**
	 * 构建发送指令
	 * 
	 * @param cmd
	 * @param fields
	 * @param args
	 * @return
	 */
	public String buildSIPPRequest(String cmd, String[] fields, String args) {
		StringBuffer sb = new StringBuffer();
		sb.append(cmd).append(" fetion.com.cn SIP-C/2.0").append("\r\n");
		for (int i = 0; i < fields.length; i++) {
			sb.append(fields[i]).append("\r\n");
		}
		sb.append("Q: 1 ").append(cmd).append("\r\n");
		if (!args.equals("")) {
			try {
				sb.append("L: ").append(args.getBytes("UTF-8").length).append("\r\n\r\n").append(args);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			sb.append("\r\n");
		}
		// System.out.println("[SEND]>>>>>>=================================");
		// System.out.println(sb.toString());
		// System.out.println("=============================================");
		return sb.toString();
	}

	/**
	 * 分析接收数据的结果
	 * 
	 * @param data
	 * @return
	 */
	public boolean getSIPPResponse(String data) {
		boolean result = false;
		int reqCode = 0;
		int reqI = 0;
		//int reqL = 0;
		String reqArgs = "";
		//String reqQ = "";
		ArrayList<String> list = centerStr(data, "([^\r\n]+)\r\n");
		String dataLine = "";
		// System.out.println(data);
		for (int i = 0; i < list.size(); i++) {
			dataLine = list.get(i);
			if (i == 0) {
				reqCode = Integer.parseInt(FetionUtil.getInstance().centerStr(dataLine, " (\\d+) ").get(0));
			} else if (dataLine.charAt(0) == 'Q') {
				//reqQ = FetionUtil.getInstance().centerStr(dataLine, "Q: \\d+ ([a-zA-Z]+)").get(0);
			} else if (dataLine.charAt(0) == 'I') {
				reqI = Integer.parseInt(FetionUtil.getInstance().centerStr(dataLine, "I: (\\d+)").get(0));
			} else if (dataLine.charAt(0) == 'L') {
				//reqL = Integer.parseInt(FetionUtil.getInstance().centerStr(dataLine, "L: (\\d+)").get(0));
				reqArgs = FetionUtil.getInstance().centerStr(data, "\r\n\r\n(.*)").get(0);
				System.out.println(reqArgs);
			}
			System.out.println("Data:" + dataLine);
		}
		switch (reqI) {
		case 3:
			FetionSocket.contactList = getContactsList(reqArgs);
			break;
		}
		switch (reqCode) {
		case 200:
			result = true;
			break;
		case 401:
			result = false;
			break;
		}
		return result;
	}
}
