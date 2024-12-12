-- Criação do banco de dados se não existir
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT
        FROM pg_catalog.pg_database
        WHERE datname = 'postgres') THEN
        PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE postgres');
    END IF;
END
$$;

-- Conectar ao banco de dados postgres
\c postgres;

-- Criação de uma tabela de exemplo
DROP TABLE IF EXISTS usuarios;
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Criação dos schemas
CREATE SCHEMA IF NOT EXISTS bookingcommand;
CREATE SCHEMA IF NOT EXISTS bookingquery;
CREATE SCHEMA IF NOT EXISTS customer;
CREATE SCHEMA IF NOT EXISTS employee;
CREATE SCHEMA IF NOT EXISTS flight;