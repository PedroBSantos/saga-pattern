package com.saga.ecommerce.external_services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.saga.ecommerce.core.services.stock.StockProductService;
import com.saga.ecommerce.core.services.stock.models.*;

@Service
public class StockProductServiceImpl implements StockProductService {

    private Logger logger;
    private String increaseProductStockUrl;
    private String decreaseProductStockUrl;
    private Gson jsonSerializer;

    public StockProductServiceImpl(
        @Value("${ecommerce.services.urls.stock-increase}") String increaseProductStockUrl,
        @Value("${ecommerce.services.urls.stock-decrease}") String decreaseProductStockUrl) {
        this.jsonSerializer = new Gson();
        this.increaseProductStockUrl = increaseProductStockUrl;
        this.decreaseProductStockUrl = decreaseProductStockUrl;
        this.logger = Logger.getLogger(StockProductServiceImpl.class.getSimpleName());
    }

    @Override
    public boolean increaseStockAmount(IncreaseProductStock params) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var requestBody = this.jsonSerializer.toJson(params);
            var httpRequest = HttpRequest
                .newBuilder(new URI(this.increaseProductStockUrl))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info("Status code Response from " + this.increaseProductStockUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 200 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            this.logger.severe("Exception when trying PUT on " + this.increaseProductStockUrl);
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean decreaseStockAmount(DecreaseProductStock params) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var requestBody = this.jsonSerializer.toJson(params);
            var httpRequest = HttpRequest
                .newBuilder(new URI(this.decreaseProductStockUrl))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info("Status code Response from " + this.decreaseProductStockUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 204 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            this.logger.severe("Exception when trying PUT on " + this.decreaseProductStockUrl);
            exception.printStackTrace();
        }
        return false;
    }
}
