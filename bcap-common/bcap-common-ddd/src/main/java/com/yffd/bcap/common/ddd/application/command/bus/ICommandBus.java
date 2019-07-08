package com.yffd.bcap.common.ddd.application.command.bus;

import com.yffd.bcap.common.ddd.application.command.ICommand;

/**
 * 消息总线-命令
 */
public interface ICommandBus {

    /**
     * 发送命令
     * @param cmd
     */
    void send(ICommand cmd);
}
