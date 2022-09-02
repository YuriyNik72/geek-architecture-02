package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {
        HttpRequest.Builder requestBuilder = HttpRequest.getBuilder();

        String[] parts = rawRequest.pollFirst().split("\\s+");

        requestBuilder.setMethod(parts[0]);
        requestBuilder.setPath(parts[1]);

        while (!rawRequest.isEmpty() && !rawRequest.peekFirst().isEmpty()) {
            String line = rawRequest.pollFirst();
            String headerName = line.split(": ")[0];
            String headerValue = line.split(": ")[1];

            requestBuilder.addHeader(headerName, headerValue);
        }

        rawRequest.pollFirst();
        if (!rawRequest.isEmpty()) {
            requestBuilder.setBody(String.join("", rawRequest));
        }
        return requestBuilder.build();
    }
}
