/* Popula Curso*/

INSERT INTO curso (curso_codigo, curso_nome, curso_carga_horaria) VALUES ('111', 'Sistemas da Informação', 3600);
INSERT INTO curso (curso_codigo, curso_nome, curso_carga_horaria) VALUES ('112', 'TPG', 2160);
INSERT INTO curso (curso_codigo, curso_nome, curso_carga_horaria) VALUES ('113', 'Engenharia de Software', 3600);
INSERT INTO curso (curso_codigo, curso_nome, curso_carga_horaria) VALUES ('114', 'Biologia', 3600);
INSERT INTO curso (curso_codigo, curso_nome, curso_carga_horaria) VALUES ('115', 'Agronomia', 3600);

/* Popula Aluno*/

INSERT INTO aluno (aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES ('0001', 'Gabriel', '01232545689', 1);
INSERT INTO aluno (aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES ('0002', 'Alessandro', '01232588756', 3);
INSERT INTO aluno (aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES ('0003', 'João', '01232545755', 2);
INSERT INTO aluno (aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES ('0004', 'Marcelo', '01232545999', 4);
INSERT INTO aluno (aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES ('0005', 'Maurício', '01232545666', 5);