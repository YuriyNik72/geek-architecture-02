package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.interfaces.SocketService;

import java.util.function.Function;

public class StrategyMethodHandler extends MethodHandlerImpl {

    private final Function<HttpRequest, HttpResponse> strategy;

    public StrategyMethodHandler(Function<HttpRequest, HttpResponse> strategy, String method, MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer, ServerConfig config) {
        super(method, next, socketService, responseSerializer, config);
        this.strategy = strategy;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return strategy.apply(request);
    }
}
