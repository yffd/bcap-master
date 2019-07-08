package com.yffd.bcap.common.ddd.application.command.handler;

/**
 * 命令控制器
 * @param <ICommand>
 */
public interface ICommandHandler<ICommand> {

    /**
     * 命令执行
     * @param cmd
     */
    void execute(ICommand cmd);

}
