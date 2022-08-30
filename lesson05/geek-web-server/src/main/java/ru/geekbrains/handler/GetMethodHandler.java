package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.interfaces.FileService;
import ru.geekbrains.service.interfaces.SocketService;

@Handler(method = "GET", order = 0)
class GetMethodHandler extends MethodHandlerImpl {

    private final FileService fileService;

    public GetMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        super("GET", next, socketService, responseSerializer, config);
        this.fileService = fileService;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest req) {
        if (!fileService.exists(req.getPath())) {
            return HttpResponse.getBuilder()
                    .setStatusCode(404)
                    .setStatusText("NOT_FOUND")
                    .addHeader("Content-Type", "text/html; charset=utf-8")
                    .build();
        }
        if (fileService.isDirectory(req.getPath())){
            return HttpResponse.getBuilder()
                    .setStatusCode(400)
                    .setStatusText("BAD_REQUEST")
                    .addHeader("Content-Type", "text/html; charset=utf-8")
                    .setBody("<h1>Запрошена папка!</h1>")
                    .build();
        }

        return HttpResponse.getBuilder()
                .setStatusCode(200)
                .setStatusText("OK")
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .setBody(fileService.readFile(req.getPath()))
                .build();
    }
}
