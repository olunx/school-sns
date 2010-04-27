package cn.gdpu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageResize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//String fileName = "map.jpg";
		//String fileDir = "I:\\Users\\olunx\\Desktop";

		//new ImageResize().cut(fileName, fileDir, 0, 0, 320, 240);
		//new ImageResize().zoom(fileName, fileDir, 0, 0, 1024, 768);
		
		//s = (Student)p;
	}

	// 剪裁图片
	public String cut(String fileName, String fileDir, int x1, int y1, int width, int height) {
		return this.process(fileName, fileDir, x1, y1, width, height, 0);
	}

	// 缩放图片
	public String zoom(String fileName, String fileDir, int x1, int y1, int width, int height) {
		return this.process(fileName, fileDir, x1, y1, width, height, 1);
	}

	/**
	 * 返回新图片的文件名。
	 * 
	 * @param fileName
	 * @param fileDir
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 * @return
	 */
	private String process(String fileName, String fileDir, int x1, int y1, int width, int height, int which) {

		File src = new File(fileDir + "\\" + fileName);

		String minFileName = null;

		String minFilePath = null;

		try {

			InputStream input = new FileInputStream(src);

			Log.init(getClass()).info("图片路径： " + src);

			BufferedImage bufferedImage = null;

			switch (which) {
			case 0: {
				minFileName = "cut_" + fileName;
				minFilePath = fileDir + "\\" + minFileName;

				bufferedImage = ImageIO.read(input);
				break;
			}
			case 1: {
				minFileName = "min_" + fileName;
				minFilePath = fileDir + "\\" + minFileName;
				bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Image image = ImageIO.read(input).getScaledInstance(width, height, Image.SCALE_SMOOTH);
				bufferedImage.getGraphics().drawImage(image, 0, 0, null);
				break;
			}
			}

			bufferedImage = bufferedImage.getSubimage(x1, y1, width, height);

			FileOutputStream fos = new FileOutputStream(minFilePath);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

			encoder.encode(bufferedImage);

			Log.init(getClass()).info("转换成功...");

			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.init(getClass()).info(minFileName);
		return minFileName;
	}

}
