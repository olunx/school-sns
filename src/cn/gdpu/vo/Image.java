package cn.gdpu.vo;

public class Image {

	private int id;
	private String oriFileName;
	private String oriFilePath;
	private String minFileName;
	private String minFilePath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriFileName() {
		return oriFileName;
	}

	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}

	public String getOriFilePath() {
		return oriFilePath;
	}

	public void setOriFilePath(String oriFilePath) {
		this.oriFilePath = oriFilePath;
	}

	public String getMinFileName() {
		return minFileName;
	}

	public void setMinFileName(String minFileName) {
		this.minFileName = minFileName;
	}

	public String getMinFilePath() {
		return minFilePath;
	}

	public void setMinFilePath(String minFilePath) {
		this.minFilePath = minFilePath;
	}

}
