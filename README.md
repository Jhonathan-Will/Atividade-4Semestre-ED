# Projeto: Sistema de Gerenciamento de Pontos e Vendas

## Descrição do Projeto
Este projeto é um sistema de gerenciamento de pontos e vendas desenvolvido como parte de uma atividade acadêmica para o 4º semestre da disciplina de Estrutura de Dados. Ele permite que vendedores registrem vendas, acumulem pontos com base em critérios específicos e realizem conversões de pontos. O sistema também inclui autenticação baseada em JWT para garantir a segurança das operações.

---

## Objetivos
- **Gerenciar vendas e pontos**: Permitir que vendedores registrem vendas e acumulem pontos com base em regras predefinidas.
- **Conversão de pontos**: Implementar a funcionalidade de conversão de pontos para valores específicos.
- **Autenticação segura**: Garantir que apenas usuários autenticados possam acessar as funcionalidades do sistema.
- **Organização e boas práticas**: Utilizar boas práticas de desenvolvimento, como separação de responsabilidades e uso de DTOs.

---

## Características
- **Autenticação JWT**: O sistema utiliza tokens JWT para autenticação e autorização.
- **Gerenciamento de vendedores**: Cadastro, consulta e autenticação de vendedores.
- **Gerenciamento de vendas**: Registro de vendas e consulta de vendas por vendedor.
- **Sistema de pontos**: Acúmulo e conversão de pontos com base em regras específicas.
- **Interceptors**: Middleware para interceptar e validar requisições protegidas.
- **Banco de dados relacional**: Persistência de dados utilizando MySQL.

---

## Ferramentas e Tecnologias Utilizadas
- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.4.4**: Framework para desenvolvimento rápido de aplicações Java.
  - **Spring Security**: Para autenticação e autorização.
  - **Spring Data JPA**: Para persistência de dados.
  - **Spring Validation**: Para validação de dados.
- **MySQL**: Banco de dados relacional para persistência.
- **JWT (JSON Web Tokens)**: Para autenticação segura.
- **Maven**: Gerenciador de dependências e build.
- **Jakarta Validation**: Para validação de dados de entrada.
- **Postman**: Para testes de API.

---

## Estrutura do Projeto
O projeto segue uma estrutura organizada em pacotes para facilitar a manutenção e escalabilidade:

```
src/main/java/com/faculdade/atividade
├── configuration/       # Configurações do Spring (ex.: interceptors)
├── controller/          # Controladores REST
├── data/                # Classes de suporte para autenticação
├── dto/                 # Data Transfer Objects (DTOs)
├── middleware/          # Interceptores para validação de requisições
├── models/              # Entidades JPA
├── repository/          # Repositórios JPA
├── security/            # Configurações e serviços de segurança (JWT)
├── services/            # Regras de negócio e lógica de aplicação
└── AtividadeApplication.java # Classe principal
```

---

## Como o Projeto Foi Construído
1. **Configuração do Ambiente**:
   - Configuração do banco de dados MySQL no arquivo `application.properties`.
   - Configuração de autenticação JWT com chaves pública e privada.

2. **Modelagem de Dados**:
   - Entidades JPA como `Vendedor`, `Venda` e `Ponto` foram criadas para representar os dados no banco de dados.
   - Relacionamentos entre entidades foram definidos com anotações como `@OneToMany` e `@ManyToOne`.

3. **Regras de Negócio**:
   - Implementadas nos serviços (`Service`) para encapsular a lógica de negócios, como conversão de pontos e registro de vendas.

4. **APIs REST**:
   - Controladores REST foram criados para expor as funcionalidades do sistema.
   - Validação de entrada foi implementada com `@Valid` e DTOs.

5. **Segurança**:
   - Configuração do Spring Security para autenticação e autorização.
   - Middleware (`AuthInterceptor`) para validar tokens JWT em rotas protegidas.

---

## Como Usar o Projeto

### Pré-requisitos
- **Java 17** ou superior instalado.
- **MySQL** configurado e rodando.
- **Maven** instalado para gerenciar dependências.

