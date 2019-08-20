package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.resource.dto.RsOperationCondition;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaRsOperationSpec {

    public static Specification<RsOperationData> build(RsOperationCondition condition) {
        Specification<RsOperationData> spec = new Specification<RsOperationData>() {
            @Override
            public Predicate toPredicate(Root<RsOperationData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (null == condition) return spec;
        if (BcapStringUtils.isNotEmpty(condition.getOprtId())) spec = spec.and(oprtIdEqual(condition.getOprtId()));
        if (BcapStringUtils.isNotEmpty(condition.getOprtName())) spec = spec.and(oprtNameLike(condition.getOprtName()));
        if (BcapStringUtils.isNotEmpty(condition.getOprtState())) spec = spec.and(oprtStateEqual(condition.getOprtState()));
        return spec;
    }

    public static Specification<RsOperationData> oprtNameLike(String value) {
        return new Specification<RsOperationData>() {
            @Override
            public Predicate toPredicate(Root<RsOperationData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("oprtName"), "%"+ value +"%");
            }
        };
    }

    public static Specification<RsOperationData> oprtIdEqual(String value) {
        return new Specification<RsOperationData>() {
            @Override
            public Predicate toPredicate(Root<RsOperationData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("oprtId"), value);
            }
        };
    }

    public static Specification<RsOperationData> oprtStateEqual(String value) {
        return new Specification<RsOperationData>() {
            @Override
            public Predicate toPredicate(Root<RsOperationData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("oprtState"), value);
            }
        };
    }
}
