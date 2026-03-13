# 📋 Task Manager Challenge

API REST de gerenciamento de tarefas construída com Spring Boot, desenvolvida como desafio técnico para prática de entrevistas backend Java.

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- springdoc-openapi (Swagger)
- Docker Compose

---

## ✅ Checklist de Desenvolvimento

### 🏗️ Estrutura e Configuração

- [x] Configuração do projeto Spring Boot
- [x] Configuração do banco de dados PostgreSQL (`application.properties`)
- [x] Docker Compose para subir o banco
- [x] Configuração do SpringDoc/Swagger (`SpringDocAPIConfiguration.java`)
- [x] Entidade `User`
- [x] Entidade `Task`
- [x] Enum `Status` (`PENDING`, `IN_PROGRESS`, `DONE`)

---

### 🗄️ Camada de Repositório

- [x] `UserRepository` (Spring Data JPA)
    - [x] Método para verificar e-mail duplicado (`existsByEmail`)
- [ ] `TaskRepository` (Spring Data JPA)
    - [ ] Método para buscar tarefas por usuário (`findByUserId`)

---

### 📦 DTOs

- [x] `UserRequestDTO` (name, email)
- [x] `UserResponseDTO` (id, name, email)
- [ ] `TaskRequestDTO` (title, description, status, userId)
- [ ] `TaskResponseDTO` (id, title, description, status, userId)
- [ ] `TaskStatusUpdateDTO` (status)

---

### ⚙️ Camada de Serviço

- [x] `UserService`
    - [x] `createUser` — valida e-mail único, salva usuário
- [ ] `TaskService`
    - [ ] `createTask` — valida existência do usuário, cria tarefa
    - [ ] `getTasksByUser` — retorna todas as tarefas de um usuário
    - [ ] `updateTaskStatus` — atualiza status, bloqueia transição a partir de `DONE`
    - [ ] `deleteTask` — remove tarefa por ID

---

### 🌐 Camada de Controller

- [x] `UserController`
    - [x] `POST /users` — criar usuário
    - [ ] `GET /users/{id}/tasks` — listar tarefas do usuário
- [ ] `TaskController`
    - [ ] `POST /tasks` — criar tarefa
    - [ ] `PATCH /tasks/{id}/status` — atualizar status da tarefa
    - [ ] `DELETE /tasks/{id}` — deletar tarefa

---

### 🛡️ Validações

- [x] Anotações `@Valid`, `@NotBlank`, `@NotNull`, `@Email` nos DTOs
- [x] Validação de e-mail único no serviço
- [ ] Validação de transição de status (`DONE` → qualquer outro é bloqueado)

---

### ❌ Tratamento de Erros

- [x] Classe `GlobalExceptionHandler` com `@ControllerAdvice`
    - [x] `EntityNotFoundException` (usuário ou tarefa não encontrado → 404)
    - [x] `EmailAlreadyExistsException` (e-mail duplicado → 409)
    - [x] `InvalidStatusTransitionException` (transição inválida de status → 422)
    - [x] `MethodArgumentNotValidException` (erros de validação → 400)

---

### ⭐ Diferenciais

- [ ] Paginação no endpoint `GET /tasks?page=0&size=10`
- [x] Documentação completa via Swagger (springdoc-openapi)
- [ ] Uso de `@Transactional` nos métodos de escrita

---

### 🧪 Testes

- [ ] Testes unitários do `UserService` com JUnit + Mockito
- [ ] Testes unitários do `TaskService` com JUnit + Mockito
- [ ] Testes de integração do `UserController`
- [ ] Testes de integração do `TaskController`

---

## 📁 Estrutura do Projeto

```
src/main/java/com/cauanlagrotta/task_manager_challenge/
├── config/
│   └── SpringDocAPIConfiguration.java
├── controller/
│   ├── UserController.java
│   └── TaskController.java
├── dto/
│   ├── UserRequestDTO.java
│   ├── UserResponseDTO.java
│   ├── TaskRequestDTO.java
│   ├── TaskResponseDTO.java
│   └── TaskStatusUpdateDTO.java
├── entity/
│   ├── enums/
│   │   └── Status.java
│   ├── User.java
│   └── Task.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── EmailAlreadyExistsException.java
│   ├── EntityNotFoundException.java
│   └── InvalidStatusTransitionException.java
├── repository/
│   ├── UserRepository.java
│   └── TaskRepository.java
├── service/
│   ├── UserService.java
│   └── TaskService.java
└── TaskManagerChallengeApplication.java
```

---

## 🗺️ Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/users` | Criar usuário |
| `GET` | `/users/{id}/tasks` | Listar tarefas do usuário |
| `POST` | `/tasks` | Criar tarefa |
| `PATCH` | `/tasks/{id}/status` | Atualizar status da tarefa |
| `DELETE` | `/tasks/{id}` | Deletar tarefa |
| `GET` | `/tasks?page=0&size=10` | Listar tarefas com paginação |
| `GET` | `/api-docs.html` | Swagger UI |

---

## ▶️ Como Rodar

```bash
# Subir o banco de dados
docker-compose up -d

# Rodar a aplicação
./mvnw spring-boot:run
```

Acesse a documentação em: [http://localhost:8080/api-docs.html](http://localhost:8080/api-docs.html)

---

## 💡 Pontos para Entrevista

- **Por que usar DTO?** Evita expor a entidade diretamente, desacopla a API do modelo interno e facilita versionamento.
- **Por que existe a Service layer?** Centraliza as regras de negócio e mantém o Controller responsável apenas pelo HTTP.
- **PUT vs PATCH?** PUT substitui o recurso inteiro; PATCH aplica uma atualização parcial (ideal para mudar só o status).
- **Por que usar `@Transactional`?** Garante que operações de escrita sejam atômicas — ou tudo persiste, ou nada persiste.