package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class InputReader implements AutoCloseable {

    private final BufferedReader input;

    public InputReader(Socket socket) throws IOException {
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    }

    public String getRequestPath() throws IOException{
        while (!input.ready());
        String firstLine = input.readLine();
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        while (input.ready()) {
            System.out.println(input.readLine());
        }
        return parts[1];
    }
    @Override
            public void close() throws Exception{
        input.close();
    }
}


