@echo off
setlocal enabledelayedexpansion

REM Carrega vari??veis de ambiente do arquivo .env
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
java -cp "conversor-moedas.jar;gson-2.8.9.jar" com.conversor.Main
cd ..
