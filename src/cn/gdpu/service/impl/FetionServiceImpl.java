package cn.gdpu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fatkun.fetion.FetionSocket;

import cn.gdpu.service.FetionService;
import cn.gdpu.service.StudentService;
import cn.gdpu.vo.Student;

public class FetionServiceImpl implements FetionService {
	private StudentService studentService;

	public void sendFetion(final String phone, final String pwd, final String tophone, final String content) {

		Thread thread = new Thread() {
			List<String> list = new ArrayList<String>();
			String[] tophones = new String[100];

			@Override
			public void run() {
				try {
					tophones = tophone.split(",");
					for (int i = 0; i < tophones.length; i++) {
						list.add(tophones[i]);
					}

					if (list.size() > 0) {
						System.out.println("tophone" + tophone.length());
						FetionSocket fetionSocket;
						fetionSocket = new FetionSocket(phone, pwd);
						fetionSocket.init();
						fetionSocket.login();
						for (int i = 0; i < list.size(); i++) {
							fetionSocket.sendMsg(content, list.get(i));
						}
						fetionSocket.logout();
						fetionSocket.socketClose();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	public void sendScore(final String phone, final String pwd, final Map<String, Object> send) {
		Thread thread = new Thread() {

			@Override
			public void run() {
				try {
					FetionSocket fetionSocket;
					fetionSocket = new FetionSocket(phone, pwd);
					fetionSocket.init();
					fetionSocket.login();
					for (String key : send.keySet()) {
						Student stu = (Student) studentService.getEntity(Student.class, key);
						if (stu != null) {
							String tophone = String.valueOf(stu.getPhoneNo());
							fetionSocket.sendMsg((String)send.get(key), tophone);
						}
					}
					fetionSocket.logout();
					fetionSocket.socketClose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

}
