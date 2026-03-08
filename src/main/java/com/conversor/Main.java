import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

// Importação da biblioteca Gson (Google) necessária para manipular o JSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Conversor de Moedas utilizando Java HttpClient e a ExchangeRate-API.
 * * Nota: Para compilar e executar este ficheiro, é necessário ter a biblioteca Gson 
 * no seu classpath (por exemplo, adicionando a dependência no Maven ou Gradle).
 */
public class ConversorDeMoedas {

    // Utilização de um Record com a anotação @SerializedName do Gson.
    // Isso permite manter as variáveis no padrão Java (camelCase) enquanto
    // mapeia corretamente para as chaves do JSON que usam snake_case ou hífens.
    public record RespostaCambio(
        String result, 
        @SerializedName("base_code") String baseCode, 
        @SerializedName("target_code") String targetCode, 
        @SerializedName("conversion_rate") double conversionRate, 
        @SerializedName("error-type") String errorType 
    ) {}

    /**
     * Fase 9: Método modularizado para realizar o cálculo e exibir os resultados.
     * Isolar esta lógica torna o código principal mais limpo, fácil de ler e reutilizável.
     */
    public static void realizarConversao(String moedaBase, String moedaAlvo, double valor, double taxa) {
        double valorConvertido = valor * taxa; // Aplica a taxa de conversão

        System.out.println("\n--- Resultado da Conversão ---");
        System.out.printf("Taxa de câmbio: 1 %s = %.4f %s%n", moedaBase, taxa, moedaAlvo);
        System.out.printf("Valor final: %.2f %s = %.2f %s%n", valor, moedaBase, valorConvertido, moedaAlvo);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Utilizando o GsonBuilder (Fase 7) para criar uma instância do Gson.
        Gson gson = new GsonBuilder().create();

        System.out.println("===========================================");
        System.out.println("   Conversor de Moedas (Consulta por Pares)  ");
        System.out.println("===========================================\n");

        // 1. Obter a chave da API (Solicitamos apenas uma vez no início)
        System.out.print("Introduza a sua chave da ExchangeRate-API: ");
        String apiKey = scanner.nextLine().trim();

        // Fase 8: Definindo a lista de moedas filtradas permitidas
        List<String> moedasValidas = List.of("ARS", "BOB", "BRL", "CLP", "COP", "USD");

        // Loop interativo para manter o programa a correr
        while (true) {
            System.out.println("\n***************************************************");
            System.out.println("Moedas disponíveis para conversão:");
            System.out.println(" ARS - Peso argentino");
            System.out.println(" BOB - Boliviano boliviano");
            System.out.println(" BRL - Real brasileiro");
            System.out.println(" CLP - Peso chileno");
            System.out.println(" COP - Peso colombiano");
            System.out.println(" USD - Dólar americano");
            System.out.println("***************************************************");
            System.out.println("(Digite 'SAIR' a qualquer momento para encerrar)\n");

            System.out.print("Moeda de origem (ex: USD): ");
            String moedaBase = scanner.nextLine().trim().toUpperCase();
            
            if (moedaBase.equals("SAIR")) break;

            // Validação do filtro de moedas (Apenas as 6 permitidas)
            if (!moedasValidas.contains(moedaBase)) {
                System.out.println("-> Erro: Moeda não suportada. Por favor, escolha uma moeda da lista.");
                continue; // Volta para o início do loop sem quebrar a aplicação
            }

            System.out.print("Moeda de destino (ex: BRL): ");
            String moedaAlvo = scanner.nextLine().trim().toUpperCase();
            
            if (moedaAlvo.equals("SAIR")) break;

            if (!moedasValidas.contains(moedaAlvo)) {
                System.out.println("-> Erro: Moeda não suportada. Por favor, escolha uma moeda da lista.");
                continue;
            }

            System.out.print("Valor a converter: ");
            String inputValor = scanner.nextLine().trim();
            if (inputValor.equalsIgnoreCase("SAIR")) break;

            double valor;
            try {
                valor = Double.parseDouble(inputValor.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("-> Erro: Por favor, introduza um valor numérico válido.");
                continue; 
            }

            // 2. Construir o URL
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + moedaBase + "/" + moedaAlvo;

            // 3. Configurar o HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            System.out.println("\nA contactar a API e a obter as taxas de câmbio...\n");

            try {
                // 4. Enviar o pedido e guardar a resposta
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                int statusCode = response.statusCode();

                // Tratamento de Respostas
                switch (statusCode) {
                    case 200 -> {
                        RespostaCambio respostaAPI = gson.fromJson(response.body(), RespostaCambio.class);

                        if ("success".equalsIgnoreCase(respostaAPI.result())) {
                            // Fase 9: Chamamos o nosso novo método passando as variáveis armazenadas
                            realizarConversao(moedaBase, moedaAlvo, valor, respostaAPI.conversionRate());
                        } else {
                            System.out.println("\n[Aviso] A API retornou um erro estrutural: " + respostaAPI.errorType());
                        }
                    }
                    case 403 -> System.out.println("\n[Erro 403] Acesso negado. A sua chave de API é inválida ou não tem permissões.");
                    case 404 -> System.out.println("\n[Erro 404] O endpoint ou par de moedas não foi encontrado pela API.");
                    case 500 -> System.out.println("\n[Erro 500] O servidor da API está com problemas internos. Tente mais tarde.");
                    default -> {
                        System.out.println("\n[Erro] Falha na requisição HTTP. Código: " + statusCode);
                        System.out.println("Detalhes: " + response.body());
                    }
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Ocorreu um erro ao comunicar com o servidor: " + e.getMessage());
            }
            
            // Pausa para leitura antes de limpar e mostrar o menu novamente
            System.out.println("\nPressione ENTER para realizar uma nova conversão...");
            scanner.nextLine();
        }
        
        System.out.println("\nObrigado por utilizar o Conversor de Moedas! A encerrar o programa...");
        scanner.close();
    }
}