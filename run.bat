@echo off
REM Script para executar o Conversor de Moedas no Windows
REM Carrega variáveis de ambiente do arquivo .env

setlocal enabledelayedexpansion

REM Verifica se o arquivo .env existe
if not exist .env (
    echo.
    echo =====================================
    echo ERRO: Arquivo .env nao encontrado!
    echo =====================================
    echo.
    echo Copie o arquivo .env.example para .env
    echo e adicione sua chave de API:
    echo.
    echo   cp .env.example .env
    echo   notepad .env
    echo.
    pause
    exit /b 1
)

REM Carrega variáveis do arquivo .env
for /f "tokens=*" %%a in (.env) do (
    if not "%%a"=="" (
        if not "%%a:~0,1%%" == "REM" (
            if not "%%a:~0,1%%" == "#" (
                set %%a
            )
        )
    )
)

REM Verifica se a chave de API foi definida
if "!EXCHANGE_RATE_API_KEY!"=="" (
    echo.
    echo =====================================
    echo ERRO: EXCHANGE_RATE_API_KEY nao definida!
    echo =====================================
    echo.
    echo Edite o arquivo .env e adicione sua chave:
    echo   EXCHANGE_RATE_API_KEY=sua_chave_aqui
    echo.
    pause
    exit /b 1
)

REM Verifica se a pasta bin existe
if not exist bin (
    echo.
    echo Compilando o projeto...
    if not exist lib (
        echo.
        echo =====================================
        echo ERRO: Biblioteca GSON nao encontrada!
        echo =====================================
        echo.
        echo Para compilar e executar, voce precisa:
        echo.
        echo 1. Baixar a biblioteca GSON:
        echo    https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.9/gson-2.8.9.jar
        echo.
        echo 2. Colocar o arquivo na pasta lib/
        echo.
        echo 3. Compilar com:
        echo    javac -cp "lib/gson-2.8.9.jar" -d bin src/main/java/com/conversor/**/*.java
        echo.
        pause
        exit /b 1
    )
    javac -cp "lib/*" -d bin src/main/java/com/conversor/**/*.java
    if errorlevel 1 (
        echo Erro ao compilar!
        pause
        exit /b 1
    )
)

echo.
echo Executando o Conversor de Moedas...
echo.

REM Executa o programa com a variável de ambiente
java -cp "bin;lib/*" com.conversor.Main

pause
