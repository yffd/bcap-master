package com.yffd.bcap.common.support.persistence.jpa;

import com.yffd.bcap.common.ddd.domain.data.DataObject;
import com.yffd.bcap.common.ddd.domain.repository.RepositorySupport;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class JpaRepositorySupport<D extends DataObject> implements RepositorySupport<D> {
    private static final Logger LOG = LoggerFactory.getLogger(JpaRepositorySupport.class);

    public abstract String getDataId(D data);
    public abstract void setDataId(D data, String id);
    public abstract JpaRepository<D, String> repository();

    @Override
    public void insertOne(D data) {
        /*if (BcapStringUtils.isNotEmpty(getDataId(data))) {
            Optional<D> optional = this.repository().findById(getDataId(data));
            D po = optional.orElse(null);
            if (null != po)
                throw DomainException.ERROR("数据已存在[id="+ getDataId(data) +", class="+ data.getClass() +"]");
        }*/
        if (BcapStringUtils.isEmpty(getDataId(data))) setDataId(data, this.nextIdentity());
        this.repository().save(data);
    }

    @Override
    @Transactional
    public void updateById(D data) {
        Optional<D> optional = this.repository().findById(getDataId(data));
        D po = optional.orElse(null);
        if (null == po) {
            LOG.warn("更新数据不存在[id="+ getDataId(data) +", class="+ data.getClass() +"]");
            return;
        }
        this.copyPropsIgnoreNull(data, po);
        this.copyPropsForUpdate(data, po);
        this.repository().save(po);
    }

    @Override
    public void deleteById(String id) {
        try {
            this.repository().deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOG.warn("删除的数据不存在[id="+ id +", class="+ this.getGenericType() +"]");
        }
    }

    @Override
    public D findById(String id) {
        Optional<D> optional = this.repository().findById(id);
        return optional.orElse(null);
    }


    /**
     * 设置修改属性值
     * @param source
     * @param target
     */
    protected void copyPropsForUpdate(DataObject source, DataObject target) {
        target.setUpdateBy(source.getUpdateBy());
        target.setUpdateTime(source.getUpdateTime());
        target.setVersion(source.getVersion() + 1);
    }

    /**
     * 拷贝非空对象属性值
     * @param source
     * @param target
     */
    protected void copyPropsIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取到对象中属性为null的属性名
     * @param source
     * @return
     */
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}
