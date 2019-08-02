package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.user.dto.UserCriteria;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class JpaUserSpec {

    public static Specification<UserData> build(UserCriteria criteria) {
        Specification<UserData> spec = new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (BcapStringUtils.isNotEmpty(criteria.getUserName())) spec = spec.and(userNameLike(criteria.getUserName()));
        if (BcapStringUtils.isNotEmpty(criteria.getUserId())) spec = spec.and(userIdEqual(criteria.getUserId()));
        if (null != criteria.getStartTime()) spec = spec.and(createTimeGreaterThanOrEqual(criteria.getStartTime()));
        if (null != criteria.getEndTime()) spec = spec.and(createTimeLessThanOrEqual(criteria.getEndTime()));
        return spec;
    }



    public static Specification<UserData> userNameLike(String value) {
        return new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("userName"), "%"+ value +"%");
            }
        };
    }

    public static Specification<UserData> userIdEqual(String value) {
        return new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("userId"), value);
            }
        };
    }

    public static Specification<UserData> createTimeLessThanOrEqual(Date endTime) {
        return new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), endTime);
            }
        };
    }

    public static Specification<UserData> createTimeGreaterThanOrEqual(Date startTime) {
        return new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), startTime);
            }
        };
    }
}
