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

### 2. Configure a Chave de API (IMPORTANTE!)
⚠️ **A chave é armazenada em variáveis de ambiente, não no código!**

**Opção A - Usando arquivo .env (Recomendado):**
```bash
# Copie o arquivo de exemplo
cp .env.example .env

# Edite e adicione sua chave
# Abra .env no seu editor e adicione:
# EXCHANGE_RATE_API_KEY=sua_chave_aqui
```

**Opção B - Variável de ambiente (Windows):**
```cmd
set EXCHANGE_RATE_API_KEY=sua_chave_aqui
```

**Opção B - Variável de ambiente (Linux/macOS):**
```bash
export EXCHANGE_RATE_API_KEY=sua_chave_aqui
```

Para detalhes completos, consulte [ENVIRONMENT-SETUP.md](ENVIRONMENT-SETUP.md)

### 3. Obtenha sua Chave de API
1. Acesse [exchangerate-api.com](https://www.exchangerate-api.com/)
2. Registre uma conta gratuita
3. Copie sua Chave de API
4. Configure conforme a opção acima

### 4. Compile e Execute

**Windows (mais fácil):**
```bash
run.bat
```

**Linux/macOS:**
```bash
chmod +x run.sh
./run.sh
```

**Manual (qualquer SO):**
```bash
# Baixe GSON de: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/gson-2.8.9.jar
# Crie a pasta lib/ e coloque o gson-2.8.9.jar lá

javac -cp "lib/*" -d bin src/main/java/com/conversor/**/*.java
java -cp "bin:lib/*" com.conversor.Main  # Linux/macOS
java -cp "bin;lib\*" com.conversor.Main  # Windows
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

✅ **Implementado neste projeto:**
- Chave de API armazenada em **variáveis de ambiente**, não no código
- Arquivo `.env` ignorado pelo Git (não será exposto)
- Arquivo `.env.example` como documentação
- Validação e tratamento robusto de erros

⚠️ **Importante:**
- O arquivo `.env` contém dados sensíveis e **não deve ser compartilhado**
- Está em `.gitignore` e não será incluído em commits
- Use variáveis de ambiente em produção
- Consulte [ENVIRONMENT-SETUP.md](ENVIRONMENT-SETUP.md) para detalhes

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

