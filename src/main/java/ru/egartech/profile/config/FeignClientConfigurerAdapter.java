package ru.egartech.profile.config;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class FeignClientConfigurerAdapter extends FeignClientsConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {};
    }

}
