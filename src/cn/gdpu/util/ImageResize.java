package cn.gdpu.util;

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

		String fileName = "map.jpg";
		String fileDir = "C:\\Users\\olunx\\Desktop";

		new ImageResize().resize(fileName, fileDir, 0, 0, 320, 240);
	}

	/**
	 * 剪裁图片大小，返回新图片的文件名。
	 * 
	 * @param fileName
	 * @param fileDir
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 * @return
	 */
	public String resize(String fileName, String fileDir, int x1, int y1, int width, int height) {

		File src = new File(fileDir + "\\" + fileName);

		String minFileName = "min_" + fileName;

		String minFilePath = fileDir + "\\" + minFileName;

		try {

			InputStream input = new FileInputStream(src);
			
			Log.init(getClass()).info("图片路径： " + src);
			
			BufferedImage bufferedImage = ImageIO.read(input);

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
