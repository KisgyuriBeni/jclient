package models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Properties;

public class Client {
    String host;
    String endpoint;
    HttpClient client;
    Properties prop;
    public Client(){
        this.prop = new Prop().getProp();
        this.host= this.prop.getProperty("host");
        this.endpoint =this.prop.getProperty("endpoint");
        client = HttpClient.newHttpClient();
        String url = this.host + this.endpoint;
    
        System.out.println(get(url));
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
        .headers()
        .POST(null)
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }
}
