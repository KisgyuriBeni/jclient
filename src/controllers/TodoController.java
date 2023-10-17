package controllers;

import java.util.Properties;
import models.Client;
import models.Prop;

public class TodoController {
    String host;
    String endpoint;
    Properties prop;
    Client client;

    public TodoController(){
    
        this.client = new Client();
        prop = new Prop().getProp();
        this.host= this.prop.getProperty("host");
        this.endpoint =this.prop.getProperty("endpoint");
    }

    public void index(){
        String url = this.host+this.endpoint;
        String res = client.Get(url);
        System.out.println(res);
    }
    public void create(){
        String url = this.host+this.endpoint;
        String body = "{ \"userId\": 1, "+
        " \"title\":\"Telefon eldobás\", "+ 
        " \"completed\":\"false\" " + 
        " }";
        String res = client.Post(url, body);
        System.out.println(res);
    }
    public void update(){}
    public void delete(){}
}