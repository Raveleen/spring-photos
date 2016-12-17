package com.raveleen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Святослав on 16.12.2016.
 */
@Configuration
@ComponentScan(value = "com.raveleen")
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("SpringJPA");
        return managerFactory.createEntityManager();
    }

    @Bean
    public PhotoDAO photoDAO() {
        return new PhotoDAO();
    }

    @Bean
    public Archive archive() {
        return new Archive();
    }

    @Bean
    public UrlBasedViewResolver setViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver setMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(65000);
        return commonsMultipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
