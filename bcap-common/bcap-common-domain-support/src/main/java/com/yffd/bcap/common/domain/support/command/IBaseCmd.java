package com.yffd.bcap.common.domain.support.command;

public interface IBaseCmd<C> {

    void add(C context);

    void edit(C context);

    void remove(C context);

    Boolean exsist(C context);

    Boolean exsistAndUnique(C context);

}
