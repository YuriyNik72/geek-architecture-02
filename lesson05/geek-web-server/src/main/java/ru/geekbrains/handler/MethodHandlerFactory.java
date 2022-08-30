package ru.geekbrains.handler;


import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;

import ru.geekbrains.service.interfaces.FileService;
import ru.geekbrains.service.interfaces.SocketService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, fileService);
    }

    public static MethodHandler createAnnotated(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Handler.class);

        if (classes.isEmpty()) {
            throw new RuntimeException("Нет ни одного класса с аннотацией @Handler или помеченные классы не реализуют интерфейс MethodHandler");
        }

        Set<Class<?>> handlers = new TreeSet<>((o1, o2) -> o2.getAnnotation(Handler.class).order() - o1.getAnnotation(Handler.class).order());
        handlers.addAll(classes);

        MethodHandler prevHandler = null;
        for (Class<?> handler : handlers) {

            if (handler.getAnnotation(Handler.class).method().equals("GET")) {
                prevHandler = (MethodHandler) handler.getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class, FileService.class)
                        .newInstance(prevHandler, socketService, responseSerializer, fileService);
            } else {
                prevHandler = (MethodHandler) handler.getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class)
                        .newInstance(prevHandler, socketService, responseSerializer);
            }
        }

        return prevHandler;
        //return create(socketService, responseSerializer, fileService);
    }
}
