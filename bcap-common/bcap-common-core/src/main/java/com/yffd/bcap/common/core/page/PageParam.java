package com.yffd.bcap.common.core.page;

import java.io.Serializable;

/**
 * 分页信息基类对象.
 */
public class PageParam implements Serializable {
	private static final long serialVersionUID = 6844568491810672063L;

	/** 开始页码为1*/
    public static final Integer PAGE_NUM_START = 1;
    /** 每页最小记录数(15) */
    public static final Integer PAGE_LIMIT_MIN = 1;
    /** 每页最大记录数(100) */
    public static final Integer PAGE_LIMIT_MAX = 100;
    
    private Integer pageNum; 		// 当前页数
    private Integer pageLimit; 		// 每页记录数
    private Integer totalRecord; 	// 总记录数
    
    /** 是否进行自动count计算总条数，默认为自动计算：true:是，false:否 */
//    private boolean autoCount = true;
    
	public PageParam(Integer pageNum, Integer pageLimit) {
		//当前页码不能小于1，如果小于将返回最小页码
		pageNum = pageNum < 1 ? PAGE_NUM_START : pageNum;
		// pageLimit in [ PAGE_LIMIT_MIN, PAGE_LIMIT_MAX ] 范围
		pageLimit = pageLimit > PAGE_LIMIT_MAX ? PAGE_LIMIT_MAX : pageLimit < 1 ? PAGE_LIMIT_MIN : pageLimit;
		this.pageNum = pageNum;
		this.pageLimit = pageLimit;
	}

	/**
	 * 当前页码不能小于1，如果小于将返回最小页码{@link #PAGE_NUM_START}
	 * @return
	 */
	public Integer getPageNum() {
		return this.pageNum;
	}

	/**
	 * 每页记录数
	 * @return
	 */
	public Integer getPageLimit() {
		return this.pageLimit;
	}

	/**
	 * 总记录数
	 * @return
	 */
	public Integer getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 总记录数
	 * @param totalRecord
	 */
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord < 0 ? 0 : totalRecord;
	}

	/**
	 * 总页数
	 * @return
	 */
	public Integer getTotalPage() {
		return this.getTotalRecord() % this.getPageLimit() == 0 ? 
				this.getTotalRecord() / this.getPageLimit() : this.getTotalRecord() / this.getPageLimit() + 1;
	}

	/**
	 * 每页开始行号
	 * @return
	 */
	public Integer getStartIndex() {
		return (this.getPageNum() - 1) * this.getPageLimit();
	}

	/**
	 * 每页结束行号
	 * @return
	 */
	public Integer getEndIndex() {
		return this.getPageNum() * this.getPageLimit();
	}
    
}

