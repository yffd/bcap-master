package com.yffd.bcap.sso.middleware.cas.password;

import com.yffd.bcap.sso.support.pwd.SsoPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;

/**
 * 自定义密码验证
 * 配置：cas.authn.jdbc.query[0].passwordEncoder.type=com.yffd.bcap.sso.middleware.cas.password.CustomPasswordEncoder
 */
public class CustomPasswordEncoder extends AbstractPasswordEncoder {
    private final Logger logger = LoggerFactory.getLogger(CustomPasswordEncoder.class);

    @Override
    protected byte[] encode(CharSequence rawPassword, byte[] salt) {
        return SsoPasswordUtils.encodePwd(rawPassword, salt);
    }

}
