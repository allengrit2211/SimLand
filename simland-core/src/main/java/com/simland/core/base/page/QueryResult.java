/**
 * Copyright 2011 Eric Microsystems,Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
package com.simland.core.base.page;

import java.util.List;

/**
 * <li>描述:查询结果集，包括数据和总数</li><br>
 * <li>This is about <code>QueryResult</code></li>
 * 
 * @author 
 * @version 1.0
 * @date 2011-6-22 下午04:46:47
 */
public class QueryResult<T> {

	/** 查询得出的数据List **/
	private List<T> resultList;
	/** 查询得出的总数 **/
	private int totalRecord;

	public QueryResult() {

	}

	public QueryResult(List<T> resultList, int totalRecord) {
		this.resultList = resultList;
		this.totalRecord = totalRecord;

	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
