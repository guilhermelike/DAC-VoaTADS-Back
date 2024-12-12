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

INSERT INTO flight.airports (
    id,
    codigo_aeroporto,
    nome,
    cidade,
    estado
) VALUES
      (DEFAULT, 'PAV', 'Afonso Pena', 'Curitiba', 'PR'),
      (DEFAULT, 'GRU', 'Guarulhos', 'São Paulo', 'SP'),
      (DEFAULT, 'CGH', 'Congonhas', 'São Paulo', 'SP'),
      (DEFAULT, 'CNF', 'Confins', 'Belo Horizonte', 'MG');

INSERT INTO flight.flight (
    id,
    codigo_voo,
    data_voo,
    aeroporto_origem,
    aeroporto_destino,
    valor_passagem,
    valor_milhas,
    total_poltronas,
    qtd_poltronas_ocupadas,
    status_voo
) VALUES
      (DEFAULT, 'TADS101', '2024-13-29 08:00', 'Afonso Pena', 'Guarulhos', 350.00, 1500, 180, 50, 'Realizado'),
      (DEFAULT, 'TADS102', '2024-13-25 12:30', 'Congonhas', 'Afonso Pena', 450.00, 2000, 200, 150, 'Realizado'),
      (DEFAULT, 'TADS103', '2024-12-21 16:45', 'Guarulhos', 'Congonhas', 500.00, 2500, 220, 180, 'Cancelado'),
      (DEFAULT, 'TADS104', '2025-01-22 20:15', 'Afonso Pena', 'Confins', 600.00, 3000, 250, 200, 'Cancelado'),
      (DEFAULT, 'TADS106', '2024-13-23 09:00', 'Confins', 'Afonso Pena', 550.00, 3200, 230, 170, 'Realizado'),
      (DEFAULT, 'TADS107', '2024-13-23 14:20', 'Afonso Pena', 'Congonhas', 400.00, 2100, 190, 120, 'Realizado'),
      (DEFAULT, 'TADS108', '2024-11-23 18:50', 'Congonhas', 'Confins', 650.00, 2800, 260, 200, 'Cancelado'),
      (DEFAULT, 'TADS109', '2024-11-24 07:15', 'Guarulhos', 'Afonso Pena', 500.00, 2500, 220, 180, 'Realizado'),
      (DEFAULT, 'TADS110', '2024-11-24 11:45', 'Confins', 'Guarulhos', 600.00, 3000, 250, 200, 'Realizado'),
      (DEFAULT, 'TADS111', '2024-14-24 15:30', 'Afonso Pena', 'Guarulhos', 750.00, 3700, 310, 260, 'Realizado'),
      (DEFAULT, 'TADS112', '2024-14-24 19:00', 'Congonhas', 'Afonso Pena', 420.00, 2300, 200, 140, 'Cancelado'),
      (DEFAULT, 'TADS113', '2024-13-25 08:30', 'Afonso Pena', 'Congonhas', 480.00, 2400, 210, 160, 'Realizado'),
      (DEFAULT, 'TADS114', '2024-13-25 13:00', 'Confins', 'Congonhas', 520.00, 2600, 230, 170, 'Cancelado'),
      (DEFAULT, 'TADS115', '2024-13-25 17:25', 'Guarulhos', 'Confins', 570.00, 2900, 240, 190, 'Realizado'),
      (DEFAULT, 'TADS116', '2024-13-26 06:45', 'Congonhas', 'Guarulhos', 610.00, 3100, 260, 210, 'Realizado'),
      (DEFAULT, 'TADS117', '2024-13-26 12:15', 'Afonso Pena', 'Confins', 430.00, 2200, 200, 150, 'Cancelado'),
      (DEFAULT, 'TADS118', '2024-13-26 16:00', 'Confins', 'Guarulhos', 460.00, 2400, 210, 160, 'Realizado'),
      (DEFAULT, 'TADS119', '2024-13-26 19:40', 'Guarulhos', 'Afonso Pena', 680.00, 3500, 300, 240, 'Cancelado'),
      (DEFAULT, 'TADS120', '2024-13-27 08:20', 'Afonso Pena', 'Congonhas', 530.00, 2700, 220, 170, 'Realizado');