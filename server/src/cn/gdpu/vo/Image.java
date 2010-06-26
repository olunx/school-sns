package cn.gdpu.vo;

public class Image {

	private int id;
	private String oriFileName;
	private String bigFileName;
	private String bigFileUrl;
	private String minFileName;
	private String minFileUrl;

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

	public String getBigFileUrl() {
		return bigFileUrl;
	}

	public void setBigFileUrl(String bigFileUrl) {
		this.bigFileUrl = bigFileUrl;
	}

	public String getMinFileName() {
		return minFileName;
	}

	public void setMinFileName(String minFileName) {
		this.minFileName = minFileName;
	}

	public String getMinFileUrl() {
		return minFileUrl;
	}

	public void setMinFileUrl(String minFileUrl) {
		this.minFileUrl = minFileUrl;
	}

}
