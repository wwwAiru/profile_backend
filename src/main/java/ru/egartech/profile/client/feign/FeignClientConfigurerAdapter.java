package ru.egartech.profile.client.feign;

import feign.Request;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class FeignClientConfigurerAdapter extends FeignClientsConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {};
    }

    @Bean
    public Encoder encoder() {
        return (object, bodyType, template) -> {};
    }

}
