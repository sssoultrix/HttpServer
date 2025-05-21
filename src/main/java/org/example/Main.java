package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.controller.ServerController;
import org.example.repository.ServerRepository;
import org.example.service.ServerService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        ServerRepository<Number> repository = new ServerRepository();
        ServerService<Number> service = new ServerService(repository);
        ServerController controller = new ServerController(service);
        server.setExecutor(null);
        server.start();
    }
}