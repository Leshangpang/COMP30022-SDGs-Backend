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

    /**
     * This method intercepts incoming HTTP requests before they reach the controller.
     * It checks if a valid JWT token is present in the request header. If valid token
     * exists, allowed. If not, reject the request and remind the front-end redirect
     * the website to the login page and let the user login.
     *
     * @param request The incoming HTTP request
     * @param response The HTTP response
     * @param handler The handler for the request
     * @return true if the request should proceed to the controller, false otherwise
     * @throws Exception if an error occurs during processing
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Retrieve the requested URL and the JWT token from the request header
        String url = request.getRequestURL().toString();
        String jwt = request.getHeader("token");

        // Check if the token is missing or empty
        if (!StringUtils.hasLength(jwt)) {
            log.info("not login");
            Result result = Result.errorResult("NOT_LOGIN");

            // Convert the result to a JSON string and write it to the response
            String JsonResult = objectMapper.writeValueAsString(result);
            response.getWriter().write(JsonResult);
            return false;
        }

        try {
            // Attempt to parse the JWT token to ensure it's valid
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
