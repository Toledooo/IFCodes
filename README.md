# Sistema de Gerenciamento Acadêmico - SORM

Este projeto é uma aplicação Java desenvolvida para a disciplina de Banco de Dados II. O sistema implementa um mapeamento objeto-relacional simples (SORM) para gerenciar o relacionamento entre **Alunos** e **Cursos**, utilizando o padrão de projeto DAO (Data Access Object) e conexão JDBC.

A aplicação oferece uma interface via console para realizar operações de CRUD (Criar, Ler, Atualizar e Deletar) no banco de dados `bancoii`.

## 📋 Funcionalidades

O sistema permite a interação via terminal para gerenciar as entidades acadêmicas:

* **Gerenciamento de Cursos:**
    * Cadastro de novos cursos com Código, Nome e Carga Horária.
    * Listagem e manutenção de cursos existentes.
* **Gerenciamento de Alunos:**
    * Cadastro de alunos com Matrícula, Nome e Documento.
    * Associação de alunos a um curso específico (Relacionamento 1:N).
* **Persistência:**
    * Conexão direta com banco de dados MySQL/MariaDB.
    * Scripts SQL inclusos para criação automática da estrutura e população de dados de teste.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17+)
* **Gerenciamento de Dependências:** Gradle 8.14
* **Banco de Dados:** MySQL ou MariaDB
* **Arquitetura:** DAO (Data Access Object) / Console Application

## 🗄️ Estrutura do Banco de Dados

O banco de dados, denominado `bancoii`, possui a seguinte estrutura definida no script de criação:

### Tabelas

1.  **`curso`**
    * `curso_id` (INT, PK, Auto Increment): Identificador único.
    * `curso_codigo` (VARCHAR, Unique): Código de referência do curso.
    * `curso_nome` (VARCHAR): Nome do curso.
    * `curso_carga_horaria` (INT): Carga horária total.

2.  **`aluno`**
    * `aluno_id` (INT, PK, Auto Increment): Identificador único.
    * `aluno_matricula` (VARCHAR, Unique): Matrícula acadêmica.
    * `aluno_nome` (VARCHAR): Nome completo.
    * `aluno_documento` (VARCHAR): Documento de identificação.
    * `fk_curso_id` (INT, FK): Chave estrangeira vinculando ao curso.

## 🚀 Como Executar o Projeto

### 1. Configuração do Banco de Dados

Antes de iniciar a aplicação Java, prepare o ambiente de banco de dados:

1.  Certifique-se de ter o **MySQL** ou **MariaDB** instalado.
2.  Execute o script de criação (`cria_banco_trabalho.sql`). Este script irá:
    * Criar o database `bancoii`.
    * Criar o usuário `sa` com senha vazia e permissões totais.
    * Criar as tabelas e relacionamentos.
3.  (Opcional) Execute o script de população (`popula_banco_trabalho.sql`) para inserir dados iniciais.

**Exemplo via terminal:**
```bash
mysql -u root -p < cria_banco_trabalho.sql
mysql -u root -p bancoii < popula_banco_trabalho.sql
