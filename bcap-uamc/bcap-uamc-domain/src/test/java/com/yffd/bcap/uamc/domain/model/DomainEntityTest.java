package com.yffd.bcap.uamc.domain.model;

import com.yffd.bcap.common.model.system.SysOperator;
import org.junit.Before;

import java.util.Calendar;

import static org.mockito.Mockito.spy;

public abstract class DomainEntityTest {
    protected SysOperator sysOperator = spy(SysOperator.class);

    @Before
    public void setup() {
        sysOperator.setUserId("junit-test");
        sysOperator.setUserName("单元测试");
        sysOperator.setOperateTime(Calendar.getInstance().getTime());
    }
}
