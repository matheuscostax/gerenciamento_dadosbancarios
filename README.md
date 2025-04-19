# 💳 Sistema de Gerenciamento Bancário

Este projeto consiste em um **sistema bancário completo**, desenvolvido em **Java** com integração ao **MySQL**, seguindo os princípios da **Programação Orientada a Objetos (POO)**. O sistema permite o gerenciamento de clientes, contas, transações bancárias e autenticação de gerentes, com uma arquitetura modular e bem estruturada.

## 👥 Desenvolvedores
- **Matheus Costa** – Estudante de Sistemas de Informação  
- **João Vitor Maia** – Estudante de Sistemas de Informação  

## 🛠️ Tecnologias Utilizadas
- Java (JDK 17)
- MySQL
- JDBC (Java Database Connectivity)
- Programação Orientada a Objetos (POO)

## 📁 Estrutura do Projeto

```
src/
├── app/
│   └── Main.java
├── banco/
│   ├── mysql-connector-j-8.0.31.jar
│   └── tabelas.sql
├── dao/
│   ├── ClienteDAO.java
│   ├── ContaDAO.java
│   ├── FuncionarioDAO.java
│   ├── TransacaoDAO.java
│   ├── ConexaoMySQL.java
│   ├── Transferencia.java
│   ├── Transferencia_PIX.java
│   └── Transferencia_TED.java
├── entidades/
│   ├── Autenticavel.java
│   ├── Cliente.java
│   ├── ClientePF.java
│   ├── ClientePJ.java
│   ├── Conta.java
│   ├── Escriturario.java
│   ├── Funcionario.java
│   ├── Gerente.java
│   └── Transacao.java
```

## ✅ Funcionalidades
- Autenticação de gerente via interface `Autenticavel`
- Cadastro de clientes (Pessoa Física e Jurídica)
- Abertura e gerenciamento de contas bancárias
- Operações bancárias: depósito, saque, transferência (PIX e TED)
- Persistência dos dados via MySQL
- Controle de acesso e segregação de responsabilidades por tipo de funcionário

## 📌 Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/matheuscostax/gerenciamento_dadosbancarios.git
   ```
2. Configure o banco de dados MySQL:
   - Execute o script `tabelas.sql` para criar as tabelas necessárias.
3. Adicione o driver JDBC (`mysql-connector-j-8.0.31.jar`) ao classpath do projeto.
4. Execute a classe `Main` localizada em `src/app/Main.java`.

## 📝 Observações
- Certifique-se de ajustar as configurações de conexão no arquivo `ConexaoMySQL.java` com as credenciais corretas do seu banco de dados.
- O sistema é orientado para testes em terminal, mas sua estrutura permite evolução para interface gráfica ou APIs futuramente.
