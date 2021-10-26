CREATE DATABASE barbearia;

USE barbearia;

CREATE TABLE agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cliente VARCHAR(255),
    telefone VARCHAR(20),
    servico VARCHAR(30),
    data TIMESTAMP,
    observacoes VARCHAR(255),
    primary key (id)
);
