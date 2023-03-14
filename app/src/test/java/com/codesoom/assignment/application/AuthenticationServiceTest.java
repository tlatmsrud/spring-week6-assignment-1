package com.codesoom.assignment.application;

import com.codesoom.assignment.errors.InvalidTokenException;
import com.codesoom.assignment.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthenticationServiceTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9." +
            "ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private static final String INVALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9." +
            "ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaD0";
    private static final String SECRET = "12345678901234567890123456789012";
    private AuthenticationService authenticationService;
    @BeforeEach
    void setUp(){
        JwtUtil jwtUtil = new JwtUtil(SECRET);
        authenticationService = new AuthenticationService(jwtUtil);
    }
    @Test
    void loginSuccess(){
        //TODO. 로그인 구현 -ID/PW 검증, 성공 시 토큰 발급
        String accessToken = authenticationService.login();

        assertThat(accessToken).isEqualTo(VALID_TOKEN);
    }

    @Test
    void loginFail(){
        //TODO. 로그인 구현 -ID/PW 검증, 실패 시 로그인 실패 예외 처리
        String accessToken = authenticationService.login();

        assertThat(accessToken).isEqualTo(VALID_TOKEN);
    }

    @Test
    void parseTokenWithValidToken(){
        Long userId = authenticationService.parseToken(VALID_TOKEN);

        assertThat(userId).isEqualTo(1L);
    }

    @Test
    void parseTokenWithInvalidToken(){
        assertThatThrownBy(() -> authenticationService.parseToken(INVALID_TOKEN))
                .isInstanceOf(InvalidTokenException.class);
    }

    @Test
    void parseTokenWithBlankToken(){
        assertThatThrownBy(() -> authenticationService.parseToken(""))
                .isInstanceOf(InvalidTokenException.class);
    }

}