# NutriApp - API REST 

Este projeto é uma API RESTful desenvolvida com Spring Boot para realizar operações CRUD (Create, Read, Update, Delete) dentro do contexto de paciente x dieta (refeições). 
Ele permite o gerenciamento de um banco de dados de pacientes e suas respectivas refeições, com suporte a várias operações HTTP.

## Acesso a API no Railway
- Acesse a documentação da API através do  [Swagger UI](https://api-refeicoes-production.up.railway.app/swagger-ui/index.html).
- Utilize um cliente HTTP (Postman, Insomnia, etc) para realizar as requisições

## Como executar o projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/nutriapp.git
2. Abra o projeto em sua IDE de preferência
3. Execute a classe `ProjetorestApplication.java`
4. Acesse a documentação da API no link :
5. ```bash 
   http://localhost:8090/swagger-ui.html
6. Utilize um cliente HTTP (Postman, Insomnia, etc) para realizar as requisições

## Funcionalidades

### Paciente
- **Criar** um novo paciente
- **Buscar** um paciente por ID ou listar todos os pacientes
- **Atualizar** as informações de um paciente existente
- **Deletar** um paciente
- **Buscar** e listar todas as refeições de um paciente
- **Deletar** uma refeição de um paciente

### Refeição
- **Criar** uma nova refeição para um paciente
- **Buscar** uma refeição por ID ou listar todas as refeições de um paciente
- **Atualizar** as informações de uma refeição existente
- **Deletar** uma refeição

## Banco de Dados
Este projeto utiliza um banco de dados relacional para persistência de dados. As entidades principais são Paciente e Refeição, com um relacionamento um para muitos, onde um paciente pode ter várias refeições associadas a ele.

### Modelo de Dados
 - **Paciente**: Contém informações como nome, idade, e email. Um paciente pode ter várias refeições associadas.
 - **Refeição**: Contém informações sobre a refeição, como descrição, calorias, e quantidade em gramas. Cada refeição pode ser associada a um único paciente.

## Tecnologias Utilizadas

- **Java 11** ou superior
- Spring Boot 3.3.4
- **Spring Web** para construção da API REST
- **Spring Data JPA** para persistência de dados
- **Lombok** para reduzir o código boilerplate
- **Maven** para gerenciamento de dependências