package com.chen.itproject;

import com.chen.itproject.interceptor.LoginInterceptor;
import com.chen.itproject.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;

@SpringBootTest
class RequestInterceptorTest {

    /**
     * Test for the LoginInterceptor's preHandle method
     * This test verifies that a valid JWT token allows the request and an invalid token is intercepted
     */
    @Test
    public void interceptorTest() throws Exception {
        // Create a mock HTTP request and set a valid JWT token in the header
        MockHttpServletRequest request = new MockHttpServletRequest();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "test");
        String jwt = JwtUtils.createJwt(claims);
        request.addHeader("token", jwt);

        // Instantiate the LoginInterceptor and test the preHandle method with the valid token
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        Boolean result = loginInterceptor.preHandle(request, new MockHttpServletResponse(), null);

        // Assert that the request is allowed with a valid token
        assert result;
        System.out.println("successfully check");

        // Create another mock request with an invalid JWT token
        MockHttpServletRequest request2 = new MockHttpServletRequest();
        request2.addHeader("token", "illegal token");

        // Test the preHandle method with the invalid token
        Boolean result2 = loginInterceptor.preHandle(request2, new MockHttpServletResponse(), null);

        // Assert that the request is blocked with an invalid token
        assert !result2;
        System.out.println("successfully intercept");
    }

}
