package com.yffd.bcap.uamc.infrastructure.persistence.user.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.user.dto.UserCondition;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class JpaUserSpec {

    public static Specification<UserData> build(UserCondition condition) {
        Specification<UserData> spec = new Specification<UserData>() {
            @Override
            public Predicate toPredicate(Root<UserData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (null == condition) return spec;
        if (BcapStringUtils.isNotEmpty(condition.getUserName())) spec = spec.and(userNameLike(condition.getUserName()));
        if (BcapStringUtils.isNotEmpty(condition.getUserId())) spec = spec.and(userIdEqual(condition.getUserId()));
        if (null != condition.getStartTime()) spec = spec.and(createTimeGreaterThanOrEqual(condition.getStartTime()));
        if (null != condition.getEndTime()) spec = spec.and(createTimeLessThanOrEqual(condition.getEndTime()));
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
