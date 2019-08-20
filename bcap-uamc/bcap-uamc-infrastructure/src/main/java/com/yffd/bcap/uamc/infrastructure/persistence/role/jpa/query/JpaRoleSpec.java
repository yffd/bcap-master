package com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.role.dto.RoleConditon;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class JpaRoleSpec {

    public static Specification<RoleData> build(RoleConditon condition) {
        Specification<RoleData> spec = new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (null == condition) return spec;
        if (BcapStringUtils.isNotEmpty(condition.getRoleName())) spec = spec.and(roleNameLike(condition.getRoleName()));
        if (BcapStringUtils.isNotEmpty(condition.getRoleState())) spec = spec.and(roleStateEqual(condition.getRoleState()));
        if (null != condition.getStartTime()) spec = spec.and(createTimeGreaterThanOrEqual(condition.getStartTime()));
        if (null != condition.getEndTime()) spec = spec.and(createTimeLessThanOrEqual(condition.getEndTime()));
        return spec;
    }

    public static Specification<RoleData> createTimeLessThanOrEqual(Date endTime) {
        return new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), endTime);
            }
        };
    }

    public static Specification<RoleData> createTimeGreaterThanOrEqual(Date startTime) {
        return new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), startTime);
            }
        };
    }

    public static Specification<RoleData> roleNameLike(String value) {
        return new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("roleName"), "%"+ value +"%");
            }
        };
    }

    public static Specification<RoleData> roleStateEqual(String value) {
        return new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("roleState"), value);
            }
        };
    }

    public static Specification<RoleData> createTimeBetween(Date startTime, Date endTime) {
        return new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("createTime"), startTime, endTime);
            }
        };
    }
}
