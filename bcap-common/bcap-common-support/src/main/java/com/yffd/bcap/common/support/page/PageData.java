package com.yffd.bcap.common.support.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageData<T> implements Serializable {
    private static final long serialVersionUID = -1148799563971287709L;
    private PageInfo pageInfo;  // 分页信息
    private List<T> data = new ArrayList<>();   // 本页的数据列表

    public PageData() {
    }

    public PageData(List<T> data) {
        this.data = data;
    }

    public PageData(PageInfo pageInfo, List<T> data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
