package com.adidas.oracle.service;

import com.adidas.oracle.exception.UserNotAuthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Test(expected = UserNotAuthorizedException.class)
    public void test_authShouldThrowUserNotAuthorizedException() throws UserNotAuthorizedException {
        authService.auth("test");
    }

}
