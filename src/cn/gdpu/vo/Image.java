package cn.gdpu.vo;

public class Image {

	private int id;
	private String oriFileName;
	private String bigFileName;
	private String bigFilePath;
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

	public String getBigFileName() {
		return bigFileName;
	}

	public void setBigFileName(String bigFileName) {
		this.bigFileName = bigFileName;
	}

	public String getBigFilePath() {
		return bigFilePath;
	}

	public void setBigFilePath(String bigFilePath) {
		this.bigFilePath = bigFilePath;
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
