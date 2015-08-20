package com.simland.core.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

			return webPath + "/" + fileName;
		} catch (Exception e) {
			logger.error("copyFile error:" + e.getMessage());
			return "";
		}
	}

}
