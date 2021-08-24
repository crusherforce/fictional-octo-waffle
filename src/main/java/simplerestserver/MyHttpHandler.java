package simplerestserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String reqParamVal = null;
        if ("GET".equals(exchange.getRequestMethod())) {
            reqParamVal = handleGetRequest(exchange);
        } else if ("POST".equals(exchange.getRequestMethod())) {
            reqParamVal = handlePostRequest(exchange);
        }
        try {
            handleResponse(exchange, reqParamVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(HttpExchange exchange, String reqParamVal) throws IOException, InterruptedException {
        OutputStream outputStream = exchange.getResponseBody();
        String htmlResponse = "" + Thread.currentThread();
        Thread.sleep(5000);
        exchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private String handlePostRequest(HttpExchange exchange) {
        return null;
    }

    private String handleGetRequest(HttpExchange exchange) {
        return exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }
}
