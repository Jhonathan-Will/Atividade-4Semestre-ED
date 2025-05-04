package com.faculdade.atividade.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.faculdade.atividade.middleware.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/exercice/sale/**")
                .addPathPatterns("/exercice/point/convert"); 
    }
}
