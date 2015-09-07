package com.simland.core.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.simland.core.module.shop.entity.Commodity;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ConstantsImage {

	public static final Log logger = LogFactory.getLog(ConstantsImage.class);

	public static final String IMAGE_URL = "/simland-app-service";

	public static final String COMMODITY = "commodity";

	public static final String SHOP_BANNER = "shop/banner";

	/***
	 * 复制商品图到新目录
	 * 
	 * @param srcFile
	 *            源文件目录
	 * @param newFile
	 *            新文件目录
	 * @return
	 */
	public static String copyFile(HttpServletRequest request, String newPath, String srcFile) {
		try {

			if (srcFile.indexOf("tmp") <= -1)
				return srcFile;

			String path = request.getSession().getServletContext().getRealPath("/") + srcFile;
			path = path.replaceAll("\\\\", "/");

			String webPath = null;
			String newFile = request
					.getSession()
					.getServletContext()
					.getRealPath(
							webPath = "/images/" + newPath + "/"
									+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

			newFile = newFile.replaceAll("\\\\", "/");
			newFile = newFile.replaceAll(request.getSession().getServletContext().getContextPath(), IMAGE_URL);

			InputStream is = new FileInputStream(new File(path));

			String type = srcFile.substring(srcFile.lastIndexOf(".") + 1);
			// file.getOriginalFilename()
			String fileName = java.util.UUID.randomUUID() + "." + type;

			FileUtils.copyInputStreamToFile(is, new File(newFile, fileName));

			ConstantsImage.scaleImg(path, newFile, fileName);

			return webPath + "/" + fileName;
		} catch (Exception e) {
			logger.error("copyFile error:" + e.getMessage());
			return "";
		}
	}

	/***
	 * 启动进程压缩
	 * 
	 * @param srcFile
	 * @param outFile
	 */
	public static void scaleImg(String srcFile, String outFile, String fileName) {
		try {
			ConstantsImage ci = new ConstantsImage();

			ExecutorService exec = Executors.newCachedThreadPool();
			for (String[] s : Commodity.IMGSIZE) {
				String fname = fileName.replace(fileName, fileName.substring(0, fileName.lastIndexOf(".")) + "_" + s[0]
						+ "x" + s[1]+ fileName.substring(fileName.lastIndexOf("."), fileName.length()));
				exec.execute(ci.new ImageScaleThread(srcFile, outFile + "/" + fname, s));
			}
			exec.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 图片压缩
	 * 
	 * @author Gavin
	 *
	 */
	class ImageScaleThread implements Runnable {

		private String srcFile;
		private String outFile;
		private String[] imgSize;

		public ImageScaleThread() {

		}

		public ImageScaleThread(String srcFile, String outFile, String[] imgSize) {
			this.srcFile = srcFile;
			this.outFile = outFile;
			this.imgSize = imgSize;
		}

		@Override
		public void run() {
			try {

				BufferedImage image = ImageIO.read(new File(srcFile));
				ImageScale is = new ImageScale();
				BufferedImage outImpage = null;
				FileOutputStream out = null;
				outImpage = is.imageZoomOut(image, Utils.strToInteger(Utils.getArrayVal(0, imgSize)),
						Utils.strToInteger(Utils.getArrayVal(1, imgSize)));
				out = new FileOutputStream(outFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(outImpage);
				/* 压缩质量 */
				jep.setQuality(1, true);
				encoder.encode(outImpage, jep);
				out.close();
				out.close();
				image.flush();
				ImageIO.getCacheDirectory();

			} catch (Exception e) {
				logger.error("ImageScaleThread -- Exception:" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
