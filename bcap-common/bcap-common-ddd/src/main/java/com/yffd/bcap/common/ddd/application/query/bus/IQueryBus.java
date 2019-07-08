package com.yffd.bcap.common.ddd.application.query.bus;

import com.yffd.bcap.common.ddd.application.query.IBaseQuery;

/**
 * 消息总线-查询
 */
public interface IQueryBus {

    /**
     * 发送查询
     * @param query
     */
    void send(IBaseQuery query);
}
