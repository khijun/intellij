package edu.du.sb1031.config;

import edu.du.sb1031.dto.AuthInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthCheckInterceptor())
//                .addPathPatterns("/edit/**")
//                .addPathPatterns("/members/**")
//                .addPathPatterns("/board/reply**");
//        registry.addInterceptor(new AdminCheckInterceptor())
//                .addPathPatterns("/admin/**")
//                .addPathPatterns("/items/itemForm");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("members/myItem");
    }
}
