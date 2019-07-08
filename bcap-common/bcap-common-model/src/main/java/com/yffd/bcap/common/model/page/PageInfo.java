package com.yffd.bcap.common.model.page;

import java.io.Serializable;

public class PageInfo implements Serializable {
    private static final long serialVersionUID = 8174130914913692186L;
    /** 开始页码为1*/
    public static final Integer PAGE_NUM_MIN = 1;
    /** 每页最小记录数(1) */
    public static final Integer PAGE_SIZE_MIN = 1;
    /** 每页最大记录数(100) */
    public static final Integer PAGE_SIZE_MAX = 100;

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalRecord;

    public PageInfo(Integer pageNum, Integer pageSize) {
        // pageNum >= PAGE_NUM_MIN
        this.pageNum = pageNum < PAGE_NUM_MIN ? PAGE_NUM_MIN : pageNum;
        // pageSize in [ PAGE_SIZE_MIN, PAGE_SIZE_MAX ]
        this.pageSize = pageSize > PAGE_SIZE_MAX ? PAGE_SIZE_MAX : pageSize < PAGE_SIZE_MIN ? PAGE_SIZE_MIN : pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord < 0 ? 0 : totalRecord;
    }

    public Integer getTotalPage() {
        return this.getTotalRecord() % this.getPageSize() == 0 ?
                this.getTotalRecord() / this.getPageSize() : this.getTotalRecord() / this.getPageSize() + 1;
    }

    public Integer getStartIndex() {
        return (this.getPageNum() - 1) * this.getPageSize();
    }

    public Integer getEndIndex() {
        return this.getPageNum() * this.getPageSize();
    }
}
