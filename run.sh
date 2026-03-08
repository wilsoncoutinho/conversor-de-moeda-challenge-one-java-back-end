#!/bin/bash
# Script para executar o Conversor de Moedas no Linux/macOS
# Carrega variáveis de ambiente do arquivo .env

set -e

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Verifica se o arquivo .env existe
if [ ! -f .env ]; then
    echo -e "${RED}=======================================\n"
    echo "ERRO: Arquivo .env não encontrado!"
    echo -e "\n=======================================${NC}"
    echo ""
    echo "Copie o arquivo .env.example para .env"
    echo "e adicione sua chave de API:"
    echo ""
    echo "  cp .env.example .env"
    echo "  nano .env"
    echo ""
    exit 1
fi

# Carrega variáveis do arquivo .env
set -a
source .env
set +a

# Verifica se a chave de API foi definida
if [ -z "$EXCHANGE_RATE_API_KEY" ]; then
    echo -e "${RED}=======================================\n"
    echo "ERRO: EXCHANGE_RATE_API_KEY não definida!"
    echo -e "\n=======================================${NC}"
    echo ""
    echo "Edite o arquivo .env e adicione sua chave:"
    echo "  EXCHANGE_RATE_API_KEY=sua_chave_aqui"
    echo ""
    exit 1
fi

# Verifica se a pasta bin existe
if [ ! -d bin ]; then
    echo ""
    echo -e "${YELLOW}Compilando o projeto...${NC}"
    
    if [ ! -d lib ]; then
        echo ""
        echo -e "${RED}=======================================\n"
        echo "ERRO: Biblioteca GSON não encontrada!"
        echo -e "\n=======================================${NC}"
        echo ""
        echo "Para compilar e executar, você precisa:"
        echo ""
        echo "1. Baixar a biblioteca GSON:"
        echo "   https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/gson-2.8.9.jar"
        echo ""
        echo "2. Criar a pasta lib/ e colocar o arquivo lá:"
        echo "   mkdir lib"
        echo "   # copie o gson-2.8.9.jar para lib/"
        echo ""
        echo "3. Compilar com:"
        echo "   javac -cp 'lib/*' -d bin src/main/java/com/conversor/**/*.java"
        echo ""
        exit 1
    fi
    
    javac -cp "lib/*" -d bin src/main/java/com/conversor/**/*.java
    if [ $? -ne 0 ]; then
        echo -e "${RED}Erro ao compilar!${NC}"
        exit 1
    fi
fi

echo ""
echo -e "${GREEN}Executando o Conversor de Moedas...${NC}"
echo ""

# Executa o programa com a variável de ambiente
java -cp "bin:lib/*" com.conversor.Main
