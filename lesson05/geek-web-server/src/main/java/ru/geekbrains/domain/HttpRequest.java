package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    public HttpRequest() {
        this.headers = new HashMap<>();
    }

    public HttpRequest(String method, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    protected void addHeader(String name, String value) {
        this.getHeaders().put(name, value);
    }

    public String getMethod() {
        return method;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    protected void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    protected void setBody(String body) {
        this.body = body;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final HttpRequest request;

        public Builder() {
            this.request = new HttpRequest();
        }

        public Builder setMethod(String method) {
            this.request.setMethod(method);
            return this;
        }

        public Builder setPath(String path) {
            this.request.setPath(path);
            return this;
        }

        public Builder setHeaders(Map<String, String> map) {
            this.request.setHeaders(map);
            return this;
        }

        public Builder addHeader(String name, String value) {
            this.request.addHeader(name, value);
            return this;
        }

        public Builder setBody(String body) {
            this.request.setBody(body);
            return this;
        }

        public HttpRequest build() {
            return this.request;
        }
    }
}
