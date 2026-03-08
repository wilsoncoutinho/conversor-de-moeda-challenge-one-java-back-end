# Conversor de Moeda - Challenge One Java Back-End

Um aplicativo de conversão de moedas desenvolvido em Java como parte do desafio ONE da Alura. Utiliza a **Exchange Rate API** para obter taxas de câmbio em tempo real.

## 🎥 Demonstração

Veja o conversor em ação:

[![Demonstração do Conversor de Moedas](https://img.youtube.com/vi/wwlSyQhPZQ4/maxresdefault.jpg)](https://www.youtube.com/watch?v=wwlSyQhPZQ4)

## Funcionalidades

- ✅ Converter entre 6 moedas principais (ARS, BOB, BRL, CLP, COP, USD)
- ✅ Integração com API de taxas de câmbio em tempo real
- ✅ Interface interativa no console com validação
- ✅ Tratamento robusto de erros da API
- ✅ Armazenamento automático da chave API
- ✅ Suporte a múltiplas conversões na mesma sessão

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

**Windows (recomendado):**
```bash
# Build automático
.\build.ps1

# Executar
.\run-jar.bat
```

**Linux/macOS:**
```bash
# Build com Maven (se disponível)
mvn clean package
java -jar target/conversor-moedas-1.0.0.jar

# Ou build manual
chmod +x build.sh run.sh
./build.sh
./run.sh
```

**Build Manual (qualquer SO):**
```bash
# Baixe GSON 2.10.1: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
# Crie a pasta lib/ e coloque o JAR lá

javac -cp "lib/gson-2.10.1.jar" -d bin src/main/java/com/conversor/Main.java
java -cp "bin;lib/gson-2.10.1.jar" com.conversor.Main
```

## Como Usar

1. Execute o programa
2. Digite a moeda de origem (ex: USD)
3. Digite a moeda de destino (ex: BRL)
4. Digite o valor a converter
5. Veja o resultado com a taxa de câmbio

### Exemplo de Execução
```
===========================================
   Conversor de Moedas (Consulta por Pares)  
===========================================

***************************************************
Moedas disponíveis para conversão:
 ARS - Peso argentino
 BOB - Boliviano boliviano
 BRL - Real brasileiro
 CLP - Peso chileno
 COP - Peso colombiano
 USD - Dólar americano
***************************************************
(Digite 'SAIR' a qualquer momento para encerrar)

Moeda de origem (ex: USD): USD
Moeda de destino (ex: BRL): BRL
Valor a converter: 100

A contactar a API e a obter as taxas de câmbio...

--- Resultado da Conversão ---
Taxa de câmbio: 1 USD = 5,2664 BRL
Valor final: 100,00 USD = 526,64 BRL
------------------------------

Pressione ENTER para realizar uma nova conversão...
```

## Estrutura do Projeto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── conversor/
│               └── Main.java                    # Classe principal com UI console
├── main/
│   └── resources/                               # Recursos estáticos (se necessário)
├── test/
│   └── java/                                    # Testes (futuro)
├── lib/                                         # Dependências JAR
├── dist/                                        # Arquivos compilados e JAR
├── index.html                                   # Interface web (bonus)
├── build.ps1                                    # Script de build PowerShell
├── run-jar.bat                                  # Script de execução
├── pom.xml                                      # Configuração Maven
├── .env                                         # Variáveis de ambiente (local)
├── .env.example                                 # Exemplo de configuração
└── README.md                                    # Esta documentação
```

## Dependências

- **Java 17+**: Para suporte a Records e HttpClient moderno
- **GSON 2.10.1**: Biblioteca para converter JSON em objetos Java
  - Baixe em: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
  - Ou use Maven/Gradle para gerenciar dependências

## Tecnologias Utilizadas

- **Java 17**: Linguagem principal com Records e HttpClient
- **HttpClient**: Cliente HTTP moderno (Java 11+)
- **GSON 2.10.1**: Serialização/deserialização JSON
- **PowerShell**: Scripts de build e execução
- **Maven**: Gerenciamento de dependências e build
- **Exchange Rate API**: Fonte de dados de taxas de câmbio
- **HTML/CSS/JavaScript**: Interface web bonus (Tailwind + Lucide Icons)

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

- [x] **Implementado**: Interface web HTML (bonus)
- [x] **Implementado**: Armazenamento automático da chave API
- [x] **Implementado**: Validação de moedas permitidas
- [x] **Implementado**: HttpClient moderno (Java 11+)
- [x] **Implementado**: Records para tipagem forte
- [ ] Adicionar mais moedas à lista permitida
- [ ] Implementar interface gráfica (Swing/JavaFX)
- [ ] Adicionar histórico de conversões
- [ ] Implementar cache de taxas
- [ ] Criar testes unitários
- [ ] Melhorar tratamento de erros de rede
- [ ] Adicionar suporte a conversão offline (cache)

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

