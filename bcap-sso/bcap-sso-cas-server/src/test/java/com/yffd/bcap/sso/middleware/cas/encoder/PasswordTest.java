package com.yffd.bcap.sso.middleware.cas.encoder;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordTest {
    private String staticSalt = ".";
    private String algorithmName = "MD5";
    private int iterations = 2;
    private String encodedPassword = "123456";
    private String dynaSalt = "adminsalt";

    @Test
    public void encodeMd5SaltPwdTest() {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(this.staticSalt));
        hashService.setHashAlgorithmName(this.algorithmName);
        hashService.setHashIterations(iterations);
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)
                .setSource(encodedPassword)
                .build();
        String res =  hashService.computeHash(request).toHex();
        System.out.println(res);

    }
    @Test
    public void encodeMd5PwdTest() {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encodPwd = md5PasswordEncoder.encodePassword("123456", "");
        System.out.println(encodPwd);
    }

}
