package com.yffd.bcap.uamc.infrastructure.persistence.role.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.role.dto.RoleCriteria;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class JpaRoleSpec {

    public static Specification<RoleData> build(RoleCriteria criteria) {
        Specification<RoleData> spec = new Specification<RoleData>() {
            @Override
            public Predicate toPredicate(Root<RoleData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (BcapStringUtils.isNotEmpty(criteria.getRoleName())) spec = spec.and(roleNameLike(criteria.getRoleName()));
        if (BcapStringUtils.isNotEmpty(criteria.getRoleState())) spec = spec.and(roleStateEqual(criteria.getRoleState()));
        if (null != criteria.getStartTime()) spec = spec.and(createTimeGreaterThanOrEqual(criteria.getStartTime()));
        if (null != criteria.getEndTime()) spec = spec.and(createTimeLessThanOrEqual(criteria.getEndTime()));
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
