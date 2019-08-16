package com.yffd.bcap.uamc.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 1.验证Springfox是否正常工作
 *  http://localhost:8080/v2/api-docs
 *  结果是带有大量键值对的JSON响应
 * 2.swagger ui地址
 *  http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yffd.bcap.uamc.ui"))
                .paths(PathSelectors.any()) // and by paths
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户授权管理中心")
                .description("接口测试")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html") // 将“url”换成自己的ip:port
                .version("v1.1.0")
                .build();
    }

}
