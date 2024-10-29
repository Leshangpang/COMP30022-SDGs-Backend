package com.chen.itproject;

import com.chen.itproject.interceptor.LoginInterceptor;
import com.chen.itproject.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;

@SpringBootTest
class ErrorInterceptorTest {

    /**
     * Test for the global error interceptor's
     */
    @Test
    public void interceptorTest() throws Exception {
    }

}
