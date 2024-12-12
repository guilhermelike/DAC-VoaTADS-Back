#Baseado na imagem oficial do PostgreSQL
FROM postgres:15-alpine

# Copia os scripts SQL de inicialização para o diretório padrão
COPY init.sql /docker-entrypoint-initdb.d/

# Exponha a porta padrão do PostgreSQL
EXPOSE 5432