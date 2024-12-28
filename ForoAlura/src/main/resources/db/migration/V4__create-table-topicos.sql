CREATE TABLE topicos (
    id BIGINT not null auto_increment,
    titulo VARCHAR(100) not null,
    mensaje VARCHAR(400) not null,
    fecha datetime not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)

);