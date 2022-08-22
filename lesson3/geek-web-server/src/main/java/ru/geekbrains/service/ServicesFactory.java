package ru.geekbrains.service;

import ru.geekbrains.interfaces.FileService;
import ru.geekbrains.interfaces.SocketService;

import java.net.Socket;

public final class ServicesFactory {
    public static FileService getFileService(String rootDir){
        return new FileServiceImpl(rootDir);
    }
    public static SocketService getSocketService(Socket socket){
        return new SocketServiceImpl(socket);
    }
}
