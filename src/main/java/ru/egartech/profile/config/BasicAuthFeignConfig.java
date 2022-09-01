package ru.egartech.profile.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicAuthFeignConfig extends FeignClientConfigurerAdapter {

    @Value("${openfeign.basic.token}")
    private String token;

    @Override
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", token);
    }

}
