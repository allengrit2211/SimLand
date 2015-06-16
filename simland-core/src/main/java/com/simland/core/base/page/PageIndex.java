/**
 * Copyright 2011 Eric Microsystems,Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.simland.core.base.page;

/**
 * <li>描述:页索引，主要是起始页和终止页</li><br>
 * <li>This is about <code>PageIndex</code></li>
 * 
 * @author 
 * @version 1.0
 * @date 2011-6-22 下午04:45:58
 */
public class PageIndex {
	/** 开始索引 **/
	private int startIndex;
	/** 结束索引 **/
	private int endIndex;

	public PageIndex(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * 起始页和终止页
	 * 
	 * @param viewPageCount
	 *            显示多少页
	 * @param currentPage
	 *            当前页
	 * @param totalpage
	 *            总页数
	 * @return PageIndex 起始页和终止页
	 */
	public static PageIndex getPageIndex(int viewPageCount, int currentPage,
			int totalpage) {
		int startpage = currentPage
				- (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1
						: viewPageCount / 2);
		int endpage = currentPage + viewPageCount / 2;
		if (startpage < 1) {
			startpage = 1;
			if (totalpage >= viewPageCount)
				endpage = viewPageCount;
			else
				endpage = totalpage;
		}
		if (endpage > totalpage) {
			endpage = totalpage;
			if ((endpage - viewPageCount) > 0)
				startpage = endpage - viewPageCount + 1;
			else
				startpage = 1;
		}
		return new PageIndex(startpage, endpage);
	}
}
