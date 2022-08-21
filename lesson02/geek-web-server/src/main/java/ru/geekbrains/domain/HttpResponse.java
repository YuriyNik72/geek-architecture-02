package ru.geekbrains.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;

    private Map<String, String> headers;

    private String statusText;

    private String body;

    public HttpResponse() {
    }

    public HttpResponse(int statusCode, String statusText, String body) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.body = body;
        this.headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");
    }

    public HttpResponse(int statusCode, String statusText, Map<String, String> headers, String body) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.headers = headers;
        this.body = body;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
