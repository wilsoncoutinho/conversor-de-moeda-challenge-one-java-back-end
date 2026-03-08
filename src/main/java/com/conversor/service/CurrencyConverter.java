package com.conversor.service;

import com.conversor.api.CurrencyExchangeAPI;
import com.conversor.model.ExchangeRateResponse;

/**
 * Serviço de conversão de moedas
 */
public class CurrencyConverter {
    
    private final CurrencyExchangeAPI api;

    public CurrencyConverter() {
        this.api = new CurrencyExchangeAPI();
    }

    /**
     * Converte um valor de uma moeda para outra
     * @param fromCurrency Moeda de origem
     * @param toCurrency Moeda de destino
     * @param amount Valor a converter
     * @return Valor convertido
     * @throws Exception se houver erro na conversão
     */
    public double convert(String fromCurrency, String toCurrency, double amount) throws Exception {
        if (amount < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
        
        ExchangeRateResponse response = api.convertCurrency(fromCurrency, toCurrency, amount);
        
        if ("success".equals(response.getResult())) {
            return response.getConversion_result();
        } else {
            throw new Exception("Erro ao obter taxa de conversão");
        }
    }

    /**
     * Obtém apenas a taxa de conversão entre duas moedas
     * @param fromCurrency Moeda de origem
     * @param toCurrency Moeda de destino
     * @return Taxa de conversão
     * @throws Exception se houver erro
     */
    public double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        ExchangeRateResponse response = api.getExchangeRate(fromCurrency, toCurrency);
        
        if ("success".equals(response.getResult())) {
            return response.getConversion_rate();
        } else {
            throw new Exception("Erro ao obter taxa de conversão");
        }
    }

    /**
     * Obtém informações detalhadas da conversão
     * @param fromCurrency Moeda de origem
     * @param toCurrency Moeda de destino
     * @param amount Valor a converter
     * @return Response com detalhes completos
     * @throws Exception se houver erro
     */
    public ExchangeRateResponse getConversionDetails(String fromCurrency, String toCurrency, double amount) throws Exception {
        return api.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
