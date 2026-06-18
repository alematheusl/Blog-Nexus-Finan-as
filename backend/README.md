# Nexus Finanças - Blog

Plataforma de blog desenvolvida como teste técnico, com área pública para leitura de artigos e painel administrativo para gerenciamento de conteúdo.

---

## Observações do Desenvolvedor

Durante o desenvolvimento deste projeto, utilizei o auxílio da IA Claude (Anthropic) para identificar e corrigir alguns erros, além de otimizar blocos de código. A IA foi utilizada como ferramenta de apoio.

---

## Tecnologias Utilizadas

**Backend**
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

**Frontend**
- HTML, CSS e JavaScript puro
- Live Server (extensão do VS Code)

---

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [Java 21](https://www.oracle.com/java/technologies/downloads/)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- [VS Code](https://code.visualstudio.com/) com as seguintes extensões instaladas:

**Obrigatórias para o Backend (Java/Spring):**
- Extension Pack for Java
- Spring Boot Extension Pack
- Spring Boot Dashboard
- Spring Boot Tools
- Language Support for Java
- Debugger for Java
- Maven for Java
- Project Manager for Java

**Obrigatórias para o Frontend:**
- Live Server
- HTML CSS Support

**Obrigatória para o Banco de Dados:**
- MySQL

---

## Aviso de Privacidade

Por questões de privacidade, o banco de dados não contém nenhum dado real. Os dados inseridos durante os testes foram removidos antes da entrega. Antes de utilizar o projeto, recomendo limpar as tabelas do banco de dados com os comandos abaixo:


TRUNCATE TABLE gamerblog.artigos;
TRUNCATE TABLE gamerblog.usuarios;

---

## Configuração do Banco de Dados

1. Abra o MySQL e execute:


CREATE DATABASE gamerblog;


2. Abra o arquivo `backend/src/main/resources/application.properties` e ajuste as credenciais:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/gamerblog
spring.datasource.username=root
spring.datasource.password=SUA_SENHA


> O Spring Boot criará as tabelas automaticamente ao iniciar a aplicação.

---

## Como Executar o Backend

1. Abra o terminal na pasta `backend`:

bash
cd backend


2. Rode a aplicação:

bash
./mvnw spring-boot:run


> No Windows, use `.\mvnw.cmd spring-boot:run`

3. O backend estará disponível em: `http://localhost:8080`

---

## Como Executar o Frontend

1. Abra a pasta `frontend` no VS Code
2. Clique com o botão direito no arquivo `index.html`
3. Selecione **"Open with Live Server"**
4. O site abrirá automaticamente no navegador

> **Importante:** O Live Server é obrigatório. Abrir os arquivos HTML diretamente pelo navegador (file://) não funcionará, pois o navegador bloqueará as requisições ao backend por questões de segurança.

---

## Como Usar

### Cadastro e Login

1. Acesse `cadastro.html` pelo Live Server e crie uma conta
2. Acesse `login.html` e faça login
3. Após o login, você será redirecionado para o painel administrativo

### Painel Administrativo

- Preencha o formulário com título, resumo, conteúdo, autor e imagem de capa
- Clique em **Salvar Artigo**
- O artigo aparecerá na lista abaixo e na página inicial do blog

### Blog Público

- A página inicial (`index.html`) exibe todos os artigos publicados
- Clique em **Ler mais** para ver o conteúdo completo do artigo

---