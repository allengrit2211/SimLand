package com.simland.core.base.page;

import java.io.Serializable;
import java.util.List;

/*******************************************************************************
 * 
 * <p>
 * PageUtil
 * </p>
 * 
 * @author Leezh
 * 
 */
public class PageUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count = 0; // 记录总数
	private int pageSize = 20; // 每页显示记录数
	private int pageCount = 0; // 总页数
	private int page = 1; // 当前页数
	private int firstResult = 0;// 开始记录数
	private int lastResult = 0;
	private List<?> result;

	public PageUtil() {
	}

	/***************************************************************************
	 * 
	 * @param page
	 */
	public PageUtil(int page) {
		this.page = page;
	}

	/***************************************************************************
	 * 
	 * @param count
	 * @param pageSize
	 */
	public PageUtil(int pageSize, int page) {
		this.pageSize = pageSize;
		this.page = page;
	}

	/***************************************************************************
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            页大小
	 * @param page
	 *            所要到达的页
	 */
	public PageUtil(int count, int pageSize, int page) {
		this.count = count;
		this.pageSize = pageSize;
		this.page = page;
		this.execuePage();
	}

	/***************************************************************************
	 * 
	 * @return
	 */
	public int execuePage() {
		this.pageCount = ((count % pageSize == 0) ? (count / pageSize) : (count
				/ pageSize + 1));
		this.toPage(this.page);
		return this.pageCount;
	}

	/***************************************************************************
	 * 
	 * @param count
	 * @return
	 */
	public int execuePage(int count) {
		this.count = count;
		this.pageCount = ((this.count % pageSize == 0) ? (this.count / pageSize)
				: (this.count / pageSize + 1));
		this.toPage(this.page);
		return this.pageCount;
	}

	/***************************************************************************
	 * 
	 * @param toPage
	 */
	public void toPage(int toPage) {
		this.page = toPage;
		if (this.page > this.pageCount) {
			this.page = this.pageCount;
		} else if (this.page <= 0) {
			this.page = 1;
		}
		this.setResult(this.page);
	}

	/***************************************************************************
	 * 
	 * @param showPage
	 */
	private void setResult(int showPage) {
		this.firstResult = (showPage - 1) * pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public int getLastResult() {
		if (this.firstResult + this.pageSize > this.count) {
			return lastResult = this.count;
		}
		lastResult = this.firstResult + this.pageSize;
		return lastResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public List<?> getResult() {
		return result;
	}

	public void setResult(List<?> result) {
		this.result = result;
	}

}
