package ru.egartech.profile.client.resttemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor, RequestInterceptor {

    private final String headerName = "Authorization";

    @Value("${openfeign.basic.token}")
    private String headerValue;

    @Override
    public void apply(RequestTemplate template) {
        template.header(headerName, headerValue);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(headerName, headerValue);
        return execution.execute(request, body);
    }

}
