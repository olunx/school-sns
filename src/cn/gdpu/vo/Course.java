package cn.gdpu.vo;

public class Course {
	private String name;
	private int startLesson;
	private int endLesson;
	private int whatDay;
	private int year;
	private int term;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartLesson() {
		return startLesson;
	}

	public void setStartLesson(int startLesson) {
		this.startLesson = startLesson;
	}

	public int getEndLesson() {
		return endLesson;
	}

	public void setEndLesson(int endLesson) {
		this.endLesson = endLesson;
	}

	public int getWhatDay() {
		return whatDay;
	}

	public void setWhatDay(int whatDay) {
		this.whatDay = whatDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}
}
