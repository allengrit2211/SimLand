/**
 * Copyright 2011 Eric Microsystems,Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.simland.core.base.page;

import java.util.ArrayList;
import java.util.List;

/**
 * <li>描述:分页数据包装，包括分页信息和List数据</li><br>
 * <li>This is about <code>PageView</code></li>
 * 
 * @author
 * @version 1.0
 * @date 2011-6-22 下午04:46:29
 */
public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;
	/** 页码开始索引和结束索引 **/
	private PageIndex pageIndex;
	/** 总页数 **/
	private int totalPage = 1;
	/** 每页显示记录数 **/
	private int pageSize = 10; 
	/** 当前页 **/
	private int currentPage = 1;
	/** 总记录数 **/
	private int totalRecord;
	/** 每次显示多少页，必须保证大于3页，保证左右链接都可以使用 **/
	private int viewPageCount = 10;

	/** 
	 * 要获取记录的开始索引
	 * 对应SQL中的#start#
	 */
	public int getFirstResult() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public int getViewPageCount() {
		return viewPageCount;
	}

	public void setViewPageCount(int viewPageCount) {
		this.viewPageCount = viewPageCount;
	}

	public PageView(){	
		
	}
	
	public PageView(int pageSize, int currentPage) {
		this.pageSize = pageSize;
		this.currentPage = (currentPage <= 0 ? 1 : currentPage);
	}

	public PageView(int currentPage) {
		this.currentPage = (currentPage <= 0 ? 1 : currentPage);
	}

	public void setQueryResult(QueryResult<T> qr) {
		setTotalRecord(qr.getTotalRecord());
		setRecords(qr.getResultList());
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		setTotalPage(this.totalRecord % this.pageSize == 0 ? this.totalRecord
				/ this.pageSize : this.totalRecord / this.pageSize + 1);
	}

	@SuppressWarnings("unchecked")
	public List<T> getRecords() {
		if(records == null){
			return new ArrayList(0);
		}
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public int getTotalPage() {
		// 重新计算总页数
		setTotalPage(this.totalRecord % this.pageSize == 0 ? this.totalRecord
				/ this.pageSize : this.totalRecord / this.pageSize + 1);
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		this.pageIndex = PageIndex.getPageIndex(viewPageCount, currentPage,
				totalPage);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setPageIndex(PageIndex pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage<=0)
			currentPage = 1;
		this.currentPage = currentPage;
	}
	
	/**
	 * 对应SQL中的#endSize#
	 * @return
	 */
	public int getMaxResult() {
		return this.currentPage * this.pageSize;
	}

}
