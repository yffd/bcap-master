package com.yffd.bcap.common.ddd.application;

public interface IBaseCmd<C> {

    /**
     * 新增
     * @param context
     */
    void add(C context);

    /**
     * 编辑
     * @param context
     */
    void edit(C context);

    /**
     * 移除
     * @param context
     */
    void remove(C context);

    /**
     * 判断是否存在
     * @param context
     * @return
     */
    Boolean exsist(C context);

    /**
     * 判断是否存在，并且唯一
     * @param context
     * @return
     */
    Boolean exsistAndUnique(C context);

}
