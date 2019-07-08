package com.yffd.bcap.common.ddd.application.query.handler;

/**
 * 查询控制器
 * @param <IQuery>
 */
public interface IQueryHandler<IQuery> {

    /**
     * 查询执行
     * @param query
     */
    void execute(IQuery query);
}
