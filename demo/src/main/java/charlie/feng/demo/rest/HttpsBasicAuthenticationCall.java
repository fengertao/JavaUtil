package charlie.feng.demo.rest;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

class HeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    String key;
    String value;

    public HeaderHttpRequestInterceptor(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().set(key, value);

        return execution.execute(requestWrapper, body);
    }
}
