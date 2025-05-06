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

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status Code: " + response.statusCode());
            //System.out.println("Headers: " + response.headers());
            //System.out.println("Body: " + response.body());
            if (response.statusCode() == 200){
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                try{
                    JsonNode events = mapper.readTree(responseBody);

                    if (!events.isArray() || events.isEmpty()){
                        System.out.println("No recent activity found.");
                        return;
                    }


                    for(JsonNode element: events){
                        String type = element.get("type").asText();
                        String repoName = element.get("repo").get("name").asText();

                        switch (type){
                            case "PushEvent":
                                System.out.println("Pushed to "+ repoName);
                                break;
                            case "WatchEvent":
                                System.out.println("Starred "+ repoName);
                                break;
                            case "CreateEvent":
                                System.out.println("Created "+ repoName);
                                break;
                            case "IssuesEvent":
                                System.out.println("Opened an issue in "+ repoName);
                                break;
                            case "DeleteEvent":
                                System.out.println("Deleted "+ repoName);
                                break;
                            default:
                                System.out.println("Performed " + type + " on " + repoName );
                                break;
                        }
                    }
                }
                catch (JsonProcessingException e){
                    System.err.println("Error parsing Json: " + e.getMessage());
                }
            }
            else System.out.println("Request failed with status code: "+response.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

