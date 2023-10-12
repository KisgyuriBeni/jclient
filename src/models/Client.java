package models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

public class Client { 

    HttpClient client;
    public Client(){
        client = HttpClient.newHttpClient();
    }

    public String get(String url){
        String response="";
        try{
            response = tryGet(url);
        }catch(IOException e){
            System.err.println("Hiba! A RestAPI lekérdezése sikertelen!");
            System.err.println(e.getMessage());
        }catch(InterruptedException e){
            System.err.println("Hiba! A lekérdezés megszakadt!");
            System.err.println(e.getMessage());
        }return response;

    }

    public String tryGet(String url) throws IOException, InterruptedException{
        HttpRequest request =  HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String post(String url, String body, String...token){
        String result = "";
        try {
            result = tryPost(url, body, token);
        }catch (InterruptedException e) {
            System.err.println("Hiba! Megszakítás történt!");
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println("Hiba! Átvitel nem lehetséges!");
            System.err.println(e.getMessage());
        }return result;        
    }

    public String tryPost(String url, String body, String...token) throws IOException, InterruptedException{
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Content-type");
        headers.add("Application/json");

        if(token.length > 0){
            headers.add("Authorization");
            headers.add("Bearer" + token);
        }

        HttpRequest request =  HttpRequest.newBuilder()
        .uri(URI.create(url))
        .headers(headers.toArray(String[]::new))
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }
}
