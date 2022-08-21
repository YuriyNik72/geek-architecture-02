package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializer {

    public String serialize(HttpResponse response) {
        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 ")
                .append(response.getStatusCode())
                .append(" ")
                .append(response.getStatusText())
                .append("\n");

        response.getHeaders().forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        sb.append("\n");

        sb.append(response.getBody());
        return sb.toString();
    }
}
