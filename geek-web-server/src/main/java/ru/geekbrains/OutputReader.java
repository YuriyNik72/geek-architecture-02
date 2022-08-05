package ru.geekbrains;

import ru.geekbrains.interfaces.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class OutputReader implements AutoCloseable {

    private final PrintWriter output;

    public OutputReader(Socket socket) throws IOException {
        this.output = new PrintWriter(socket.getOutputStream());
    }

    public void writeResponse(Response resp){
        output.print(resp);
    }

    public void sendResponse(Response resp){
        output.print(resp);
        output.flush();
    }

    public void writeBufferedReader(BufferedReader reader) throws IOException{
        reader.transferTo(output);
    }

    @Override
    public void close() throws Exception {
        output.close();
    }
}
