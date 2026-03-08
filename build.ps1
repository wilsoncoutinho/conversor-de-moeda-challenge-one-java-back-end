# Script de Build para Conversor de Moedas
# Compila o código Java e cria JAR executável

param(
    [switch]$Clean
)

Write-Host "===== Construindo Conversor de Moedas =====" -ForegroundColor Green

# Limpar se solicitado
if ($Clean) {
    Write-Host "Limpando arquivos antigos..." -ForegroundColor Yellow
    if (Test-Path "bin") { Remove-Item -Recurse -Force "bin" }
    if (Test-Path "dist") { Remove-Item -Recurse -Force "dist" }
    if (Test-Path "lib") { Remove-Item -Recurse -Force "lib" }
}

# Criar diretórios
Write-Host "Preparando diretórios..." -ForegroundColor Green
New-Item -ItemType Directory -Force -Path "bin", "dist", "lib" | Out-Null
Write-Host "[OK] Diretórios criados" -ForegroundColor Green

# Verificar/verificar dependências
Write-Host "Verificando dependências..." -ForegroundColor Green

$gsonJar = "lib/gson-2.10.1.jar"

if (!(Test-Path $gsonJar)) {
    Write-Host "Baixando Gson..." -ForegroundColor Yellow
    try {
        Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar" -OutFile $gsonJar
        Write-Host "[OK] Gson baixado" -ForegroundColor Green
    } catch {
        Write-Host "[ERRO] Falha ao baixar Gson: $($_.Exception.Message)" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "[OK] Gson encontrado" -ForegroundColor Green
}

# Compilar código-fonte
Write-Host "Compilando código-fonte..." -ForegroundColor Green

$javaFiles = Get-ChildItem -Path "src/main/java" -Recurse -Filter "*.java" | ForEach-Object { $_.FullName }
$classpath = "$gsonJar"

$compileCmd = "javac -cp `"$classpath`" -d bin $javaFiles"
Write-Host "Executando: $compileCmd" -ForegroundColor Gray

try {
    $result = Invoke-Expression $compileCmd 2>&1
    if ($LASTEXITCODE -ne 0) {
        Write-Host "[ERRO] Falha na compilação:" -ForegroundColor Red
        Write-Host $result -ForegroundColor Red
        exit 1
    }
    Write-Host "[OK] Compilação concluída" -ForegroundColor Green
} catch {
    Write-Host "[ERRO] Exceção na compilação: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Criar manifest
Write-Host "Criando manifest..." -ForegroundColor Green
$manifestContent = @"
Manifest-Version: 1.0
Main-Class: com.conversor.Main
Class-Path: gson-2.10.1.jar
"@
$manifestContent | Out-File -FilePath "manifest.txt" -Encoding ASCII
Write-Host "[OK] Manifest criado" -ForegroundColor Green

# Criar JAR executável
Write-Host "Criando JAR executável..." -ForegroundColor Green
$jarCmd = "jar cfm dist/conversor-moedas.jar manifest.txt -C bin ."
Write-Host "Executando: $jarCmd" -ForegroundColor Gray

try {
    $result = Invoke-Expression $jarCmd 2>&1
    if ($LASTEXITCODE -ne 0) {
        Write-Host "[ERRO] Falha na criação do JAR:" -ForegroundColor Red
        Write-Host $result -ForegroundColor Red
        exit 1
    }
    Write-Host "[OK] JAR criado: dist/conversor-moedas.jar" -ForegroundColor Green
} catch {
    Write-Host "[ERRO] Exceção na criação do JAR: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Copiar dependências
Write-Host "Copiando dependências..." -ForegroundColor Green
Copy-Item $gsonJar -Destination "dist/" -Force
Write-Host "[OK] Dependências copiadas" -ForegroundColor Green

# Criar script de execução
Write-Host "Criando script de execução..." -ForegroundColor Green
$runScript = @'
@echo off
setlocal enabledelayedexpansion

REM Carrega variáveis de ambiente do arquivo .env
if exist .env (
    for /f "tokens=*" %%a in (.env) do (
        if not "%%a"=="" (
            if not "%%a:~0,1%%" == "REM" (
                if not "%%a:~0,1%%" == "#" (
                    set %%a
                )
            )
        )
    )
)

REM Executa o JAR
cd dist
java -cp "conversor-moedas.jar;gson-2.10.1.jar" com.conversor.Main
cd ..
'@
$runScript | Out-File -FilePath "run-jar.bat" -Encoding UTF8
Write-Host "[OK] Script de execução criado: run-jar.bat" -ForegroundColor Green

Write-Host "===== Build Concluído com Sucesso! =====" -ForegroundColor Green
Write-Host ""
Write-Host "Arquivos gerados:"
Write-Host "  JAR executável: dist/conversor-moedas.jar"
Write-Host "  Script: run-jar.bat"
Write-Host ""
Write-Host "Para executar:"
Write-Host "  .\run-jar.bat"
Write-Host ""
Write-Host "Ou:"
Write-Host "  java -cp 'dist/conversor-moedas.jar;dist/gson-2.10.1.jar' com.conversor.Main"