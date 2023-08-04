package com.saga.ecommerce.infra.viacep;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ViaCEPRequest {
    
    private String requestUrl;

    public ViaCEPRequest(@Value("${viacep.request.url}") String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public ViaCEPResponseModel getAddressFrom(String cep) {
        try {
            var jsonResponse = this.doRequest(cep);
            return jsonResponse != null ? this.convertJsonResponseToViaCEPResponseModel(jsonResponse) : null;
        } catch (Exception exception) {
            throw new ViaCEPRequestException(exception.getMessage());
        }
    }

    private String doRequest(String cep) throws URISyntaxException, IOException, InterruptedException {
        var httpClient = HttpClient.newHttpClient();
        var httpRequest = HttpRequest.newBuilder(new URI(requestUrl + cep + "/json/")).GET().build();
        var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404)
            return null;
        if (response.statusCode() != 200)
            throw new RuntimeException("Error when trying GET cep " + cep + " on " + requestUrl);
        return response.body();
    }

    private ViaCEPResponseModel convertJsonResponseToViaCEPResponseModel(String jsonResponse) {
        var gson = new Gson();
        var address = gson.fromJson(jsonResponse, ViaCEPResponseModel.class);
        return address;
    }
}
