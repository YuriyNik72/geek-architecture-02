package ru.geekbrains;

import ru.geekbrains.interfaces.Response;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private final Socket socket;

    private final String folder;

    public RequestHandler(Socket socket, String folder) {
        this.socket = socket;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (InputReader input = new InputReader(socket);
             OutputReader output = new OutputReader(socket)
        ) {
            Path path = Paths.get(folder, input.getRequestPath());
            if (!Files.exists(path)) {
                Response resp = new SimpleResponse(404, "NOT_FOUND");
                resp.appendTextToMessage("<h1>File not found</h1>");
                output.sendResponse(resp);
                return;
            }
            if (Files.isDirectory(path)) {
                Response resp = new SimpleResponse(500, "INTERNAL_SERVER_ERROR");
                resp.appendTextToMessage("<h1>This directory</h1>");
                output.sendResponse(resp);
                return;
            }
            Response resp = new SimpleResponse(200, "OK");
            output.writeResponse(resp);

            output.writeBufferedReader(Files.newBufferedReader(path));

            System.out.println("Client disconnected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
