package com.yffd.bcap.sso.support.pwd;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class SsoPasswordUtils {
    private static final Logger logger = LoggerFactory.getLogger(SsoPasswordUtils.class);

    private static final String staticSalt = ".";
    private static final String algorithmName = "MD5";
    private static final int iterations = 2;

    public static String encodePwd(String rawPassword, String username) {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(staticSalt));
        hashService.setHashAlgorithmName(algorithmName);
        hashService.setHashIterations(iterations);
        HashRequest request = new HashRequest.Builder()
                .setSalt(username)//用户名作为动态盐
                .setSource(rawPassword)
                .build();
        return hashService.computeHash(request).toHex();
    }

    public static byte[] encodePwd(CharSequence rawPassword, byte[] salt) {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(staticSalt));
        hashService.setHashAlgorithmName(algorithmName);
        hashService.setHashIterations(iterations);
        HashRequest request = new HashRequest.Builder()
                .setSalt(salt)
                .setSource(rawPassword)
                .build();
        return hashService.computeHash(request).getBytes();
    }

}
