package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import org.example.interfaces.controller.IServerController;
import org.example.interfaces.service.IServerService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ServerController implements IServerController {
    private final IServerService service;

    public ServerController(IServerService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (!path.startsWith("/api")) {
            exchange.sendResponseHeaders(404, 0);
            exchange.close();
            return;
        }

        if (path.matches("/api/\\d+")) {
            int id = Integer.parseInt(path.substring(5));
            handleGetById(exchange, id);
            return;
        }

        switch (method) {
            case "GET" -> handleGet(exchange);
            case "POST" -> handlePost(exchange);
            case "DELETE" -> handleDelete(exchange);
            default -> {
                exchange.sendResponseHeaders(405, 0);
                exchange.close();
            }
        }
    }

    private void handleGetById(HttpExchange exchange, int id) throws IOException {
        try {
            String response = service.getAtIndex(id).toString();
            sendResponse(exchange, response, 200);
        } catch (IndexOutOfBoundsException e) {
            sendResponse(exchange, "Element not found", 404);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        try {
            String response = service.getLast().toString();
            sendResponse(exchange, response, 200);
        } catch (IllegalStateException e) {
            sendResponse(exchange, "Repository is empty", 404);
        }
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        try {
            service.add(Integer.parseInt(requestBody));
            sendResponse(exchange, "Added successfully", 201);
        } catch (IllegalArgumentException e) {
            sendResponse(exchange, e.getMessage(), 400);
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        if (!service.isEmpty()) {
            service.removeAtIndex(service.size() - 1);
            sendResponse(exchange, "Deleted successfully", 200);
        } else {
            sendResponse(exchange, "Repository is empty", 400);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}