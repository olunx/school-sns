package cn.gdpu.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.gdpu.util.Log;

@SuppressWarnings("serial")
public class FileUploadAction extends BaseAction {

	// 页面中配置，文件对象
	private List<File> files;
	// 系统默认注入
	private List<String> filesFileName;
	private List<String> filesContentType;

	// struts配置文件中设置
	private String savePath;

	private ServletContext context = ServletActionContext.getServletContext();

	private List<String> targetsFilePath;//真实路径
	private List<String> targetsFileUrl;//相对路径 /upload/xxx
	private List<String> targetsFileName;//修改后的文件名

	// 上传图片
	public String image() {
		Log.init(getClass()).info("--------上传 图片");
		return this.upload(".jpg.jpeg.gif.png.bmp");
	}

	// 上传学生信息文件
	public String people() {
		Log.init(getClass()).info("--------上传学生信息文件 ");
		return this.upload(".xls");
	}

	// 上传课程表文件
	public String course() {
		Log.init(getClass()).info("--------上传 课程表文件");
		return this.upload(".xls");
	}

	// 上传成绩表文件
	public String score() {
		Log.init(getClass()).info("--------成绩表上传 ");
		return this.upload(".xls");
	}

	private String upload(String allowedType) {

		if (files == null) {
			Log.init(getClass()).info("files is null");
			return super.INDEX;
		}

		targetsFilePath = new ArrayList<String>();
		targetsFileUrl = new ArrayList<String>();
		targetsFileName = new ArrayList<String>();
		
		// 获取在服务器中的目录
		String targetDirectory = context.getRealPath(savePath);
		String targetFileName = null;
		File targetFile = null;
		int n = files.size();
		for (int i = 0; i < n; i++) {
			Log.init(getClass()).info("filesFileName " + filesFileName);
			String fileExt = extraFileExt(filesFileName.get(i));
			if (!allowedType.contains(fileExt)) {
				Log.init(getClass()).info("不能上传非 " + allowedType + " 类型的文件");
				return super.INDEX;
			}

			targetFileName = generateFileName(filesFileName.get(i));
			targetFile = new File(targetDirectory, targetFileName);

			try {
				FileUtils.copyFile(files.get(i), targetFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			targetsFileUrl.add(savePath + "/" + targetFileName);
			targetsFilePath.add(targetDirectory + "\\" + targetFileName);
			targetsFileName.add(targetFileName);
		}

		//this.getSession().put("filesFileName", filesFileName);
		//this.getSession().put("targetsFileUrl", targetsFileUrl);
		//this.getSession().put("targetsFileName", targetsFileName);
		Log.init(getClass()).info("-----------------上传成功 " + targetsFilePath);
		return super.SUCCESS;
	}

	// 得到扩展名
	private String extraFileExt(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}

	// 产生唯一的文件名
	private synchronized String generateFileName(String filename) {
		String ext = extraFileExt(filename);
		return System.nanoTime() + ext;
	}

	public List<File> getFiles() {
		return files;
	}

	public List<String> getTargetsFileUrl() {
		return targetsFileUrl;
	}

	public void setTargetsFileUrl(List<String> targetsFileUrl) {
		this.targetsFileUrl = targetsFileUrl;
	}

	public List<String> getTargetsFileName() {
		return targetsFileName;
	}

	public void setTargetsFileName(List<String> targetsFileName) {
		this.targetsFileName = targetsFileName;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public List<String> getTargetsFilePath() {
		return targetsFilePath;
	}

	public void setTargetsFilePath(List<String> targetsFilePath) {
		this.targetsFilePath = targetsFilePath;
	}

}
