package com.yffd.bcap.uamc;

import com.yffd.bcap.common.model.system.SysOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UamcApplicationTest {
    protected final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SysOperator sysOperator;

    static {
        sysOperator = new SysOperator();
        sysOperator.setOperatorId("test_id");
        sysOperator.setOperatorName("test");
        sysOperator.setOperateTime(Calendar.getInstance().getTime());
    }

    protected SysOperator sysOperator() {
        return sysOperator;
    }

    @Test
    public void test() {
    }
}
