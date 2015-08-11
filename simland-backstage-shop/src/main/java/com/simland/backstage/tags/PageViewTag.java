package com.simland.backstage.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.simland.core.base.page.PageView;

public class PageViewTag extends TagSupport {

	private PageView pageView;
	private String baseUrl;
	private Map<String, String> parameterMap = new HashMap<String, String>();

	public PageView getPageView() {
		return pageView;
	}

	public void setPageView(PageView pageView) {
		this.pageView = pageView;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			JspWriter out = pageContext.getOut();

			int currentPage = pageView.getCurrentPage();
			int pageSize = pageView.getCurrentPage();
			int totalPage = pageView.getTotalPage();

			String parameter = "";
			parameter = parameter + "&pageSize=" + pageSize;

			for (Entry<String, String> e : parameterMap.entrySet()) {
				parameter = parameter + "&" + e.getKey() + "=" + e.getValue();
			}

			if (baseUrl.contains("?"))
				baseUrl = baseUrl + "&";
			else
				baseUrl = baseUrl + "?";

			String firstPageUrl = baseUrl + "currentPage=1" + parameter;
			String lastPageUrl = baseUrl + "currentPage=" + totalPage + parameter;
			String prePageUrl = baseUrl + "currentPage=" + (currentPage - 1) + parameter;
			String nextPageUrl = baseUrl + "currentPage=" + (currentPage + 1) + parameter;

			int maxShowtotalPage = 6;
			int segment = ((currentPage - 1) / maxShowtotalPage) + 1;
			int startcurrentPage = (segment - 1) * maxShowtotalPage + 1;
			int endcurrentPage = segment * maxShowtotalPage;

			if (startcurrentPage < 1)
				startcurrentPage = 1;

			if (endcurrentPage > totalPage)
				endcurrentPage = totalPage;

			if (totalPage > 0) {
				out.println("");
				out.println("<ul class=\"pageView pages\">");
				out.println("<li class=\"pageInfo\">");
				out.println("Total " + totalPage);
				out.println("</li>");

				// <#-- 首页 -->
				if (currentPage > 1) {
					out.println("<li class=\"firstPage\">");
					out.println("<a href=\"" + baseUrl + firstPageUrl + "\">First</a>");
					out.println("</li>");

				} else {
					out.println("<li class=\"firstPage\">");
					out.println("<a class=\"currentPage\">First</a>");
					out.println("</li>");
				}

				// <#-- 上一页 -->
				if (currentPage > 1) {
					out.println("<li class=\"prePage\">");
					out.println("<a href=\"" + baseUrl + prePageUrl + "\">Pre</a>");
					out.println("</li>");
				} else {
					out.println("<li class=\"prePage\">");
					out.println("<a class=\"currentPage\">Pre</a>");
					out.println("</li>");
				}

				// <#-- 上一组 -->
				if (startcurrentPage > 1) {
					out.println("<li>");
					out.println("<a href=\"" + baseUrl + "currentPage=" + (startcurrentPage - 1) + parameter
							+ "\">...</a>");
					out.println("</li>");
				}

				for (int index = startcurrentPage; index <= endcurrentPage; index++) {
					if (currentPage != index) {
						out.println("<li>");
						out.println("<a href=\"" + baseUrl + "currentPage=" + index + parameter + "\">"+index+"</a>");
						out.println("</li>");
					} else {
						out.println("<li>");
						out.println("<a class=\"currentPage\">" + index + "</a>");
						out.println("</li>");
					}
				}

				// <#-- 下一组 -->
				if (endcurrentPage < totalPage) {
					out.println("<li>");
					out.println("<a href=\"" + baseUrl + "currentPage=" + (endcurrentPage + 1) + parameter + "\">...</a>");
					out.println("</li>");
				}

				// <#-- 下一页 -->
				if (currentPage < totalPage) {
					out.println("<li class=\"nextPage\">");
					out.println("<a href=\"" + nextPageUrl + "\">Next</a>");
					out.println("</li>");
				} else {
					out.println("<li class=\"nextPage\">");
					out.println("<a class=\"currentPage\">Next</a>");
					out.println("</li>");
				}

				// <#-- 末页 -->
				if (currentPage < totalPage) {
					out.println("<li class=\"lastPage\">");
					out.println("<a href=\"" + lastPageUrl + "\">Last</a>");
					out.println("</li>");
				} else {
					out.println("<li class=\"lastPage\">");
					out.println("<a class=\"currentPage\">Last</a>");
					out.println("</li>");
				}
				out.println("</ul>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
}
