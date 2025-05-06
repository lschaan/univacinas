# univacinas-backend
Projeto para um sistema de gerenciamento de clínica de vacinas

### Funcionalidades da aplicação

- Gerenciar usuários
- Gerenciar lotes de vacina
- Realizar consultas médicas
- Criar relatórios de estoque

### Rodando a aplicação

#### Pré-requisitos

- Postgres rodando
- Alterar application.properties para apontar para URL do Postgres

#### Iniciando

- É possível rodar utilizando `./gradlew bootRun`
- A documentação das rotas se encontra em localhost:8080/swagger-ui.html

### Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.5
- Postgres
- Gradle
- Lombok
- Swagger