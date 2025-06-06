# 💡 GS-SOA 2025 - FIAP  
### Disciplina: **Sistemas Orientados a Objetos e Serviços (SOA)**  
**Aplicação Spring Boot - Controle de Consumo de Energia Residencial**

---

## 📘 Descrição do Projeto

Este projeto consiste em uma API RESTful desenvolvida com **Spring Boot** com o objetivo de **gerenciar o consumo energético de uma residência**, permitindo que o usuário cadastre seus equipamentos, vincule seus dados pessoais e registre os valores de energia por hora e tarifas fixas por mês.

O sistema foi idealizado como se os dados de consumo fossem coletados por dispositivos IoT (não implementados), mas, para fins acadêmicos, os dados são inseridos manualmente.

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Oracle Database
- Lombok
- Swagger (springdoc-openapi)
- Maven


---

## 🔗 Relacionamentos

- **User** 1:1 **Person**
- **Person** 1:N **Equipament**
- **Equipament** 1:N **EnergyConsume**

---

## 📋 Funcionalidades da API

### 📍 Person
- `POST /person` – Criar pessoa
- `GET /person/{id}` – Buscar pessoa por ID
- `GET /person` – Listar todas
- `PUT /person/{id}` – Atualizar dados
- `DELETE /person/{id}` – Remover pessoa

### 📍 User
- `POST /user` – Criar usuário (precisa de Person já criada)
- `GET /user/{id}` – Buscar usuário
- `GET /user` – Listar todos
- `DELETE /user/{id}` – Deletar usuário

### 📍 Equipament
- `POST /equipament` – Criar equipamento
- `GET /equipament/{id}` – Buscar por ID
- `GET /equipament` – Listar todos
- `PUT /equipament/{id}` – Atualizar equipamento
- `DELETE /equipament/{id}` – Remover equipamento

### 📍 EnergyConsume
- `POST /energy-consume` – Criar gasto de energia
- `PUT /energy-consume/{id}` – Atualizar valores
- `GET /energy-consume/equipament/{equipamentId}` – Buscar gastos por equipamento
- `GET /energy-consume/monthly-total?personId=1&month=5&year=2025` – Total de consumo por mês/ano
- `DELETE /energy-consume/{id}` – Deletar gasto registrado

---

## 🧪 Swagger

Após subir a aplicação, acesse:

📎 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

---

## 🧑‍💻 Como Executar Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gssoa2025.git
   cd gssoa2025
    ./mvnw spring-boot:run
    ```
