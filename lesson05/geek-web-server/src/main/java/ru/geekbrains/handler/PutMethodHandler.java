package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.interfaces.SocketService;

@Handler(method = "PUT", order = 2)
class PutMethodHandler extends MethodHandlerImpl {

    public PutMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        super("PUT", next, socketService, responseSerializer, next.config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.getBuilder()
                .setStatusCode(200)
                .setStatusText("Ok")
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setBody("<h1>PUT method handled</h1>")
                .build();
    }
}
