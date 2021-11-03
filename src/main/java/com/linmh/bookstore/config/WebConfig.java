package com.linmh.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;

@Configuration
@EnableConfigurationProperties(ResourceConfig.class)
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    ResourceConfig resourceConfig;

    /**
     * 解决前端跨域问题
     * @param registry
     */
   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(HttpMethod.POST.name(),
                        HttpMethod.GET.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.TRACE.name())
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }

    /**
     * 配置上传图片的路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(resourceConfig.getImageMapUrl())
                .addResourceLocations("file:"+ resourceConfig.getImageDir());

    }
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        //ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                HashSet<ErrorPage> errorPages = new HashSet<>();
                errorPages.add(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
                factory.setErrorPages(errorPages);

            }
        };
    }
}
