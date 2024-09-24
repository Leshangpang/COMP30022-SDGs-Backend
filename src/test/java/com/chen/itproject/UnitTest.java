package com.chen.itproject;

import com.chen.itproject.interceptor.LoginInterceptor;
import com.chen.itproject.util.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@SpringBootTest
class UnitTest {

    @Test
    public void utilsTest() throws Exception {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "test");
        String jwt = JwtUtils.createJwt(claims);

        Claims claimsTest = JwtUtils.parseJwt(jwt);
        Integer idStored = claimsTest.get("id", Integer.class);
        String nameStored = claimsTest.get("name", String.class);

        assert idStored.equals(1);
        assert nameStored.equals("test");

        System.out.println("util works well");

    }

    @Test
    public void interceptorTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "test");
        String jwt = JwtUtils.createJwt(claims);
        request.addHeader("token", jwt);

        LoginInterceptor loginInterceptor = new LoginInterceptor();
        Boolean result = loginInterceptor.preHandle(request, new MockHttpServletResponse(), null);

        assert result;
        System.out.println("successfully check");

        MockHttpServletRequest request2 = new MockHttpServletRequest();
        request2.addHeader("token", "illegal token");

        Boolean result2 = loginInterceptor.preHandle(request2, new MockHttpServletResponse(), null);

        assert !result2;
        System.out.println("successfully intercept");
    }

}
