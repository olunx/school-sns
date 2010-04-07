package cn.gdpu.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class EditorUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private File filedata;
	private String fileName;
	private String savePath;
	private String imgUrl;
	
	
	

	public String execute() throws Exception {
		if(filedata == null){
			getRequest().put("err", "上传图片出错");
			return ERROR;
		}
		
		ServletContext context = ServletActionContext.getServletContext();
		
		// 获取在服务器中的目录
		String targetDirectory = context.getRealPath(savePath);
		
		File target = null;
		String targetFileName = null;
		targetFileName = generateFileName(fileName);
		target = new File(targetDirectory, targetFileName);
		
		try {
			FileUtils.copyFile(filedata, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgUrl = context.getContextPath() + savePath + "/" + targetFileName;
		getRequest().put("imgUrl", imgUrl);
		return SUCCESS;
	}

	//产生唯一的文件名  
    private synchronized String generateFileName(String filename)  
    {  
        int position=filename.lastIndexOf(".");  
        String ext=filename.substring(position);  
          
        return System.nanoTime()+ext;  
    }
	
	//Getter and Setter
    public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
	
	public void setFiledataFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
