package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.resource.dto.RsOperationCriteria;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaRsOperationSpec {

    public static Specification<RsOperationData> build(RsOperationCriteria criteria) {
        Specification<RsOperationData> spec = new Specification<RsOperationData>() {
            @Override
            public Predicate toPredicate(Root<RsOperationData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (BcapStringUtils.isNotEmpty(criteria.getOprtId())) spec = spec.and(oprtIdEqual(criteria.getOprtId()));
        if (BcapStringUtils.isNotEmpty(criteria.getOprtName())) spec = spec.and(oprtNameLike(criteria.getOprtName()));
        if (BcapStringUtils.isNotEmpty(criteria.getOprtState())) spec = spec.and(oprtStateEqual(criteria.getOprtState()));
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
