package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.interfaces.SocketService;

@Handler(method = "POST", order = 1)
class PostMethodHandler extends MethodHandlerImpl {

    public PostMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer) {
        super("POST", next, socketService, responseSerializer, next.config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.getBuilder()
                .setStatusCode(200)
                .setStatusText("Ok")
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setBody("<h1>POST method handled</h1>")
                .build();
    }
}
