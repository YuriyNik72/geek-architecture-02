package ru.geekbrains.service.interfaces;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;


public interface SocketService extends Closeable {


     Deque<String> readRequest();

     void writeResponse(String rawResponse);

}
