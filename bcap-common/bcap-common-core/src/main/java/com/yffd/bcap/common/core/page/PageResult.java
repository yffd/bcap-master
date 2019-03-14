package com.yffd.bcap.common.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果基类对象.
 * @param <T>
 */
public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = -6241544541048563186L;
	
	private PageParam pageParam; // 分页信息
	private List<T> recordList = new ArrayList<T>(0); // 本页的数据列表
	
	public PageResult() {
	}
	
	public PageResult(PageParam pageParam, List<T> recordList) {
		this.pageParam = pageParam;
		this.recordList = recordList;
	}

	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

}

