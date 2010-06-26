package cn.gdpu.util.excel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import cn.gdpu.vo.Course;

public class GetCourseTest {

	@Test
	public void getCourse() {
		List<Course> lists = new ArrayList<Course>();
			
		lists = StudentExcel.getStudentExcel().getCourseData(
				"G:/workspace/jee/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/SchoolSNS/upload/8214564795333.xls");
		for (Course c:lists){
			System.out.println(c);
		}
	}
}
