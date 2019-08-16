package com.yffd.bcap.common.exception;

import com.yffd.bcap.common.model.exception.SysException;
import org.junit.Test;

public class SysExceptionTest {

    @Test
    public void test() {
        try {
            m1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m1() {
        if (true)
//            throw new SysException("code-123", "测试异常");
            throw new SysException("code-123", "测试异常", new SysException("code-123", "测试异常www"));

    }
}
