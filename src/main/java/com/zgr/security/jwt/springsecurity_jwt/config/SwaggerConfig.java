package com.zgr.security.jwt.springsecurity_jwt.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/4/6 10:04
 * knife4j访问地址:   http://localhost:port/doc.html#/home
 */

@Configuration
@EnableKnife4j
public class SwaggerConfig {
    /**
     * 配置swagger的Docket的bean实例
     * @return
     */
    @Bean
    public Docket docket(){
           return new Docket(DocumentationType.OAS_30)
                   .apiInfo(apiInfo())
                   .select()
                   .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                   //paths过滤什么路径
                   .paths(PathSelectors.any())
                   .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger3")
                .description("springboot+swagger,jwt+springSecurity")
                .contact(new Contact("zgr","213","1176754512@qq.com"))
                .version("1.1")
                .build();
    }
}
