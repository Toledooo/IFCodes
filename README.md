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

1.  **Extração:** Descompacte o arquivo do projeto (por exemplo, `br.com.boeira.bancoii.sorm.rar` ou `orm.rar`).
2.  **Verificação de Credenciais:** Verifique a classe de conexão (geralmente `FabricaConexao.java` na versão nativa ou `application.properties` na versão framework). As credenciais devem corresponder às definidas no script de criação do banco:
    * **URL:** `jdbc:mysql://localhost:3306/bancoii`
    * **Usuário:** `sa` (Conforme criado no script SQL).
    * **Senha:** (Vazia/Em branco, conforme comando `IDENTIFIED BY ''`).
