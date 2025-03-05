package org.equinox.interceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("preHandle"+ handlerMethod.getMethod().getName());
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        Random rand = new Random();
        int delay = rand.nextInt(500);
        Thread.sleep(delay);
        Map<String, String> json = new HashMap<>();
        json.put("error", "you don't have permission to access this resource");
        json.put("date", new Date().toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().print(jsonString);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("postHandle" + handlerMethod.getMethod().getName());
        long end = System.currentTimeMillis();
        long start =  (long) request.getAttribute("startTime");
        long duration = end - start;
        logger.info("time treasured: " + duration);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
