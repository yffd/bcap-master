package com.yffd.bcap.bpm.application.service;

public interface ProcessInstanceService {

    //启动流程
    Object startProcess(Object obj);

    //结束流程
    Object stopProcess(Object obj);

    //挂起流程
    Object suspendProcess(Object obj);

    //激活已挂起的流程
    Object reactiveProcess(Object obj);


}
