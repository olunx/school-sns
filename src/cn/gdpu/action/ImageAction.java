package cn.gdpu.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.ImageService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.ImageResize;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class ImageAction extends BaseAction {

	private List<String> filesFileName;
	private List<String> targetsFileUrl;
	private List<String> targetsFileName;

	private ImageService<Image, Integer> imageService;
	private PeopleService<People, Integer> peopleService;

	private Image image;

	private int x;
	private int y;
	private int width;
	private int height;

	public String goUpload() {
		return "uploadPage";
	}
	
	public String upload() {
		Log.init(getClass()).info("targetsFileUrl " + targetsFileUrl);
		Log.init(getClass()).info("filesFileName " + filesFileName);
		Log.init(getClass()).info("targetsFileName " + targetsFileName);

		image = new Image();
		image.setOriFileName(filesFileName.get(0));
		image.setBigFileName(targetsFileName.get(0));
		image.setBigFileUrl(targetsFileUrl.get(0));

		// 处理缩略图
		String fileDir = ServletActionContext.getServletContext().getRealPath("/upload");
		String cutFileName = new ImageResize().cut(targetsFileName.get(0), fileDir, x, y, width, height);
		String miniFileName = new ImageResize().zoom(cutFileName, fileDir, 0, 0, 200, 200);
		image.setMinFileName(miniFileName);
		image.setMinFileUrl("/upload/" + miniFileName);

		Log.init(getClass()).info(image.getOriFileName());

		return super.INDEX;
	}
	
	@Override
	public String add() {
		Log.init(getClass()).info(image.getOriFileName());
		imageService.addEntity(image);
		return super.add();
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getTargetsFileUrl() {
		return targetsFileUrl;
	}

	public void setTargetsFileUrl(List<String> targetsFileUrl) {
		this.targetsFileUrl = targetsFileUrl;
	}

	public ImageService<Image, Integer> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<String> getTargetsFileName() {
		return targetsFileName;
	}

	public void setTargetsFileName(List<String> targetsFileName) {
		this.targetsFileName = targetsFileName;
	}

}
