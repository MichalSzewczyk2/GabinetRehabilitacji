package http;

import clinic.Clinic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gson.GsonConverter;
import users.User;

import java.io.IOException;
import java.lang.reflect.Type;

import java.time.Duration;
import java.util.ArrayList;


public class HttpClient {
    private final String serverUrl;
    private final java.net.http.HttpClient httpClient;
    private final Gson g = GsonConverter.newGsonRWConverter();

    public HttpClient(String serverUrl) {
        this.serverUrl = serverUrl;
        this.httpClient = java.net.http.HttpClient.newHttpClient();
    }

    public java.net.http.HttpClient getHttpClient() {
        return httpClient;
    }

    public ArrayList<Clinic> getClinics() throws IOException, InterruptedException {
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(serverUrl + "/clinics"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        java.net.http.HttpResponse<String> response = this.getHttpClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

        String res = response.body();
        Type type = new TypeToken<ArrayList<Clinic>>() {
        }.getType();
        ArrayList<Clinic> clinics = g.fromJson(res, type);
        return clinics;
    }

    public ArrayList<User> getUsers() throws IOException, InterruptedException {
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(serverUrl + "/users"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        java.net.http.HttpResponse<String> response = this.getHttpClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

        String res = response.body();
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> users = g.fromJson(res, type);
        return users;
    }

}
