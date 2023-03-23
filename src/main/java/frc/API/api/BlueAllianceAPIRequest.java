package frc.API.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import frc.API.type.RequestFailedException;

public class BlueAllianceAPIRequest {

    private final HttpClient client;
    private final HttpRequest request;
    public String result;
    
    public BlueAllianceAPIRequest(HttpClient httpClient, IAPIKey key, String args) {

        this.client = httpClient;
        System.out.println(args);
        this.request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.thebluealliance.com/api/v3/" + args))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .header("X-TBA-Auth-Key", key.getKey())
            .build();

    }

    //get the body of the http response
    public String get() throws RequestFailedException {

        this.client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept((o) -> this.result=o)
            .join();

        return this.result;
    }

}
