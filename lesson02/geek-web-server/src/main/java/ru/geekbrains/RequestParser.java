package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {
        // TODO
        HttpRequest req = new HttpRequest();

        String[] parts = rawRequest.pollFirst().split("\\s+");

        req.setMethod(parts[0]);
        req.setPath(parts[1]);

        Map<String, String> headers = new HashMap<>();

        while (!rawRequest.isEmpty() && !rawRequest.peekFirst().isEmpty()) {
            String line = rawRequest.pollFirst();
            String headerName = line.substring(0, line.indexOf(":"));
            String headerValue = line.substring(line.indexOf(":") + 2);

            headers.put(headerName, headerValue);
        }
        req.setHeaders(headers);

        rawRequest.pollFirst();
        if (!rawRequest.isEmpty()) {
            req.setBody(String.join("", rawRequest));
        }
        return req;
    }
}
