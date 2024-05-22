package com.todoService.todoService.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private final String jwtToken;
    public static final String REQUEST_HEADER_NAME = "Authorization";

    public RestTemplateInterceptor(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        String token = RequestContext.getContext().getToken();
//        request.getHeaders().add(RequestContext.REQUEST_HEADER_NAME,token);
        HttpHeaders headers = request.getHeaders();
        headers.add(REQUEST_HEADER_NAME,"Bearer "+jwtToken);
        return execution.execute(request, body);
    }
}
