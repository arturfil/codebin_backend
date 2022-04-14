package com.arturofilio.codebin.security;

import com.arturofilio.codebin.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_DATE = 1000 * 60 * 60 * 24 * 10;// 10 dias
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
