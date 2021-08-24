package simplerestserver;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleJavaWebServer {
    public static void main(String[] args) throws Exception{
        // simpleHttpServer();
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new MyHttpHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started on port 8001");
    }

    private static void simpleHttpServer() throws IOException {
        final ServerSocket server = new ServerSocket(8080);
        final Random randomGen = new Random();
        while (true) {
            try (Socket client = server.accept()) {
                //readClientInput(client);
                String message = "HTTP/1.1 200 OK\r\n\nbe happy " + randomGen.nextGaussian();
                client.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    private static void readClientInput(Socket client) throws IOException {
        InputStreamReader isr = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        String line = reader.readLine();
        while (!line.isEmpty()) {
            System.out.println(line);
            line = reader.readLine();
        }
    }
}
