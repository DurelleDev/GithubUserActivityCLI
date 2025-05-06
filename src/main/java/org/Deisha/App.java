package org.Deisha;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        /* Using a builder for more configuration (optional)
         HttpClient client = HttpClient.newBuilder()
                 .connectTimeout(Duration.ofSeconds(10))
                 .build();*/

        String username = "DurelleDev";

        //URI of the API endpoint
        String API_URL = String.format("https://api.github.com/users/%s/events", username);
        //Create a URI Object
        URI uri = URI.create(API_URL);

        //Build a GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()  // or .POST(HTTPRequest.BodyPublishers.ofString("your request body") for post requests)
                .header("Content-Type", "application/json")
                .build();
        /*
            Send the request and handle the response
            Send() - Synchronous (Blocking)
            sendAsync() - Asynchronous (Returns CompletableFuture(Holds the response)) (Non-blocking)
        */

        //HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("Status Code: " + response.statusCode());
                    System.out.println("Headers: " + response.headers());
                    System.out.println("Body: " + response.body());
                })
                .exceptionally(e -> { // Catch Exception
                    e.printStackTrace(); // Robust Logging
                    return null;
                });


        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree()

        try{
            /*
            "id",
            "type",
            "actor",
            "repo",
            "payload",
            "public",
            "created_at"
            */

        }
        catch(JsonProcessingException e){

        }
    Thread.sleep(5000);
    }

}

