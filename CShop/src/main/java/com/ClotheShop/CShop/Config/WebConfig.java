package com.ClotheShop.CShop.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.100.6:80", "http://192.168.100.6:8080", "http://localhost", "http://zlatapsu.zapto.org")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(4);
//    }

}
