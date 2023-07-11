package org.valyuta.valyutaBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.valyuta.valutes.Currency;
import org.valyuta.valutes.CurrencyDeserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCurrenciesAndFlags {
    public List<Currency> mainCurrenciesList(){
        List<Currency> currencies = allCurrencies();

        List<Currency> mainCurrencies = new ArrayList<>();
        for (Currency currency : currencies) {
            System.out.println(currency.getCcy());
            if (currency.getCcy().equals("\"USD\"") || currency.getCcy().equals("\"EUR\"")
                    || currency.getCcy().equals("\"RUB\"") || currency.getCcy().equals("\"GBP\"")
                    || currency.getCcy().equals("\"JPY\"") || currency.getCcy().equals("\"AFN\"") ||
                    currency.getCcy().equals("\"TJS\"") || currency.getCcy().equals("\"TMT\"") ||
                    currency.getCcy().equals("\"TRY\"") || currency.getCcy().equals("\"KGS\"") ||
                    currency.getCcy().equals("\"KRW\"") || currency.getCcy().equals("\"KZT\"")) {
                mainCurrencies.add(currency);
            }
        }
        System.out.println(mainCurrencies);
        return mainCurrencies;
    }
    public List<Currency> allCurrencies(){

        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://cbu.uz/uzc/arkhiv-kursov-valyut/json/"))
                    .GET()
                    .build();
            HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addDeserializer(Currency.class,new CurrencyDeserializer());
            objectMapper.registerModule(simpleModule);
          Currency[] currencies =  objectMapper.readValue(send.body(), Currency[].class);
            ArrayList<Currency> list = new ArrayList<>(Arrays.asList(currencies));
            System.out.println(list);
          return list;
        } catch (URISyntaxException | IOException | InterruptedException e) {
             e.printStackTrace();
        }
        return new ArrayList<>();
    }
    private List<Currency> dateAllCurrencies(String date){
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://cbu.uz/uzc/arkhiv-kursov-valyut/json/all/" + date +"/"))
                    .GET()
                    .build();
            HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addDeserializer(Currency.class,new CurrencyDeserializer());
            objectMapper.registerModule(simpleModule);
            Currency[] currencies =  objectMapper.readValue(send.body(), Currency[].class);
            ArrayList<Currency> list = new ArrayList<>(Arrays.asList(currencies));
            System.out.println(list);
            return list;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<String> mainFlags(){
        return List.of("\uD83C\uDDFA\uD83C\uDDF8", "\uD83C\uDDEA\uD83C\uDDFA", "\uD83C\uDDF7\uD83C\uDDFA",
                "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F", "\uD83C\uDDEF\uD83C\uDDF5",
                "\uD83C\uDDE6\uD83C\uDDEB", "\uD83C\uDDF0\uD83C\uDDEC", "\uD83C\uDDF0\uD83C\uDDF7", "\uD83C\uDDF0\uD83C\uDDFF",
                "\uD83C\uDDF9\uD83C\uDDEF", "\uD83C\uDDF9\uD83C\uDDF2", "\uD83C\uDDF9\uD83C\uDDF7");
    }
    public List<String> allFlags(){
        return  List.of("\uD83C\uDDFA\uD83C\uDDF8", "\uD83C\uDDEA\uD83C\uDDFA", "\uD83C\uDDF7\uD83C\uDDFA", "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F",
                "\uD83C\uDDEF\uD83C\uDDF5", "\uD83C\uDDE6\uD83C\uDDFF", "\uD83C\uDDE7\uD83C\uDDE9", "\uD83C\uDDE7\uD83C\uDDEC", "\uD83C\uDDE7\uD83C\uDDED",
                "\uD83C\uDDE7\uD83C\uDDF3", "\uD83C\uDDE7\uD83C\uDDF7", "\uD83C\uDDE7\uD83C\uDDFE", "\uD83C\uDDE8\uD83C\uDDE6", "\uD83C\uDDE8\uD83C\uDDED",
                "\uD83C\uDDE8\uD83C\uDDF3", "\uD83C\uDDE8\uD83C\uDDFA", "\uD83C\uDDE8\uD83C\uDDFF", "\uD83C\uDDE9\uD83C\uDDF0", "\uD83C\uDDE9\uD83C\uDDFF",
                "\uD83C\uDDEA\uD83C\uDDEC", "\uD83C\uDDE6\uD83C\uDDEB", "\uD83C\uDDE6\uD83C\uDDF7", "\uD83C\uDDEC\uD83C\uDDEA", "\uD83C\uDDED\uD83C\uDDF0",
                "\uD83C\uDDED\uD83C\uDDFA", "\uD83C\uDDEE\uD83C\uDDE9", "\uD83C\uDDEE\uD83C\uDDF1", "\uD83C\uDDEE\uD83C\uDDF3",
                "\uD83C\uDDEE\uD83C\uDDF6", "\uD83C\uDDEE\uD83C\uDDF7", "\uD83C\uDDEE\uD83C\uDDF8", "\uD83C\uDDEF\uD83C\uDDF4",
                "\uD83C\uDDE6\uD83C\uDDFA", "\uD83C\uDDF0\uD83C\uDDEC", "\uD83C\uDDF0\uD83C\uDDED", "\uD83C\uDDF0\uD83C\uDDF7", "\uD83C\uDDF0\uD83C\uDDFC",
                "\uD83C\uDDF0\uD83C\uDDFF", "\uD83C\uDDF1\uD83C\uDDE6", "\uD83C\uDDF1\uD83C\uDDE7", "\uD83C\uDDF1\uD83C\uDDFE", "\uD83C\uDDF2\uD83C\uDDE6",
                "\uD83C\uDDF2\uD83C\uDDE9", "\uD83C\uDDF2\uD83C\uDDF2", "\uD83C\uDDF2\uD83C\uDDF3", "\uD83C\uDDF2\uD83C\uDDFD", "\uD83C\uDDF2\uD83C\uDDFE",
                "\uD83C\uDDF3\uD83C\uDDF4", "\uD83C\uDDF3\uD83C\uDDFF", "\uD83C\uDDF4\uD83C\uDDF2", "\uD83C\uDDF5\uD83C\uDDED", "\uD83C\uDDF5\uD83C\uDDF0", "\uD83C\uDDF5\uD83C\uDDF1",
                "\uD83C\uDDF6\uD83C\uDDE6", "\uD83C\uDDF7\uD83C\uDDF4", "\uD83C\uDDF7\uD83C\uDDF8", "\uD83C\uDDE6\uD83C\uDDF2", "\uD83C\uDDF8\uD83C\uDDE6",
                "\uD83C\uDDF8\uD83C\uDDE9", "\uD83C\uDDF8\uD83C\uDDEA", "\uD83C\uDDF8\uD83C\uDDEC", "\uD83C\uDDF8\uD83C\uDDFE", "\uD83C\uDDF9\uD83C\uDDED",
                "\uD83C\uDDF9\uD83C\uDDEF", "\uD83C\uDDF9\uD83C\uDDF2", "\uD83C\uDDF9\uD83C\uDDF3", "\uD83C\uDDF9\uD83C\uDDF7", "\uD83C\uDDFA\uD83C\uDDE6",
                "\uD83C\uDDE6\uD83C\uDDEA", "\uD83C\uDDFA\uD83C\uDDFE", "\uD83C\uDDFB\uD83C\uDDEA", "\uD83C\uDDFB\uD83C\uDDF3", "\uD83C\uDDFA\uD83C\uDDF3",
                "\uD83C\uDDFE\uD83C\uDDEA", "\uD83C\uDDFF\uD83C\uDDE6");
    }
    public List<Currency> archiveCurList(String text){
        List<Currency> currencies = dateAllCurrencies(text);
        List<Currency> mainCurrencies = new ArrayList<>();
        for (Currency currency : currencies) {
            if (currency.getCcy().equals("\"USD\"") || currency.getCcy().equals("\"EUR\"")
                    || currency.getCcy().equals("\"RUB\"") || currency.getCcy().equals("\"GBP\"")
                    || currency.getCcy().equals("\"JPY\"") || currency.getCcy().equals("\"AFN\"") ||
                    currency.getCcy().equals("\"TJS\"") || currency.getCcy().equals("\"TMT\"") ||
                    currency.getCcy().equals("\"TRY\"") || currency.getCcy().equals("\"KGS\"") ||
                    currency.getCcy().equals("\"KRW\"") || currency.getCcy().equals("\"KZT\"")) {
                mainCurrencies.add(currency);
            }
        }
        return mainCurrencies;
    }
}
