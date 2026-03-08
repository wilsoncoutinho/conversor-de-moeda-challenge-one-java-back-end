@echo off
setlocal enabledelayedexpansion

REM Carrega variÃ¡veis de ambiente do arquivo .env
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
