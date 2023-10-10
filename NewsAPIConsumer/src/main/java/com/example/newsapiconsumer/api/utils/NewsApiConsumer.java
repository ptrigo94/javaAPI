package com.example.newsapiconsumer.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class NewsApiConsumer {
    public static String consumeAPI (){

        String finalResponse = "";
        try{
            String apikey = "a9771cf92f3c596d9dcb1fcdb07589ee";
            String apiUrl = "https://gnews.io/api/v4/search?q=example&lang=en&country=us&max=10&apikey="+ apikey;
            URI uri = new URI(apiUrl);

            HttpURLConnection con =  (HttpURLConnection) uri.toURL().openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader (new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine())!= null){
                    content.append(inputLine);
                }
                in.close();
                finalResponse = content.toString();
            }
            else{
                System.out.println("Error :" + responseCode);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        return finalResponse;
    }

}
