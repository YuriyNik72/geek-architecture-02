package ru.geekbrains.service.interfaces;

import java.io.*;
import java.util.Deque;



public interface SocketService extends Closeable {


    Deque<String> readRequest();

     void writeResponse(String rawResponse);

}
