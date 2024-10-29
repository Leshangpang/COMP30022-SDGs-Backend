package com.chen.itproject;

import com.chen.itproject.interceptor.LoginInterceptor;
import com.chen.itproject.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;

@SpringBootTest
class UtilsTest {

    /**
     * Test for the utility methods in JwtUtils
     * This test verifies the creation and parsing of a JWT token
     */
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

}
