package ru.egartech.profile.config.client.feign;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.AbstractFormWriter;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicAuthFeignConfig extends FeignClientConfigurerAdapter {

    @Value("${openfeign.basic.token}")
    private String token;

    @Override
    public Encoder feignEncoder(ObjectProvider<AbstractFormWriter> formWriterProvider, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return (object, bodyType, template) -> {};
    }

    @Override
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return (response, type) -> response;
    }

    @Override
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", token);
        };
    }

}
