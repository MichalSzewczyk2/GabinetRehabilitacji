package http.web;

import com.sun.net.httpserver.HttpServer;
import http.handlers.*;
import http.service.*;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WebServerFactory {

    public static HttpServer createServer() {
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8081), 0);
            Executor executor = Executors.newFixedThreadPool(25);
            server.setExecutor(executor);
            UserService userService = new UserService();

            server.createContext("/users", new GlobalHttpHandler(new UserRestHandler(userService)));

            return server;
        } catch (IOException e) {
            throw new RuntimeException("Unable to start server", e);
        }
    }
}
