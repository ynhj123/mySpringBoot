package com.ynhj.nativemysql.configure;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: SwaggerConfigure
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@EnableSwagger2
public class SwaggerConfigure {
    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo("navite-mysql"))
                //分组名称  想要网关被记录到swagger就不要开分组
//                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.ynhj.nativemysql.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(String name) {
        return new ApiInfoBuilder()
                .title("后端接口文档-" + name)
                .description("<div style='font-size:14px;color:red;'>description</div>")
                .termsOfServiceUrl("N")
                .contact(new Contact("ynhj", "https://github.com/ynhj123/mySpringBoot", "yangniuhaojiangyyds@gmail.com"))
                .version("1.0")
                .build();
    }


}
