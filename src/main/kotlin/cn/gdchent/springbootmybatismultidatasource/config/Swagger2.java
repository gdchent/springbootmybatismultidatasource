package cn.gdchent.springbootmybatismultidatasource.config;

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
 * @author: gdchent
 * @date: 2019/10/17
 * @description:生成API文档配置
 */
@Configuration
@EnableSwagger2 //在启动类中添加配置
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiIinfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.gdchent.springsell"))
                .paths(PathSelectors.regex("/rest/.*"))
                //.paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiIinfo() {
        return new ApiInfoBuilder()
                .title("spring boot利用swagger构建api文档")
                .description("简单优雅restful风格")
                .termsOfServiceUrl("http://www.gdchent.cn")
                .version("1.0")
                .build();
    }

}
