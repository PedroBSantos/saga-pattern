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
import com.saga.ecommerce.core.services.delivery.DeliveryRegistrationService;
import com.saga.ecommerce.core.services.delivery.models.DeliveryRegistration;

@Service
public class DeliveryRegistrationServiceImpl implements DeliveryRegistrationService {

    private Logger logger;
    private String deliveryServiceUrl;
    private Gson jsonSerializer;

    public DeliveryRegistrationServiceImpl(@Value("${ecommerce.services.urls.delivery}") String deliveryServiceUrl) {
        this.deliveryServiceUrl = deliveryServiceUrl;
        this.jsonSerializer = new Gson();
        this.logger = Logger.getLogger(DeliveryRegistrationServiceImpl.class.getSimpleName());
    }

    @Override
    public boolean registerPendingDelivery(DeliveryRegistration registration) {
        try {
            var httpClient = HttpClient.newHttpClient();
            var requestBody = this.jsonSerializer.toJson(registration);
            var httpRequest = HttpRequest
                .newBuilder(new URI(this.deliveryServiceUrl))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();
            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info("Status code Response from " + this.deliveryServiceUrl + ": " + httpResponse.statusCode());
            return httpResponse.statusCode() == 204 ? true : false;
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            this.logger.severe("Exception when trying POST on " + this.deliveryServiceUrl);
            exception.printStackTrace();
        }
        return false;
    }
}
