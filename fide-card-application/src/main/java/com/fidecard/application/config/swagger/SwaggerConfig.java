package com.fidecard.application.config.swagger;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.fidecard"))
//                .build().securitySchemes(securitySchemes());
    
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(basePackage("com.fidecard"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/").setViewName("forward:" + "/swagger-ui/index.html");
    }

//    private List<SecurityScheme> securitySchemes() {
//        return Arrays.asList(
//                new ApiKey(AuthenticationType.API.getName(), AuthenticationType.API.getHeader(), "header"),
//                new ApiKey(AuthenticationType.IAPI.getName(), AuthenticationType.IAPI.getHeader(), "header")
//        );
//    }
}
