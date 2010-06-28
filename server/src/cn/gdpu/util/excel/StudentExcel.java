package cn.gdpu.util.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Score;
import cn.gdpu.vo.Student;

public class StudentExcel extends ReadExcel {
	private Log logger = LogFactory.getLog(this.getClass());

	private static StudentExcel studentExcel;

	public static StudentExcel getStudentExcel() {
		if (studentExcel == null) {
			studentExcel = new StudentExcel();
		}
		return studentExcel;
	}

	/**
	 * 读取Excel的内容，返回需要的属性列数据，最后一位为属性列数
	 * 
	 * @param filePath
	 * @return
	 */
	public List<Student> getStudentData(String filePath) {

		// 获取原始数据
		List<String> result = super.readExcel(filePath);

		if (result != null) {

			// 用于保存返回的数据
			List<Student> resultData = new ArrayList<Student>();

			int resultLength = result.size();
			// 获取保存的属性列数
			int columns = Integer.parseInt(result.get(resultLength - 1).toString());

			// System.out.println("columns  " + columns);
			// System.out.println("result  " + result);

			int schoolOffset = -1;
			int instituteOffset = -1;
//			int classOffset = -1;
			int entryOffset = -1;
			int sexOffset = -1;
			int nameOffset = -1;
			int qqOffset = -1;
			int snoOffset = -1;

			// 记录实际获取到的属性列数
			String text;
			int resultColumn = 0;
			for (int index = 0; index < columns; index++) {
				text = result.get(index).toString();
				// System.out.println(text);
				if (text.contains("学校")) {
					schoolOffset = index;
					resultColumn++;
				} else if (text.contains("学院")) {
					instituteOffset = index;
					resultColumn++;
				} else if (text.contains("年级")) {
					entryOffset = index;
					resultColumn++;
				} else if (text.contains("性别")) {
					sexOffset = index;
					resultColumn++;
				} else if (text.contains("姓名")) {
					nameOffset = index;
					resultColumn++;
				} else if (text.contains("QQ")) {
					qqOffset = index;
					resultColumn++;
				} else if (text.contains("学号")) {
					snoOffset = index;
					resultColumn++;
				}
			}

			// 取出数据
			int lastLoop = resultLength - columns;// 去掉最后一次循环
			School school;
			Institute ins;
//			Classes classes;
			Student stu;
			String temp;
			for (int index = columns; index < lastLoop;) {
				school = new School();
				ins = new Institute();
//				classes = new Classes();
				stu = new Student();

				if (schoolOffset != -1){
					school.setName(result.get(index + schoolOffset));
					stu.setSchool(school);
				}
				if (instituteOffset != -1){
					ins.setName(result.get(index + instituteOffset));
					stu.setInstitute(ins);
				}
//				if (classOffset != -1){
//					classes.setName(result.get(index + classOffset));
//					stu.setClasses(classes);
//				}
				if (entryOffset != -1)
					stu.setEntryYear(Integer.parseInt(result.get(index + entryOffset)));
				if (sexOffset != -1)
					stu.setSex(Integer.parseInt(result.get(index + sexOffset)));
				if (nameOffset != -1)
					stu.setName(result.get(index + nameOffset));
				if (qqOffset != -1) {
					temp = result.get(index + qqOffset);
					stu.setQq(temp);
					stu.setEmail(temp + "@qq.com");
				}
				if (snoOffset != -1) {
					temp = result.get(index + snoOffset);
					stu.setSno(temp);
					stu.setUsername(temp);
					stu.setPassword(temp);
				}

				resultData.add(stu);
				// System.out.println(stu.toString());
				index = index + columns;
			}

			return resultData;
		}

		return null;
	}

	/**
	 * 
	 *读取成绩表信息，返回字符数组
	 * 
	 * @param filePath
	 * @return
	 */
	public List<Score> getScoreData(String filePath) {

		// 获取原始数据
		List<String> result = super.readExcel(filePath);

		if (result != null) {

			// 用于保存返回的数据
			List<Score> resultScore = new ArrayList<Score>();

			int resultLength = result.size();
			// 获取保存的属性列数
			int columns = Integer.parseInt(result.get(resultLength - 1).toString());

			// 保存属性名称
			String[] columnName;
			columnName = new String[columns];
			int snoColumn = -1;// 记录学号所在的列数
			for (int i = 0; i < columns; i++) {
				columnName[i] = result.get(i);
				if (columnName[i].equals("学号")) {
					snoColumn = i;
				}
			}

			// 链接每行字符串
			int lastLoop = resultLength - columns;
			String thisSno = null;// 记录学号字符串
			for (int i = columns; i < lastLoop; i++) {
				Student stu = new Student();
				for (int j = 0; j < columns; j++) {
					if (snoColumn == j) {
						thisSno = result.get(i);
						stu.setSno(thisSno);
					}
					if (j > 1) { // 根据成绩表的格式，第一列是学号，第二列是名字，第三列开始是成绩
						Score score = new Score();
						score.setStudent(stu);
						score.setSubject(columnName[j]);
						score.setMarks(Integer.parseInt(result.get(i)));
						resultScore.add(score);
					}
					i++;
				}
				i--;
			}
			return resultScore;
		}

		return null;
	}

	/**
	 * 获取课程表
	 * 
	 * @param filePath
	 * @return
	 */
	public List<Course> getCourseData(String filePath) {

		// 获取原始数据
		List<String> result = super.readExcel(filePath);

		if (result != null) {

			// 用于保存返回的数据
			List<Course> resultData = new ArrayList<Course>();

			int resultLength = result.size();
			// 获取保存的属性列数
			int columns = Integer.parseInt(result.get(resultLength - 1).toString());

			// System.out.println("columns  " + columns);
			// System.out.println("result  " + result);

			int nameOffset = -1;
			int startLessonOffset = -1;
			int endLessonOffset = -1;
			int whatDayOffset = -1;
			int yearOffset = -1;
			int termOffset = -1;

			// 记录实际获取到的属性列数
			String text;
			int resultColumn = 0;
			for (int index = 0; index < columns; index++) {
				text = result.get(index).toString();
				// System.out.println(text);
				if (text.contains("课程名称")) {
					nameOffset = index;
					resultColumn++;
				} else if (text.contains("星期")) {
					whatDayOffset = index;
					resultColumn++;
				} else if (text.contains("起始节")) {
					startLessonOffset = index;
					resultColumn++;
				} else if (text.contains("结束节")) {
					endLessonOffset = index;
					resultColumn++;
				} else if (text.contains("学年")) {
					yearOffset = index;
					resultColumn++;
				} else if (text.contains("学期")) {
					termOffset = index;
					resultColumn++;
				}
			}

			// 取出数据
			int lastLoop = resultLength - columns;// 去掉最后一次循环
			Course course;
			for (int index = columns; index < lastLoop;) {
				course = new Course();
				if (nameOffset != -1)
					course.setName(result.get(index + nameOffset));
				if (whatDayOffset != -1)
					course.setWhatDay(Integer.parseInt(result.get(index + whatDayOffset)));
				if (startLessonOffset != -1)
					course.setStartLesson(Integer.parseInt(result.get(index + startLessonOffset)));
				if (endLessonOffset != -1)
					course.setEndLesson(Integer.parseInt(result.get(index + endLessonOffset)));
				if (yearOffset != -1)
					course.setYear(Integer.parseInt(result.get(index + yearOffset)));
				if (termOffset != -1)
					course.setTerm(Integer.parseInt(result.get(index + termOffset)));

				resultData.add(course);
				logger.info(course.toString());
				index = index + columns;
			}

			return resultData;
		}

		return null;
	}
}
