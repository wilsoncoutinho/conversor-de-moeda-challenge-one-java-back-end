# Conversor de Moeda - Challenge One Java Back-End

Um aplicativo de conversão de moedas desenvolvido em Java como parte do desafio ONE da Alura. Utiliza a **Exchange Rate API** para obter taxas de câmbio em tempo real.

## Funcionalidades

- ✅ Converter entre diferentes moedas (161 moedas suportadas)
- ✅ Integração com API de taxas de câmbio em tempo real
- ✅ Interface interativa no console
- ✅ Validação de dados de entrada
- ✅ Tratamento de erros robusto
- ✅ Obtém taxa de câmbio e resultado da conversão

## Requisitos

- Java 11 ou superior
- Conexão com a Internet (para acessar a API)
- Chave de API gratuita do [Exchange Rate API](https://www.exchangerate-api.com/)

## Instalação

### 1. Clone o repositório
```bash
git clone https://github.com/wilsoncoutinho/conversor-de-moeda-challenge-one-java-back-end.git
cd conversor-de-moeda-challenge-one-java-back-end
```

### 2. Obtenha a chave de API
1. Acesse [exchangerate-api.com](https://www.exchangerate-api.com/)
2. Registre uma conta gratuita
3. Copie sua Chave de API
4. Abra `src/main/java/com/conversor/api/CurrencyExchangeAPI.java`
5. Substitua a chave na linha:
   ```java
   private static final String API_KEY = "SUA_CHAVE_AQUI";
   ```

### 3. Compile o projeto
```bash
javac -d bin src/main/java/com/conversor/*.java src/main/java/com/conversor/**/*.java
```

### 4. Execute o programa
```bash
java -cp bin com.conversor.Main
```

## Como Usar

1. Execute o programa
2. Digite a moeda de origem (ex: USD)
3. Digite a moeda de destino (ex: BRL)
4. Digite o valor a converter
5. Veja o resultado com a taxa de câmbio

### Exemplo de Execução
```
╔════════════════════════════════════════╗
║    Bem-vindo ao Conversor de Moedas!   ║
║   Powered by Exchange Rate API         ║
╚════════════════════════════════════════╝

Digite a moeda de origem (ex: USD): USD
Digite a moeda de destino (ex: BRL): BRL
Digite o valor a converter: 100

==================================================
Conversão de USD para BRL
==================================================
Valor original:    100.00 USD
Taxa de câmbio:    5.1234
Valor convertido:  512.34 BRL
==================================================

Deseja fazer outra conversão? (s/n): n

Obrigado por usar o Conversor de Moedas! 👋
```

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── conversor/
│   │           ├── Main.java                    # Classe principal com UI
│   │           ├── api/
│   │           │   └── CurrencyExchangeAPI.java # Cliente HTTP da API
│   │           ├── model/
│   │           │   └── ExchangeRateResponse.java # Modelo de resposta
│   │           └── service/
│   │               └── CurrencyConverter.java   # Lógica de conversão
│   └── resources/
│       └── config.properties                    # Configurações da API
└── test/
    └── java/
```

## Dependências

- **GSON**: Biblioteca para converter JSON em objetos Java
  - Baixe em: https://repo1.maven.org/maven2/com/google/code/gson/gson/
  - Adicione o JAR ao classpath ao compilar

## API Utilizada

**Exchange Rate API** - https://www.exchangerate-api.com/

### Endpoints
- `GET /pair/{base_code}/{target_code}` - Obtém taxa de câmbio
- `GET /pair/{base_code}/{target_code}/{amount}` - Converte um valor

### Moedas Suportadas
A API suporta mais de 161 moedas, incluindo: USD, BRL, EUR, GBP, JPY, AUD, CAD, CHF, etc.

Para ver a lista completa e entender melhor a API, consulte [API-DOCUMENTATION.md](API-DOCUMENTATION.md)

## Status da API

- ✅ Plano Gratuito: 1.500 requisições/mês
- ✅ Sem configuração complicada
- ✅ Autenticação via chave simples

## Segurança

⚠️ **Importante**: A chave de API foi incluída apenas para demonstração. Em produção:
- Use variáveis de ambiente
- Nunca exponha a chave em repositórios públicos
- Considere usar um servidor backend para fazer as requisições

## Próximas Melhorias

- [ ] Implementar interface gráfica (Swing/JavaFX)
- [ ] Adicionar histórico de conversões
- [ ] Implementar cache de taxas
- [ ] Criar testes unitários
- [ ] Gerar arquivo JAR executável
- [ ] Adicionar suporte a mais fontes de dados
- [ ] Criar endpoint REST

## Autor

Wilson Coutinho

## Licença

Este projeto está sob a licença MIT.

## Referências

- [Exchange Rate API Docs](https://www.exchangerate-api.com/docs)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [GSON Library](https://github.com/google/gson)
- [ISO 4217 Currency Codes](https://www.iso.org/iso-4217-currency-codes.html)

## Contato & Suporte

Para dúvidas sobre a API, consulte a [documentação completa](API-DOCUMENTATION.md).

