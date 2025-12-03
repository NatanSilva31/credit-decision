# Credit Decision API  
**Java 17 • Spring Boot 3 • REST API • H2 Database • Clean Architecture (Simplificada)**

![Java](https://img.shields.io/badge/Java-17-red)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

API criada para realizar a análise simples de solicitações de crédito, aplicando regras objetivas baseadas em renda, score, valor solicitado e histórico de inadimplência. Projeto desenvolvido para fins de avaliação técnica.

---

## 📌 **Objetivo do Projeto**

Demonstrar domínio de:
- Java + Spring Boot  
- Estruturação de APIs REST  
- Boas práticas (camadas, DTOs, services)  
- Persistência com JPA/H2  
- Testes unitários básicos  
- Organização e clareza no código  

---

## 🏗 **Arquitetura**

A aplicação segue um modelo simples baseado em camadas:
Controller → Service → Repository → Database (H2)


### 🔹 Fluxo da regra de decisão

```mermaid
flowchart TD
    A[Início] --> B[Recebe solicitação]
    B --> C{Score < 600?}
    C -->|Sim| F[Negado]
    C -->|Não| D{Possui dívidas?}
    D -->|Sim| F[Negado]
    D -->|Não| E{Valor > 8x renda?}
    E -->|Sim| F[Negado]
    E -->|Não| G[Aprovado]
