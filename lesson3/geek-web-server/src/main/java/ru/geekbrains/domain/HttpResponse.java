package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;

    private String statusText;

    private Map<String, String> headers = new HashMap<>();

    private String body;

    public HttpResponse() {
        this.headers = new HashMap<>();
    }
    public HttpResponse(int statusCode, String statusText, String body){
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.body = body;
        this.headers  = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");
    }
    public HttpResponse(int statusCode, String statusText, Map<String, String> headers, String body){
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.body = body;
        this.headers = headers;
    }
    private void addHeader(String name, String value){
        this.getHeaders().put(name, value);
    }

    public int getStatusCode() {
        return statusCode;
    }

    protected void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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

    public String getStatusText() {
        return statusText;
    }

    protected void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private final HttpResponse response;

        public Builder(){
            this.response = new HttpResponse();
        }
        public Builder setStatusCode(int code){
            this.response.setStatusCode(code);
            return this;
        }
        public Builder setStatusText(String text) {
            this.response.setStatusText(text);
            return this;
        }
        public Builder setHeaders(Map<String, String> headers){
            this.response.setHeaders(headers);
            return this;
        }
        public Builder setBody(String body){
            this.response.setBody(body);
            return this;
        }
        public Builder addHeader(String name, String value){
            this.response.addHeader(name, value);
            return this;
        }
        public HttpResponse build(){
            if(!this.response.getHeaders().containsKey("Content-Type")){
                this.response.addHeader("Content-Type", "text/html; charset=utf-8");
            }
            return this.response;
        }
    }

}
