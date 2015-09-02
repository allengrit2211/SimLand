package com.simland.backstage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.simland.core.base.Utils;

@Controller
public class ImgUploadController {

	/**
	 * 图片上传
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/commodity/imgUploadAction")
	public String imgUpload(HttpServletRequest request, @RequestParam MultipartFile upload, HttpServletResponse response)
			throws IOException {

		if (upload == null || upload.getSize() == 0) {
			return null;
		}

		
		
		String uploadContentType = upload.getContentType();

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");
		String expandedName = ""; // 文件扩展名
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'',"
					+ "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}
		if (upload.getSize() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return null;
		}

		String webPath = null;
		String path = request
				.getSession()
				.getServletContext()
				.getRealPath(
						webPath = "/images/commodityGraphic/" + new SimpleDateFormat("yyyy/MM/dd/").format(new Date()));

		// file.getOriginalFilename()
		String fileName = java.util.UUID.randomUUID() + "." + expandedName;
		FileUtils.copyInputStreamToFile(upload.getInputStream(), new File(path, fileName));

		// 返回"图像"选项卡并显示图片 request.getContextPath()为web项目名
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + request.getContextPath()
				+ webPath + fileName + "','')");
		out.println("</script>");
		return null;
	}

}
