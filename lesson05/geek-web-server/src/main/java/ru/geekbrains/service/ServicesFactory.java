package ru.geekbrains.service;

import ru.geekbrains.service.interfaces.FileService;
import ru.geekbrains.service.interfaces.SocketService;

import java.net.Socket;

public final class ServicesFactory {

    public static FileService getFileService(String rootDir){
        return new FileServiceImpl(rootDir);
    }

    public static SocketService getSocketService(Socket socket){
        return new SocketServiceImpl(socket);
    }
}
