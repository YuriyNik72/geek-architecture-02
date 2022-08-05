package ru.geekbrains;

import ru.geekbrains.interfaces.Response;

public class SimpleResponse implements Response {

    private final StringBuilder result;


    public SimpleResponse(int status, String statusText) {
        this.result = new StringBuilder()
                .append("HTTP/1.1 ")
                .append(status)
                .append(" ")
                .append(statusText)
                .append("\n Content-Type: text/html; charsets=utf_8 \n\n");
    }
    public void appendTextToMessage (String text){
        result.append(text);
    }

    @Override
    public String toString(){
        return result.toString();
    }
}
