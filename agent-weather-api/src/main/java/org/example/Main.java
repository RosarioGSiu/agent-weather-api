package org.example;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        //Perform an HTTP REQUEST
        URL url = new URL("https://www.youtube.com/");
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        //Read results
        int responseCode= connection.getResponseCode(); //Example (200=success, 404=not found)
        if(responseCode==200){
            //YEAH!
            System.out.println("Success! The http request returned status code: "+responseCode);
            String response= extractContentFromHttpResponse(connection);
            System.out.println("Api response:");
            System.out.println(response);
        }else{
            System.out.println("Oh no! The http request failed, status code: "+responseCode);
        }

        //Close connection
        connection.disconnect();
    }

    public static String extractContentFromHttpResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content= new StringBuilder();

        //Leggo caratteri finchè non finisco
        while((inputLine = in.readLine()) != null){
            System.out.println("Received chunk"+inputLine);
            content.append(inputLine);
        }

        in.close();
        return content.toString();
    }

}