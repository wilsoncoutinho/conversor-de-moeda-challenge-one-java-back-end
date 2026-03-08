# Configuração de Variáveis de Ambiente

Este projeto usa variáveis de ambiente para armazenar dados sensíveis como chaves de API.

## ⚠️ Segurança

**Nunca** commit arquivos `.env` ou similares em repositórios públicos. Eles já estão no `.gitignore`.

## 🔧 Como Configurar

### Opção 1: Windows (Command Prompt)
```cmd
set EXCHANGE_RATE_API_KEY=sua_chave_api_aqui
java -cp bin com.conversor.Main
```

### Opção 2: Windows (PowerShell)
```powershell
$env:EXCHANGE_RATE_API_KEY="sua_chave_api_aqui"
java -cp bin com.conversor.Main
```

### Opção 3: Linux / macOS
```bash
export EXCHANGE_RATE_API_KEY=sua_chave_api_aqui
java -cp bin com.conversor.Main
```

### Opção 4: Arquivo .env (Recomendado para desenvolvimento)

1. Copie o arquivo `.env.example` para `.env`:
```bash
cp .env.example .env
```

2. Edite o arquivo `.env` e adicione sua chave:
```
EXCHANGE_RATE_API_KEY=9026bfb0183b94379a5ef93b
```

3. Para carregar automaticamente em Windows, crie um arquivo `run.bat`:
```batch
@echo off
for /f "tokens=*" %%a in (.env) do set %%a
java -cp bin com.conversor.Main
```

4. Execute: `run.bat`

### Opção 5: Linux/macOS com Script
Crie um arquivo `run.sh`:
```bash
#!/bin/bash
set -a
source .env
set +a
java -cp bin com.conversor.Main
```

Torne executável:
```bash
chmod +x run.sh
./run.sh
```

## 📝 Passos Rápidos

1. **Obtenha sua chave:**
   - Acesse https://www.exchangerate-api.com/
   - Registre uma conta
   - Copie a API Key

2. **Configure a variável:**
   - Execute o comando apropriado para seu SO (veja acima)
   - Ou crie um arquivo `.env`

3. **Execute o programa:**
   ```bash
   java -cp bin com.conversor.Main
   ```

## ✅ Verificar Configuração

Para verificar se a variável está configurada corretamente:

**Windows (Command Prompt):**
```cmd
echo %EXCHANGE_RATE_API_KEY%
```

**Windows (PowerShell):**
```powershell
$env:EXCHANGE_RATE_API_KEY
```

**Linux/macOS:**
```bash
echo $EXCHANGE_RATE_API_KEY
```

Se aparecer a chave, está configurada corretamente! Se estiver em branco, siga as instruções acima.

## 🚀 Integração com IDEs

### NetBeans
1. Clique com botão direito no projeto → Properties
2. Run → Set Project Configuration → Default
3. Em "Working Directory", defina a pasta do projeto
4. Na seção "Environment", adicione: `EXCHANGE_RATE_API_KEY=sua_chave`

### IntelliJ IDEA
1. Run → Edit Configurations
2. Em "Environment variables", adicione:
```
EXCHANGE_RATE_API_KEY=sua_chave
```

### Eclipse
1. Run → Run Configurations
2. Abra a aba "Environment"
3. Clique "New" e adicione:
   - Name: `EXCHANGE_RATE_API_KEY`
   - Value: `sua_chave`

### VS Code
Crie um arquivo `.vscode/launch.json`:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "Java Debug",
            "type": "java",
            "name": "Launch Conversor",
            "request": "launch",
            "mainClass": "com.conversor.Main",
            "projectName": "conversor-de-moeda",
            "env": {
                "EXCHANGE_RATE_API_KEY": "sua_chave"
            }
        }
    ]
}
```

## 🔒 Boas Práticas

✅ **Faça:**
- Use variáveis de ambiente para dados sensíveis
- Inclua `.env` no `.gitignore`
- Use `.env.example` para documentar configurações
- Revise o `.gitignore` antes de fazer push

❌ **Não faça:**
- Commit de chaves diretamente no código
- Commit de arquivo `.env`
- Compartilhar chaves em chats ou emails
- Usar chaves em repositórios públicos

## 🆘 Solução de Problemas

### "Variável de ambiente 'EXCHANGE_RATE_API_KEY' não configurada"
**Solução:** Configure a variável seguindo as instruções acima para seu SO.

### "Chave inválida"
Verifique:
1. Copiou a chave corretamente?
2. A chave tem espaços em branco?
3. Sua conta está ativa em exchangerate-api.com?

### "Connection refused"
Verifique se você tem Internet e acesso a `https://v6.exchangerate-api.com/`

## 📚 Referências

- [Variáveis de Ambiente (Windows)](https://docs.microsoft.com/pt-br/previous-versions/windows/it-pro/windows-xp/bb490954(v=technet.10))
- [Variáveis de Ambiente (Linux)](https://www.gnu.org/software/bash/manual/html_node/Environment.html)
- [Exchange Rate API Docs](https://www.exchangerate-api.com/docs)
- [12 Factor App - Config](https://12factor.net/config)