### Passos para Configuração
1. **Clone o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd Atividade-4Semestre-ED
   ```

2. **Crie o arquivo `application.properties`**:
   - Crie e configure o arquivo de configuração do projeto. Para isso, crie as seguintes ramificações que estão faltando no projeto:
     ```
     src/main
     ├── java
     └── resources
         └── application.properties
     ```
   - Dentro do arquivo `application.properties`, insira o seguinte dado:
     ```properties
     server.port=8080
     ```

3. **Configure o banco de dados**:
   - Atualize as credenciais do banco de dados no arquivo `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/atividade_ed
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     ```

4. **Configure o JWT**:
   - Adicione as configurações de segurança no `application.properties`:
     ```properties
     jwt.secret=seu_secret_jwt
     jwt.private.key=classpath:app.key
     jwt.public.key=classpath:app.pub
     ```
   - Crie uma chave privada (`app.key`) e uma chave pública (`app.pub`). Elas devem ficar em arquivos separados na mesma pasta que está o `application.properties`. Veja a ramificação de pastas para se localizar:
     ```
     src/main
     ├── java
     └── resources
         ├── app.key
         ├── app.pub
         └── application.properties
     ```

   4.1 **Adicione as seguintes chaves**:
   - **`app.key`**:
     ```
     -----BEGIN PRIVATE KEY-----
     MIICWwIBAAKBgQCAxyHhK6g14LVArZAYaNMeoBz1J6hUIv9bESwi+FDhDspXTF9i
     QVSxxDhaZg8kQ62S4XmKkY3Dsfd8cLJE+R5JDTsTKCdqQWLB9NZb3nfMxgip1WAw
     Qvfhz7AMMr9DdUOx6ZQ2k3+oJEmpNQ3ZTc9agxfE6k3yS5ZCMtZXG2sVOwIDAQAB
     AoGASnPWsJpO/CdYY3Z1MseYF8K2Oz6cYViCjiIVhHpQOG4WqfYrx2DCGKy7b/hF
     lM/UEHFvlZ+leJjx3u5BAwOMcBNJu9LEIn4Rd7Av1tWen+3LnqyCqzr4tGXmeJfy
     y9QZjgEqFcLPMMEmMeBzEuPW/dOkSbp4oHl2X1rcYr11+/ECQQDo6hQpU2eWtDCm
     mWUg6HapEkX8hdysasc6fs2CTJtTlS6ah19zGvea+3pp7h0zLHmkA5cUGe3cGJ7i
     W0JhDhS/AkEAjYq3GpxbMA2zCTDgYWSLXT7zWiXTBCdpJ9dF+EXEzLelmXN2u9Mb
     Crz0K6qfsaf2IuKhwgaz2DWRk8a6czMyhQJAFhSi5qwOhP3+Ir0mFjGTlkGyOtdh
     7UKOMqB00SZ8Dzd0XJf614m18Y5ns4UsZX6Q5ZiliPVQVG5NGb53E4ZnNwJAIXnJ
     TJXmSzsyON5Cc0ZgX7FfxNZlTfo0OKQFfG6j2PcmR2CzmECZiLSf2yQDmPTsldub
     /lOvfVdOEvFRb6WQMQJAFOI2BssEoJznzFC5VXZoRarzSbVlxQQWd/lLcSiK/UN2
     UgfLPSnHeyh721Piwg0i7/tvs7pMazNtERzP/Sp45Q==
     -----END PRIVATE KEY-----
     ```

   - **`app.pub`**:
     ```
     -----BEGIN PUBLIC KEY-----
     MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAxyHhK6g14LVArZAYaNMeoBz1
     J6hUIv9bESwi+FDhDspXTF9iQVSxxDhaZg8kQ62S4XmKkY3Dsfd8cLJE+R5JDTsT
     KCdqQWLB9NZb3nfMxgip1WAwQvfhz7AMMr9DdUOx6ZQ2k3+oJEmpNQ3ZTc9agxfE
     6k3yS5ZCMtZXG2sVOwIDAQAB
     -----END PUBLIC KEY-----
     ```

5. **Compile e execute o projeto**:
   ```bash
   ./mvnw spring-boot:run
   ```

6. **Acesse a aplicação**:
   - A API estará disponível em: `http://localhost:8080`.

---

## Exemplos de Uso

### 1. **Registro de Vendedor**
- **Endpoint**: `POST /auth/register`
- **Corpo da Requisição**:
  ```json
  {
    "name": "João",
    "email": "joao@email.com",
    "password": "senha123"
  }
  ```

### 2. **Login**
- **Endpoint**: `POST /auth/login`
- **Corpo da Requisição**:
  ```json
  {
    "email": "joao@email.com",
    "password": "senha123"
  }
  ```
- **Resposta**:
  ```json
  {
    "token": "JWT_TOKEN_AQUI"
  }
  ```

### 3. **Registrar Venda**
- **Endpoint**: `POST /exercice/sale`
- **Cabeçalho**:
  ```http
  Authorization: Bearer JWT_TOKEN_AQUI
  ```
- **Corpo da Requisição**:
  ```json
  {
    "in_person": true,
    "value": 150.0
  }
  ```

### 4. **Converter Pontos**
- **Endpoint**: `PUT /exercice/point/convert`
- **Cabeçalho**:
  ```http
  Authorization: Bearer JWT_TOKEN_AQUI
  ```
- **Corpo da Requisição**:
  ```json
  {
    "value": "50"
  }
  ```

---

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

---

## Licença
Este projeto está licenciado sob a MIT License.

--- 
