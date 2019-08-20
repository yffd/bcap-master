package com.yffd.bcap.uamc.infrastructure.persistence.group.jpa.query;

import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.uamc.application.group.dto.GroupCondition;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaGroupSpec {

    public static Specification<GroupData> build(GroupCondition condition) {
        Specification<GroupData> spec = new Specification<GroupData>() {
            @Override
            public Predicate toPredicate(Root<GroupData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("delFlag"), "0");
            }
        };
        if (null == condition) return spec;
        if (BcapStringUtils.isNotEmpty(condition.getGroupName())) spec = spec.and(groupNameLike(condition.getGroupName()));
        if (BcapStringUtils.isNotEmpty(condition.getGroupState())) spec = spec.and(groupStateEqual(condition.getGroupState()));
        return spec;
    }

    public static Specification<GroupData> groupNameLike(String value) {
        return new Specification<GroupData>() {
            @Override
            public Predicate toPredicate(Root<GroupData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("groupName"), "%"+ value +"%");
            }
        };
    }

    public static Specification<GroupData> groupStateEqual(String value) {
        return new Specification<GroupData>() {
            @Override
            public Predicate toPredicate(Root<GroupData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("groupState"), value);
            }
        };
    }
}
