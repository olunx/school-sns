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

	private ImageService<Image, Integer> imageService;
	private PeopleService<People, Integer> peopleService;

	private Image image;

	private int x;
	private int y;
	private int width;
	private int height;

	@Override
	public String goModify() {
		Object author = this.getSession().get("user");
		Log.init(getClass()).info("add3 " + author);
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				image = people.getAvatar();
			}
		}

		return super.goModify();
	}

	@Override
	public String modify() {

		Object author = this.getSession().get("user");
		Log.init(getClass()).info("add3 " + author);
		if (author != null) {
			if (author instanceof People) {
				People people = (People) author;
				image = people.getAvatar();

				String fileName = image.getOriFileName();
				String fileDir = ServletActionContext.getServletContext().getRealPath("/upload");

				String minFileName = new ImageResize().resize(fileName, fileDir, x, y, width, height);

				image.setMinFileName(minFileName);
				
				Log.init(getClass()).info("minFileName添加： " + minFileName);
				
				imageService.updateEntity(image);
			}
		}

		return super.INDEX;
	}

	@Override
	public String add() {
		if (filesFileName != null && filesFileName.size() > 0) {
			Object author = this.getSession().get("user");
			Log.init(getClass()).info("add333 " + author);
			if (author != null) {
				if (author instanceof People) {
					People people = (People) author;
					Log.init(getClass()).info("people name " + people.getName());
					image = new Image();
					image.setOriFileName(filesFileName.get(0));
					image.setOriFilePath(targetsFileUrl.get(0));
					imageService.addEntity(image);
					Log.init(getClass()).info(image.getOriFileName());
					people.setAvatar(image);
					peopleService.updateEntity(people);
					return super.goModify();
				}
			}
		}

		return super.INDEX;
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

}
