-- Criação das tabelas
CREATE TABLE IF NOT EXISTS aluno ( -- Tabela para armazenar informações dos alunos
    aluno_id INT NOT AUTO_INCREMENT PRIMARY KEY, -- Identificador único do aluno, não nulo e auto-incrementado
    aluno_matricula VARCHAR(10) NOT NULL UNIQUE, -- Matrícula do aluno (única) não nula
    aluno_nome VARCHAR(50) NOT NULL, -- Nome do aluno não nulo
    aluno_documento VARCHAR(20) NOT NULL, -- Algum número de documento do aluno (CPF, RG, etc.) não nulo
    fk_curso_id INT NOT NULL -- Chave estrangeira referenciando o curso do aluno não nula
);

CREATE TABLE IF NOT EXISTS curso ( -- Tabela para armazenar informações dos cursos
    curso_id INT AUTO_INCREMENT PRIMARY KEY, -- Identificador único do curso, não nulo e auto-incrementado
    curso_codigo VARCHAR(10) NOT NULL UNIQUE, -- Código do curso (único) não nulo
    curso_nome VARCHAR(50) NOT NULL, -- Nome do curso não nulo
    curso_carga_horaria INT NOT NULL -- Carga horária do curso não nula
);

-- Criação das constraints (chaves estrangeiras)
ALTER TABLE aluno ADD CONSTRAINT fk_aluno_curso 
    FOREIGN KEY (fk_curso_id) REFERENCES curso (curso_id) -- Define a chave estrangeira para o curso do aluno

    ON DELETE NO ACTION ON UPDATE NO ACTION; -- Define ações para deleção e atualização, sem ação específica

