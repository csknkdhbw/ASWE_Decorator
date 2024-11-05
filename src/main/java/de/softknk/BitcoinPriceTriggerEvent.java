package de.softknk;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BitcoinPriceTriggerEvent extends Event {

    private static final String API_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    private int priceLimit;

    public BitcoinPriceTriggerEvent(int priceLimit) {
        // Price Limit cannot be below zero
        if (priceLimit < 0)
            priceLimit = 0;

        this.priceLimit = priceLimit;
    }

    @Override
    public boolean checkEventAndNotify() {
        int btcPrice = fetchBitcoinPrice();

        if (btcPrice >= priceLimit) {
            setMessage("BTC reached limit of " + priceLimit + "$!" + " Current BTC price: " + btcPrice);
            return true;
        }

        // No event occured because price limit is not reached
        return false;
    }

    private static int fetchBitcoinPrice() {
        try {
            // Build the GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful
            if (response.statusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = response.body();
                JsonNode root = mapper.readTree(jsonResponse);
                
                // Extract the USD price of Bitcoin
                return root.path("bitcoin").path("usd").asInt();                

            } else {
                System.out.println("Error: Received response code " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(int priceLimit) {
        this.priceLimit = priceLimit;
    }

}