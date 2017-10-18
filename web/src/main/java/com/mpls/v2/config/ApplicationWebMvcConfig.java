package com.mpls.v2.config;


import com.mpls.v2.ServiceApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by danul on 27.04.2017.
 */


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.mpls.v2"})
@Import({ServiceApplication.class})
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {


    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = "sample";
    private static final String description = "Documentation for the project";
    String rootPath = System.getProperty("catalina.home");
    String[] PATH = {
            "file:/" + rootPath + "/public/front/dist",
            "file:/" + rootPath + "/resources/"

    };

//    @Bean
//    public ClassLoaderTemplateResolver templateResolver() {
//
//        final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//        resolver.setPrefix("/email/");
//        resolver.setTemplateMode("HTML5");
//        resolver.setSuffix(".html");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setOrder(0);
//        return resolver;
//    }

//    @Bean
//    public TemplateEngine templateEngine() {
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.addTemplateResolver(templateResolver());
//        return templateEngine;
//    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(PATH);
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:/" + rootPath + "/resources/");
    }
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver createMultipartResolver() {
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        resolver.setMaxUploadSizePerFile(2000000);
//        resolver.setMaxUploadSize(20000000);
//        return resolver;
//
//    }



//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/*").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
//                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
//                        "Access-Control-Request-Headers")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .allowCredentials(true).maxAge(3600);
//    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .build();
    }
    @Bean
    public Docket decksApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.any())
                .build();
    }


}
