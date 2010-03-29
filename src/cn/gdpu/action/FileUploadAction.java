package cn.gdpu.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
public class FileUploadAction extends BaseAction{
	private Log logger = LogFactory.getLog(this.getClass());
	
	//页面中配置，文件对象
	private List<File> files;
	//系统默认注入
	private List<String> filesFileName;
	private List<String> filesContentType;
	
	//struts配置文件中设置
	private String savePath;

	private ServletContext context = ServletActionContext.getServletContext();
	
	//上传注册文件
	public String reg() {
		return this.upload(".xls");
	}
	
	//上传课程表文件
	public String course() {
		logger.info("--------上传 ");
		return this.upload(".xls");
	}
	
	private String upload(String allowedType) {
		
		if (files == null) {
			return super.INDEX;
		}
		
		List<String> targetsFilePath = new ArrayList<String>();
		// 获取在服务器中的目录
		String targetDirectory = context.getRealPath(savePath);
		String targetFileName = null;
		File targetFile = null;
		int n = files.size();
		for (int i = 0; i < n; i++) {
			
			String fileExt = extraFileExt(filesFileName.get(i));
			if(!fileExt.equals(allowedType)) {
				this.addFieldError("fileTypeAlert", "不能上传非 " +allowedType +" 类型的文件");
				return super.INDEX;
			}
			
			targetFileName = generateFileName(filesFileName.get(i));
			targetFile = new File(targetDirectory, targetFileName);
			
			try {
				FileUtils.copyFile(files.get(i), targetFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			targetsFilePath.add(targetDirectory + "\\" + targetFileName);
		}
		
		this.getRequest().put("targetsFilePath", targetsFilePath);
		
		return super.SUCCESS;
	}	
	
	//得到扩展名
	private String extraFileExt(String filename){
		return filename.substring(filename.lastIndexOf("."));
	}
	
	//产生唯一的文件名  
    private synchronized String generateFileName(String filename)  
    {  
        String ext=extraFileExt(filename);  
        return System.nanoTime()+ext;  
    }

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
