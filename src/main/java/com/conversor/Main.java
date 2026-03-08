package com.conversor;

import com.conversor.service.CurrencyConverter;
import com.conversor.model.ExchangeRateResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    Bem-vindo ao Conversor de Moedas!   ║");
        System.out.println("║   Powered by Exchange Rate API         ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("Digite a moeda de origem (ex: USD): ");
                String fromCurrency = scanner.nextLine().trim().toUpperCase();

                System.out.print("Digite a moeda de destino (ex: BRL): ");
                String toCurrency = scanner.nextLine().trim().toUpperCase();

                System.out.print("Digite o valor a converter: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());

                // Obter detalhes da conversão
                ExchangeRateResponse response = converter.getConversionDetails(fromCurrency, toCurrency, amount);

                // Exibir resultado
                System.out.println("\n" + "=".repeat(50));
                System.out.printf("Conversão de %s para %s%n", fromCurrency, toCurrency);
                System.out.println("=".repeat(50));
                System.out.printf("Valor original:    %.2f %s%n", amount, fromCurrency);
                System.out.printf("Taxa de câmbio:    %.4f%n", response.getConversion_rate());
                System.out.printf("Valor convertido:  %.2f %s%n", response.getConversion_result(), toCurrency);
                System.out.println("=".repeat(50) + "\n");

            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite um valor numérico válido!\n");
            } catch (Exception e) {
                System.out.println("❌ Erro na conversão: " + e.getMessage() + "\n");
            }

            System.out.print("Deseja fazer outra conversão? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            continuar = resposta.equals("s") || resposta.equals("sim");
        }

        System.out.println("\nObrigado por usar o Conversor de Moedas! 👋");
        scanner.close();
    }
}
