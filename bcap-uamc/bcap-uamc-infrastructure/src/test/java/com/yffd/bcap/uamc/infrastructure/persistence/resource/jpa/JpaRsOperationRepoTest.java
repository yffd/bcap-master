package com.yffd.bcap.uamc.infrastructure.persistence.resource.jpa;

import com.yffd.bcap.common.ddd.domain.data.DataObjectHelper;
import com.yffd.bcap.uamc.UamcApplicationTest;
import com.yffd.bcap.uamc.domain.constants.enums.ActiveStateEnum;
import com.yffd.bcap.uamc.domain.model.resource.RsOperationData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRsOperationRepoTest extends UamcApplicationTest {
    @Autowired
    private JpaRsOperationRepo jpaRsOperationRepo;

    @Test
    public void insertXxxTest() {
        for (int i = 100; i<125; i++) {
            RsOperationData data = new RsOperationData();
            data.setOprtName("操作_" + i);
            data.setOprtState(ActiveStateEnum.ACTIVE.getCode());
            data.setOprtUrl("xx/yy/" + i);
            data.setNum(i);
            data.setRemark("测试");
            DataObjectHelper.initPropsForAdd(data, sysOperator());
            this.jpaRsOperationRepo.insertOne(data);
        }
    }

}
