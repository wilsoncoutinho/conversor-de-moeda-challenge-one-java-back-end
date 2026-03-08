package com.conversor.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.conversor.model.ExchangeRateResponse;
import com.google.gson.Gson;

/**
 * Classe responsável por fazer requisições à API Exchange Rate API
 */
public class CurrencyExchangeAPI {
    
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6";
    private static final String API_KEY = "9026bfb0183b94379a5ef93b"; // Sua chave de API
    private final Gson gson = new Gson();

    /**
     * Obtém a taxa de conversão entre duas moedas
     * @param fromCurrency Código da moeda de origem (ex: USD)
     * @param toCurrency Código da moeda de destino (ex: BRL)
     * @return ExchangeRateResponse com os dados da conversão
     * @throws Exception se houver erro na requisição
     */
    public ExchangeRateResponse getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String urlString = String.format("%s/%s/pair/%s/%s",
                API_BASE_URL, API_KEY, fromCurrency.toUpperCase(), toCurrency.toUpperCase());
        
        return fetchFromAPI(urlString);
    }

    /**
     * Realiza uma conversão de um valor específico entre duas moedas
     * @param fromCurrency Código da moeda de origem
     * @param toCurrency Código da moeda de destino
     * @param amount Valor a ser convertido
     * @return ExchangeRateResponse com o resultado da conversão
     * @throws Exception se houver erro na requisição
     */
    public ExchangeRateResponse convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception {
        String urlString = String.format("%s/%s/pair/%s/%s/%.2f",
                API_BASE_URL, API_KEY, fromCurrency.toUpperCase(), toCurrency.toUpperCase(), amount);
        
        return fetchFromAPI(urlString);
    }

    /**
     * Faz a requisição HTTP à API e retorna a resposta parsed
     * @param urlString URL completa da requisição
     * @return ExchangeRateResponse com os dados da resposta
     * @throws Exception se houver erro na requisição
     */
    private ExchangeRateResponse fetchFromAPI(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            return gson.fromJson(response.toString(), ExchangeRateResponse.class);
        } else {
            throw new Exception("Erro na API: HTTP " + responseCode);
        }
    }
}
