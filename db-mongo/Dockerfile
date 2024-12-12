# Baseado na imagem oficial do MongoDB
FROM mongo:6

# Copia um script de inicialização opcional para o MongoDB
COPY init.js /docker-entrypoint-initdb.d/

# Exponha a porta padrão do MongoDB
EXPOSE 27017