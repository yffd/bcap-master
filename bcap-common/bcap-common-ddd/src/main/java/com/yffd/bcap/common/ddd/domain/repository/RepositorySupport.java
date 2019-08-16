package com.yffd.bcap.common.ddd.domain.repository;

import com.yffd.bcap.common.model.exception.SysException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 领域厂库接口
 * @param <D>   数据实体
 */
public interface RepositorySupport<D> extends IRepository {

    void insertOne(D data);

    void updateById(D data);

    void deleteById(String id);

    D findById(String id);

    default Class<D> getGenericType() {
        Type genericClazz = this.getClass().getGenericSuperclass();
        if(genericClazz instanceof ParameterizedType) {
            return (Class<D>) ((ParameterizedType) genericClazz).getActualTypeArguments()[0];
        } else {
            throw SysException.instance("泛型解析错误");
        }
    }

}
