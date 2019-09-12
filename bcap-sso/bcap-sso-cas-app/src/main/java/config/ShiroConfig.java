package config;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig extends AbstractShiroWebFilterConfiguration {

    /**
     * 对应于web.xml中配置的那个shiroFilter {@link org.springframework.web.filter.DelegatingFilterProxy}
     * chiro注入的入口
     * @param securityManager
     * @return
     */
    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //把subject对象设为subjectFactory
        ((DefaultSecurityManager) securityManager).setSubjectFactory(subjectFactory());
        ShiroFilterFactoryBean filterFactoryBean = super.shiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap();
        filterFactoryBean.setFilters(filters());
        return filterFactoryBean;
    }

    /**
     * 由于cas代理了用户，所以必须通过cas进行创建对象
     * @return
     */
    @Bean
    protected SubjectFactory subjectFactory() {
        return new Pac4jSubjectFactory();
    }

    /**
     * 路径过滤设置
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/callback", "callbackFilter");
        definition.addPathDefinition("/logout", "logoutFilter");
        definition.addPathDefinition("/**", "casSecurityFilter");
        return definition;
    }

    /**
     * 对shiro的过滤策略进行明确
     * @return
     */
    @Bean
    protected Map<String, Filter> filters() {
        Map<String, Filter> filters = new HashMap<>();
        //安全过滤器，用于拦截受保护的url
        filters.put("casSecurityFilter", casSecurityFilter());

        //回调过滤器，用于完成ticket认证
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(authConfig());
        callbackFilter.setDefaultUrl(casClientCallbackUrl);
        filters.put("callbackFilter", callbackFilter);

        //登出过滤器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(authConfig());
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(false);
        filters.put("logoutFilter", logoutFilter);
        return filters;
    }

    /*************************** pac4j config begin ***************************************/
    @Value("#{ @environment['sso.cas.server.prefixUrl'] ?: null }")
    private String casServerPrefixUrl;
    @Value("#{ @environment['sso.cas.server.loginUrl'] ?: null }")
    private String casServerLoginUrl;
    @Value("#{ @environment['sso.cas.client.callbackUrl'] ?: null }")
    private String casClientCallbackUrl;

    /**
     * CAS配置：服务端的登录地址、服务端的地址前缀、协议等
     * @return
     */
    @Bean
    public CasConfiguration casConfiguration() {
        CasConfiguration casConfiguration = new CasConfiguration(casServerLoginUrl, casServerPrefixUrl);
        casConfiguration.setProtocol(CasProtocol.CAS30);
        return casConfiguration;
    }


    /**
     * 具有客户端、授权者和匹配者的默认配置
     * @return
     */
    @Bean
    protected Config authConfig() {
        Config config = new Config(casClient());
        return config;
    }

    /**
     * cas客户端配置
     * @return
     */
    @Bean
    public CasClient casClient() {
        CasClient casClient = new CasClient();
        casClient.setConfiguration(casConfiguration());
        casClient.setCallbackUrl(casClientCallbackUrl);//客户端回调地址
        casClient.setIncludeClientNameInCallbackUrl(false);
        return casClient;
    }

    /**
     * 用于拦截受保护的url
     * cas核心过滤器，把支持的client写上，filter过滤时才会处理，clients必须在authConfig.clients已经注册
     * @return
     */
    @Bean
    public Filter casSecurityFilter() {
        SecurityFilter filter = new SecurityFilter();
        filter.setConfig(authConfig());
        filter.setClients("CasClient,rest,jwt");
        return filter;
    }

/*************************** pac4j config end ***************************************/


//jwt秘钥
@Value("${jwt.salt}")
private String salt;
//    @Bean
//    public Realm pac4jRealm() {
//        return new Pac4jRealm();
//    }
//
//
//
//    /**
//     * JWT Token 生成器，对CommonProfile生成然后每次携带token访问
//     * @return
//     */
//    @Bean
//    protected JwtGenerator jwtGenerator() {
//        return new JwtGenerator(new SecretSignatureConfiguration(salt), new SecretEncryptionConfiguration(salt));
//    }
//
//
//    /**
//     * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
//     * @return
//     */
//    @Bean
//    protected CasRestFormClient casRestFormClient() {
//        CasRestFormClient casRestFormClient = new CasRestFormClient();
//        casRestFormClient.setConfiguration(casConfiguration());
//        casRestFormClient.setName("rest");
//        return casRestFormClient;
//    }
//
//
//    @Bean
//    protected Clients clients() {
//        //可以设置默认client
//        Clients clients = new Clients();
//
//        //token校验器，可以用HeaderClient更安全
//        ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator());
//        parameterClient.setSupportGetRequest(true);
//        parameterClient.setName("jwt");
//        //支持的client全部设置进去
//        clients.setClients(casClient(), casRestFormClient(), parameterClient);
//        return clients;
//    }
//
//    /**
//     * JWT校验器，也就是目前设置的ParameterClient进行的校验器，是rest/或者前后端分离的核心校验器
//     * @return
//     */
//    @Bean
//    protected JwtAuthenticator jwtAuthenticator() {
//        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
//        jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration(salt));
//        jwtAuthenticator.addEncryptionConfiguration(new SecretEncryptionConfiguration(salt));
//        return jwtAuthenticator;
//    }
//

//
//
//
//
//
//

//
//


//
//


}
