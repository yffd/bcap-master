package com.yffd.bcap.sso.support.pwd;

import org.junit.Test;

public class SsoPasswordUtilsTest {

    @Test
    public void encodePwdTest() {
        String encodePwd = SsoPasswordUtils.encodePwd("123456", "adminsalt");
        System.out.println(encodePwd);
    }


}
