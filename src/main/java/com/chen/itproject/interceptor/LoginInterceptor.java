package com.chen.itproject.interceptor;

import com.chen.itproject.pojo.Result;
import com.chen.itproject.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        String jwt = request.getHeader("token");

        if (!StringUtils.hasLength(jwt)) {
            log.info("not login");
            Result result = Result.errorResult("NOT_LOGIN");

            String JsonResult = objectMapper.writeValueAsString(result);
            response.getWriter().write(JsonResult);
            return false;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            log.info("illegal token");
            Result result = Result.errorResult("NOT_LOGIN");

            String JsonResult = objectMapper.writeValueAsString(result);
            response.getWriter().write(JsonResult);
            return false;
        }

        log.info("correct token");
        return true;
    }
}
