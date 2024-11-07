package edu.du.sb1030.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("!!Log: LoggerInterceptor.preHandle()");
        log.info("!!Log: Request URI \t: {}", request.getRequestURI());
//        response.sendRedirect("/view/main");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("!!Log: LoggerInterceptor.postHandle()");
        super.postHandle(request, response, handler, modelAndView);
    }
}
