package com.njust.config;


import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createDocket(){

        /**
         * 这是为了在用 swagger 测试接口的时候添加头部信息
         */
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder accessTokenBuilder = new ParameterBuilder();
        ParameterBuilder refreshTokenBuilder = new ParameterBuilder();
        accessTokenBuilder.name("authorization").description("自测的时候动态传输AccessToken入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);
        refreshTokenBuilder.name("refreshToken").description("自测的时候动态传输RefreshToken入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);
        params.add(accessTokenBuilder.build());
        params.add(refreshTokenBuilder.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.njust.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(params)
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("景色分明的后台权限管理系统")
                .description("景色分明的后台权限管理系统后端接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
