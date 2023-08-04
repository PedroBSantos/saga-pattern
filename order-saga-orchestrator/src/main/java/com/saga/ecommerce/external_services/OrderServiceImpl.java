package com.saga.ecommerce.external_services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.saga.ecommerce.core.services.order.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private String serviceUrl;
    private Logger logger;

    public OrderServiceImpl(@Value("${ecommerce.services.urls.remove-order}") String serviceUrl) {
        this.serviceUrl = serviceUrl;
        this.logger = Logger.getLogger(OrderServiceImpl.class.getSimpleName());
    }

    @Override
    public boolean remove(UUID id) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var httpRequest = HttpRequest
                    .newBuilder(new URI(serviceUrl + "/" + id.toString()))
                    .DELETE()
                    .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            this.logger.info("Status code Response from " + this.serviceUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 200 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean processing(UUID id) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var httpRequest = HttpRequest
                    .newBuilder(new URI(serviceUrl + "/processing/" + id.toString()))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8))
                    .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            this.logger.info("Status code Response from " + this.serviceUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 200 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean cancel(UUID id) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var httpRequest = HttpRequest
                    .newBuilder(new URI(serviceUrl + "/cancel/" + id.toString()))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8))
                    .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            this.logger.info("Status code Response from " + this.serviceUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 200 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean finish(UUID id) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var httpRequest = HttpRequest
                    .newBuilder(new URI(serviceUrl + "/finish/" + id.toString()))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8))
                    .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            this.logger.info("Status code Response from " + this.serviceUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 200 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
