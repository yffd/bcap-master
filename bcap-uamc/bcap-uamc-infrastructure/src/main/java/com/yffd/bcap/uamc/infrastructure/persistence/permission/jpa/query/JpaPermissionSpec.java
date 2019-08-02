package com.yffd.bcap.uamc.infrastructure.persistence.permission.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.permission.dto.PermissionCriteria;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaPermissionSpec {

    public static Specification<PermissionData> build(PermissionCriteria criteria) {
        Specification<PermissionData> spec = new Specification<PermissionData>() {
            @Override
            public Predicate toPredicate(Root<PermissionData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (BcapStringUtils.isNotEmpty(criteria.getPmsName())) spec = spec.and(pmsNameLike(criteria.getPmsName()));
        if (BcapStringUtils.isNotEmpty(criteria.getPmsState())) spec = spec.and(pmsStateEqual(criteria.getPmsState()));
        if (BcapStringUtils.isNotEmpty(criteria.getPmsId())) spec = spec.and(pmsIdEqual(criteria.getPmsId()));
        return spec;
    }

    public static Specification<PermissionData> pmsNameLike(String value) {
        return new Specification<PermissionData>() {
            @Override
            public Predicate toPredicate(Root<PermissionData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("pmsName"), "%"+ value +"%");
            }
        };
    }

    public static Specification<PermissionData> pmsStateEqual(String value) {
        return new Specification<PermissionData>() {
            @Override
            public Predicate toPredicate(Root<PermissionData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("pmsState"), value);
            }
        };
    }

    public static Specification<PermissionData> pmsIdEqual(String value) {
        return new Specification<PermissionData>() {
            @Override
            public Predicate toPredicate(Root<PermissionData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("pmsId"), value);
            }
        };
    }
}
