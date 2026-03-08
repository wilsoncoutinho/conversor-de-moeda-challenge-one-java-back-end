# API - Exchange Rate API

## 📌 Visão Geral

Este projeto utiliza a **Exchange Rate API** para obter taxas de câmbio em tempo real. Essa API permite criar aplicações que realizam conversões de moedas com dados atualizados diariamente.

### Link da Documentação Oficial
- **URL**: https://www.exchangerate-api.com/
- **Documentação**: https://www.exchangerate-api.com/docs

---

## 🔑 Obtenção da Chave de API

### Passo 1: Acesse o site
Visite [exchangerate-api.com](https://www.exchangerate-api.com/)

### Passo 2: Registre uma conta
1. Clique em **"Sign Up"** ou **"Get Free"**
2. Preencha o formulário com seus dados:
   - Email
   - Senha
   - Confirmar senha
3. Clique em **Create Account**

### Passo 3: Confirme seu email
1. Verifique seu email
2. Clique no link de confirmação

### Passo 4: Obtenha a chave
1. Faça login em sua conta
2. Vá para o dashboard
3. Copie a **API Key** (Uma string longa de caracteres)

### Passo 5: Configure no projeto
Substitua a chave em `src/main/java/com/conversor/api/CurrencyExchangeAPI.java`:

```java
private static final String API_KEY = "SUA_CHAVE_AQUI";
```

---

## 📋 Planos Disponíveis

| Plano | Requisições/Mês | Moedas Suportadas | Custo |
|-------|-----------------|------------------|-------|
| Free (Gratuito) | 1.500 | 161 | Grátis |
| Basic | 150.000 | 161 | $7/mês |
| Pro | 1.500.000 | 161 | $20/mês |
| Business | Ilimitado | 161 | Contato |

---

## 🔗 Endpoints Utilizados

### 1. Consulta de Taxa de Câmbio
**Endpoint**: `GET /pair/{base_code}/{target_code}`

Retorna a taxa de câmbio em tempo real.

**Exemplo**:
```
https://v6.exchangerate-api.com/v6/{API_KEY}/pair/USD/BRL
```

⚠️ Nunca inclua sua chave de API em URLs publicamente visíveis!

**Resposta**:
```json
{
    "result": "success",
    "documentation": "https://www.exchangerate-api.com/docs",
    "terms_of_use": "https://www.exchangerate-api.com/terms",
    "time_last_update_unix": 1234567890,
    "time_last_update_utc": "Fri, 02 Jan 2024 00:00:00 +0000",
    "time_next_update_unix": 1234654290,
    "time_next_update_utc": "Sat, 03 Jan 2024 00:00:00 +0000",
    "base_code": "USD",
    "target_code": "BRL",
    "conversion_rate": 5.1234
}
```

### 2. Conversão com Valor Específico
**Endpoint**: `GET /pair/{base_code}/{target_code}/{amount}`

Converte um valor específico de uma moeda para outra.

**Exemplo**:
```
https://v6.exchangerate-api.com/v6/9026bfb0183b94379a5ef93b/pair/USD/BRL/100
```

**Resposta**:
```json
{
    "result": "success",
    "base_code": "USD",
    "target_code": "BRL",
    "conversion_rate": 5.1234,
    "conversion_result": 512.34
}
```

---

## 💻 Como Usar a API no Projeto

### Exemplo 1: Obter Taxa de Câmbio
```java
CurrencyConverter converter = new CurrencyConverter();
double rate = converter.getExchangeRate("USD", "BRL");
System.out.println("Taxa: " + rate);
```

### Exemplo 2: Converter Valor
```java
CurrencyConverter converter = new CurrencyConverter();
double result = converter.convert("USD", "BRL", 100.00);
System.out.println("100 USD = " + result + " BRL");
```

### Exemplo 3: Obter Detalhes Completos
```java
CurrencyConverter converter = new CurrencyConverter();
ExchangeRateResponse response = converter.getConversionDetails("USD", "BRL", 100.00);

System.out.println("Moeda origem: " + response.getBase_code());
System.out.println("Moeda destino: " + response.getTarget_code());
System.out.println("Taxa: " + response.getConversion_rate());
System.out.println("Resultado: " + response.getConversion_result());
```

---

## ⚠️ Códigos de Moedas Suportados

A API suporta mais de 161 moedas. Aqui estão alguns exemplos:

| Código | Moeda | País |
|--------|-------|------|
| USD | Dólar Americano | Estados Unidos |
| BRL | Real Brasileiro | Brasil |
| EUR | Euro | União Europeia |
| GBP | Libra Esterlina | Reino Unido |
| JPY | Iene Japonês | Japão |
| AUD | Dólar Australiano | Austrália |
| CAD | Dólar Canadense | Canadá |
| CHF | Franco Suíço | Suíça |
| MXN | Peso Mexicano | México |
| CNY | Yuan Chinês | China |

Para a lista completa, consulte: https://www.exchangerate-api.com/docs/supported-currencies

---

## 🛡️ Tratamento de Erros

A API retorna diferentes códigos de erro:

| Código | Descrição |
|--------|-----------|
| `success` | Requisição bem-sucedida |
| `unsupported-code` | Código de moeda não suportado |
| `malformed-request` | Requisição malformada |
| `invalid-key` | Chave de API inválida |
| `inactive-account` | Conta inativa |
| `quota-reached` | Limite de requisições atingido |

---

## 📝 Anotações Importantes

⚠️ **Segurança**: 
- Nunca compartilhe sua chave de API em repositórios públicos
- Considere usar variáveis de ambiente para armazenar a chave
- A chave foi incluída neste projeto apenas para demonstração

✅ **Boas Práticas**:
- Verifique sempre o status `result` da resposta
- Trate exceções de conexão
- Implemente cache para reduzir requisições à API
- Respeite os limites de taxa (rate limiting)

---

## 🔄 Próximos Passos

1. Gerar arquivo JAR executável
2. Implementar interface gráfica (Swing/JavaFX)
3. Adicionar histórico de conversões
4. Criar testes unitários
5. Implementar cache local de taxas

---

## 📚 Referências

- Documentação Official: https://www.exchangerate-api.com/docs
- GitHub da API: https://github.com/fawazahmed0/exchange-api
- Códigos ISO 4217: https://www.iso.org/iso-4217-currency-codes.html
